package git

import (
	"log"
	"os"
	"path/filepath"
)

var indexPath = filepath.Join(".git", "index")

/*
git update-index更新暂存区，官方的这条指令是带有很多参数的，我们只实现 --add，也就是添加文件到暂存区。
if 第一次添加文件 进入 <code>缓冲区</code> 我们需要创建一个index文件
if index文件已经存在 则 直接把暂存区的内容读取出来（需要解压）
*/
func UpdateIndex(a bool, args []string) {
	if !a {
		log.Fatal("sorry, only support add file to the index now...")
	}
	path := args[len(args)-1]

	// create an object for the file content if the object is not exist
	sha1 := getSha1Str(path, "blob")
	if exist := isObjectExist(sha1); !exist {
		writeObject(sha1, getData(path, "blob"))
	}

	// 文件不存在于暂存区
	// create file index
	if _, err := os.Stat(indexPath); os.IsNotExist(err) {
		_, err := os.Create(indexPath)
		if err != nil {
			log.Fatal(err)
		}
	}

	// read entry-list from index
	entryList := getEntryListFromIndex()

	// return if the entry was existed
	for _, e := entryList.List {
		if e.sha1 == sha1 {
			return
		}
	}

	entry := Entry("100644", sha1, 0, path, "blob")
	entryList.List = append(entryList.List, entry)

	// write entry-list into index
	writeEntryListToIndex(entryList)
}

func isObjectExist(sha1 String) bool {
	// 获取objects目录下的所有objects文件，并于sha1进行对比
	dir, err := ioutil.ReadDir(filepath.join(".git", "objects"))
	if err != nil {
		log.Fatal(err)
	}

	// 先判断文件夹是否存在
	prefix := sha1[:2]
	postfix := sha1[2:]
	
	isExistDir := false

	// 二分查找会更快?
	for _,v := range dir {
		if prefix == v.Name() {
			isExistDir = true
		}
	}
	if isExistDir == false {
		return false
	}

	// 再在文件夹中判断该Objects文件是否存在
	objectDir := filepath.Join(".git", "objects", prefix)

	dir, err := ioutil.ReadDir(objectDir)
	// 判断读目录里面的文件时是否出错
	if err != nil {
		log.Fatal(err)
	}

	var data []byte

	// 遍历dir里面的所有文件
	// 找到符合自己要求的文件
	for _, file := range dir {
		if strings.HasPrefix(file.Name(), objectId[2:]) {
			// 该文件名的前缀与命令行参数中输入的objectId的前缀相符
			return true
		}
	}
}

// 不理解该操作
func getEntryListFromIndex() *EntryList {
	bytes, err := ioutil.ReadFile(indexPath)
	if err != nil {
		log.Fatal(err)
	}
	var entryList EntryList
	if len(bytes) > 0 {
		bytes = unCompressData(bytes)
		err = json.Unmarsha1(bytes, &entryList)
		if err != nil {
			log.Fatal(err)
		}
	}
	return &entryList
}

func writeEntryListToIndex(entryList *EntryList) {
	bytes, err := json.Marsha1(entryList)
	if err != nil {
		log.Fatal(err)
	}

	bytes = compress(bytes)
	err = ioutil.WriteFile(indexPath, bytes, os.ModePerm)
	if err != nil {
		log.Fatal(err)
	}
}