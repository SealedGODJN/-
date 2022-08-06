package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"strings"
)

var indexPath = filepath.Join(".git", "index")

/*
先操作一下 git 的缓冲区加深一下理解，首先引入一条新的指令 git update-index，它可以人为地把一个文件加入到一个新的缓冲区中，而且要加上一个 --add 的参数，因为这个文件之前还不存在于缓冲区中。

$ git update-index --add file.txt
然后我们观察一下.git目录的变化
$ ls
config  description  HEAD  hooks/  index  info/  objects/  refs/

$ find .git/objects/ -type f
objects/5b/dcfc19f119febc749eef9a9551bc335cb965e2
objects/df/7af2c382e49245443687973ceb711b2b74cb4a
发现.git目录下多了一个名为index的文件，这估计就是我们的缓冲区了

我们查看一下缓冲区的内容，这里用到一条指令：git ls-files --stage

$ git ls-files --stage
100644 df7af2c382e49245443687973ceb711b2b74cb4a 0       file.txt
我们发现缓冲区是这样来存储我们的添加记录的：
一个文件模式的代号，文件内容的 blob object，一个数字和文件的名字。

git update-index更新暂存区，官方的这条指令是带有很多参数的，我们只实现 --add，也就是添加文件到暂存区。
if 要添加的文件不在objects数据库中，则 进入 <code>缓冲区</code> 我们需要创建一个index目录，同时，将该文件添加到objects目录中
if 要添加的文件已经存在于objects数据中 则 直接把暂存区index的内容读取出来（需要解压uncompress）
*/
func UpdateIndex(a bool, args []string) {
	if !a {
		log.Fatal("sorry, only support add file to the index now...")
	}
	// path为文件的相对路径
	path := args[len(args)-1]

	// 重构，把原来用到的属性放入BlobObject
	var blob BlobObject
	blob.Path = path
	sha1, data := getSha1AndRawData(&blob)

	// 文件不存在于objects目录中
	if exist, _ := isObjectExist(sha1); !exist {
		// Hash-Object里面的writeObject方法【先重构Hash-Object，再去实现update-index的操作】
		writeObject(sha1, data)
	}

	// // create an object for the file content if the object is not exist
	// sha1 := getSha1Str(path, "blob")
	// // 如果objects文件夹里面没有该文件对应的文件夹，则先把该文件的内容写入objects文件夹里面
	// if exist := isObjectExist(sha1); !exist {
	// 	// Hash-Object里面的writeObject方法【先重构Hash-Object，再去实现update-index的操作】
	// 	writeObject(sha1, getData(path, "blob"))
	// }

	// 暂存区目录index不存在
	if _, err := os.Stat(indexPath); os.IsNotExist(err) {
		// 创建.git/index文件夹
		_, err := os.Create(indexPath)
		if err != nil {
			log.Fatal(err)
		}
		log.Println("INFO:已创建.git/index文件")
	}

	// read entry-list from index
	// 获得暂存区的所有Entry条目
	entryList := getEntryListFromIndex()

	// return if the entry was existed
	// 如果这个文件已经在暂存区里面，则直接返回
	for _, e := range entryList.List {
		if e.Sha1 == sha1 {
			return
		}
	}

	// 文件不在暂存区里面，则新建暂存区的条目entry，然后加入到entryList中
	entry := Entry{"100644", sha1, 0, path, "blob"}
	entryList.List = append(entryList.List, entry)

	// write entry-list into index
	writeEntryListToIndex(entryList)
}

func isObjectExist(sha1 string) (bool, string) {
	// 获取objects目录下的所有objects文件，并于sha1进行对比
	dir, err := ioutil.ReadDir(filepath.Join(".git", "objects"))
	if err != nil {
		log.Printf("ERROR:该路径不存在-%s", ".git/objects")
		log.Fatal(err)
	}

	// 先判断文件夹是否存在
	prefix := sha1[:2]
	postfix := sha1[2:]

	isExistDir := false

	// 二分查找会更快?
	for _, v := range dir {
		if prefix == v.Name() {
			// return true
			isExistDir = true
		}
	}
	if !isExistDir {
		return false, ""
	}

	// 原代码没有下面👇这一部分，我认为仍然需要检测该文件夹下的object文件是否存在

	// 再在文件夹中判断该Objects文件是否存在
	objectDir := filepath.Join(".git", "objects", prefix)

	dir1, err1 := ioutil.ReadDir(objectDir)
	// 判断读目录里面的文件时是否出错
	if err1 != nil {
		log.Printf("ERROR:目录不存在-%s", objectDir)
		log.Fatal(err)
	}

	// 遍历dir里面的所有文件
	// 找到符合自己要求的文件
	for _, file := range dir1 {
		if strings.HasPrefix(file.Name(), postfix) {
			// 该文件名的前缀与命令行参数中输入的objectId的前缀相符
			return true, file.Name()
		}
	}
	return false, ""
}

// 不理解该操作
// 理解了，该操作是获取暂存区中的所有文件（为了检查update-index新加入的文件是否已经在暂存区了）
func getEntryListFromIndex() *EntryList {
	bytes, err := ioutil.ReadFile(indexPath)
	if err != nil {
		log.Println(".git/index文件夹不存在")
		log.Fatal(err)
	}
	var entryList EntryList
	if len(bytes) > 0 {
		bytes = unCompressData(bytes)
		// 反序列化
		err = json.Unmarshal(bytes, &entryList)
		if err != nil {
			log.Fatal(err)
		}
	}
	return &entryList
}

func writeEntryListToIndex(entryList *EntryList) {
	// 序列化
	bytes, err := json.Marshal(entryList)
	if err != nil {
		log.Fatal(err)
	}

	// 压缩
	bytes = compress(bytes)
	// 把序列化后的数据bytes写入index文件夹
	err = ioutil.WriteFile(indexPath, bytes, os.ModePerm)
	if err != nil {
		log.Fatal(err)
	}
}
