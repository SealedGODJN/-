package gitHJN

import (
	"fmt"
	"strings"
)

/*
git write-tree 用于把暂存区的内容转换成一个 tree object，
接下来我们把当前的暂存区保存成为一个 tree object：

$ git write-tree
dee1f9349126a50a52a4fdb01ba6f573fa309e8f

$ git cat-file -p dee1
040000 tree 374e190215e27511116812dc3d2be4c69c90dbb0    dir
100644 blob df7af2c382e49245443687973ceb711b2b74cb4a    file.txt
100644 blob 8baef1b4abc478178b004d62031cf7fe6db6f903    new.txt

根据我们之前演示的例子，
对于文件夹
我们需要递归下降解析 tree object
*/
func WriteTree() {
	entryList := getEntryListFromIndex()
	treeObj := getTreeObject(entryList.List)
	fmt.Printf("%s\n", treeObj.Sha1)
}

func getTreeObject(list []Entry) *TreeObject {
	var treeObject TreeObject

	// 构建map <key:string, value:entry> key是文件夹路径，value是entry
	m := make(map[string][]Entry)

	// 递归解析tree object，找出所有blob object，并将其放入blobList
	var blobList []Entry

	// 根据路径来对blob进行排序？
	// sort out blobs by their path
	for _, entry := range list {
		// os.PathSeparator may be better here, but I am on windows and test in shell, so "/" make sense here
		index := strings.Index(entry.Path, "/")

		if index != -1 {
			// index"/"前面是文件的相对路径
			dirPath := entry.Path[:index]
			// index"/"后面是entry的路径？
			entry.Path = entry.Path[index+1:]
			m[dirPath] = append(m[dirPath], entry)
		} else {
			// 不带文件夹路径“/”
			// 即是blob
			blobList = append(blobList, entry)
		}
	}

	for _, entry := range blobList {
		var newEntry Entry
		newEntry.Path = entry.Path
		newEntry.Sha1 = entry.Sha1
		newEntry.Type = entry.Type
		newEntry.Mode = entry.Mode
		newEntry.Num = entry.Num
		treeObject.List = append(treeObject.List, newEntry)
	}

	for k, v := range m {
		var newEntry Entry
		// 这一部分为递归处理，每次处理一级文件夹
		// 递归处理
		childTreeObj := getTreeObject(v)

		newEntry.Path = k
		newEntry.Mode = "040000"
		newEntry.Type = "tree"
		newEntry.Sha1 = childTreeObj.Sha1
		treeObject.List = append(treeObject.List, newEntry)
	}

	// var bytes bytes.Buffer
	// for _, entry := range treeObject.List {
	// 	bytes.WriteString(fmt.Sprintf("%s %s %s %s\n", entry.Mode, entry.Type, entry.Sha1, entry.Path))
	// }
	// content := bytes.Bytes()
	// header := fmt.Sprintf("%s %d\u0000", "tree", len(content))
	// data := append([]byte(header), content...)
	// objSha1 := sha1.Sum(data)

	// treeObject.Sha1 = fmt.Sprintf("%x", objSha1)
	// writeObject(treeObject.Sha1, data)

	// write tree object to object database
	objSha1, data := getSha1AndRawData(&treeObject)
	treeObject.Sha1 = objSha1

	writeObject(objSha1, data)
	return &treeObject
}
