package main

import (
	"bytes"
	"compress/zlib"
	"crypto/sha1"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
)

func HashObject(t string, w bool, args []string) {
	path := args[len(args)-1]

	// 使用sha1来计算data的sha1值
	// idStr即为data的sha1值
	idStr := getSha1Str(path, t)
	fmt.Printf("%s\n", idStr)

	data := getData(path, t)

	writeObject(idStr, data)
}

func getContent(path string) []byte {
	file, err := ioutil.ReadFile(path)
	if err != nil {
		log.Fatal(err)
	}
	return file
}

func getSha1Str(path string, t string) string {
	// 使用sha1来计算data的sha1值
	// id := sha1.Sum(data)
	// idStr := fmt.Sprintf("%x", id)
	// fmt.Printf("%s\n", idStr)
	data := getData(path, t)
	id := sha1.Sum(data)
	return fmt.Sprintf("%x", id)
}

func getData(path string, t string) []byte {
	context := getContent(path)
	// 首先要构造头部信息，头部信息由对象类型，一个空格，数据内容的字节数，一个空字节拼接而成，格式是这样：
	// blob 9\u0000
	header := fmt.Sprintf("%s %d\u0000", t, len(context))

	// 然后把头部信息和原始数据拼接起来，格式是这样：
	// blob 9\u0000version1
	data := append([]byte(header), context...)
	return data
}

func writeObject(idStr string, data []byte) {
	// 取sha1值的前两位为前缀，作为文件夹名
	// 除前两位以外，其余部分作为object文件名
	//write into object database
	prefix := idStr[:2]
	postfix := idStr[2:]

	// 设置文件夹的路径.git/objects/idStr[:2]
	objectDir := filepath.Join(".git", "objects", prefix)
	err := os.MkdirAll(objectDir, os.ModePerm)
	// 判断创建文件夹是否出错
	if err != nil {
		log.Fatal(err)
	}

	// 设置object文件的路径
	objectFile := filepath.Join(objectDir, postfix)
	file, err := os.Create(objectFile)
	// 判断创建文件是否出错
	if err != nil {
		log.Fatal(err)
	}

	// compress with zlib
	compressedData := compress(data)
	// file.Write(b.Bytes())
	file.Write(compressedData)
}

func compress(raw []byte) []byte {
	// 用 zlib 把上面拼接好的信息进行压缩，然后存进 object 文件中
	// compress with zlib
	var b bytes.Buffer
	// NewWriter可以接受io.Writer
	writer := zlib.NewWriter(&b)
	// writer.Write(data)
	writer.Write(raw)
	writer.Close()
	return b.Bytes()
}
