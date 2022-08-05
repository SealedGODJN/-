package main

import (
	"flag"
	"os"
)

func main() {
	// 取第二个参数
	var command = os.Args[1]
	os.Args = os.Args[1:] // 获取第二个参数之后的所有参数

	switch command {
	case "init":
		flag.Parse()
		Init(flag.Arg(0))

	case "hash-object":
		w := flag.Bool("w", true, "write into object database")
		t := flag.String("t", "blob", "object type")
		flag.Parse()
		HashObject(*t, *w, flag.Args())

	case "cat-file":
		// 参数默认都为false
		p := flag.Bool("p", false, "print object content")
		t := flag.Bool("t", false, "show object type")
		s := flag.Bool("s", false, "show object size")

		flag.Parse()
		CatFile(*p, *t, *s, flag.Args())

	case "update-index":
		a := flag.Bool("add", false, "add file content to the index")
		flag.Parse()
		UpdateIndex(*a, flag.Args())

	case "ls-files":
		s := flag.Bool("stage", false, "Show staged contents' mode bits, object name and stage number")
		flag.Parse()
		LsFiles(*s)

	case "write-tree":
		WriteTree()
	case "comit-tree":
		treeObjSha1 := os.Args[1]
		os.Args = os.Args[1:]
		p := flag.String("p", "", "indicates the id of a parent commit object")
		m := flag.String("m", "", "the commit log message")
		flag.Parse()
		CommitTree(treeObjSha1, *p, *m, flag.Args())
	case "log":
		Log(os.Args)
	case "update-ref":
		UpdateRef(os.Args)
	case "symbolic-ref":
		SymbolicRef(os.Args)
	case "commit":
		m := flag.String("m", "", "commit message")
		flag.Parse()
		Commit(*m, flag.Args())
	}

}
