package main

import (
	"bytes"
	"compress/zlib"
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"path/filepath"
	"strings"
)

func CatFile(p bool, t bool, s bool, args []string) {
	// -p参数：查询文件内容
	// -t参数：查询文件类型
	if !p {
		fmt.Printf("%s\n", getCatFileStr(p, t, s, args))
	} else {
		// maybe there is a \n at the last of raw, so we don't need to add \n ? (+_+)...
		fmt.Printf("%s", getCatFileStr(p, t, s, args))
	}
}

func getCatFileStr(p bool, t bool, s bool, args []string) string {
	if !p && !t && !s {
		log.Fatal("-s or -p or -t is needed!")
	}

	objectId := args[len(args)-1]
	objectDir := filepath.Join(".git", "objects", objectId[:2])

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
			// 读取该文件名对应的文件
			data, err = ioutil.ReadFile(filepath.Join(objectDir, file.Name()))
			if err != nil {
				log.Fatal(err)
			}
		}
	}

	// 解压缩object文件
	raw := unCompressData(data)
	i := bytes.IndexByte(raw, ' ')
	j := bytes.IndexByte(raw, '\u0000')

	// 参数-t：show object type
	if t {
		// raw的格式为: blob len(data)+\u0000+data
		objectType := raw[:i]
		return string(objectType)
	} else if s {
		// 参数-s：show object size
		objectSize := raw[i+1 : j]
		return string(objectSize)
	} else if p {
		objectContent := raw[j+1:]
		// 文件数据的最后可能存在换行符，因此，此处fmt.Printf不需要输出\n
		return string(objectContent)
	}
	return ""
}

// 原本位于catfile中的第三步：解压缩文件
func unCompressData(data []byte) []byte {
	// 由于读取到的文件是经过hash-object命令压缩过的
	// 因此，需要解压缩该文件
	reader := bytes.NewReader(data)
	r, err := zlib.NewReader(reader)
	if err != nil {
		log.Fatal(err)
	}

	var out bytes.Buffer
	io.Copy(&out, r)

	raw := out.Bytes()
	return raw
}
