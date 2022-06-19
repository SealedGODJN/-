package main

import (
	"flag"
)

func main() {
	flag.Parse()

	var command = flag.Arg(0)
	// args := flag.Args()[1:]

	switch command {
	case "init":
		Init(flag.Arg(1))
	}
}
