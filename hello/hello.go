package main

// In Go, code executed as an application must be in a main package.

import (
	"fmt"
	"log"

	"example.com/greetings"
)

func main() {
	// Set properties of the predefined Logger, including
	// the log entry prefix and a flag to disable printing
	// the time, source file, and line number.
	log.SetPrefix("greetings: ")
	log.SetFlags(0)
	/*
		greetings: empty name
		exit status 1
	*/
	// log.SetFlags(1)
	/*
		greetings: 2022/06/01 empty name
		exit status 1
	*/

	// A slice of names.
	names := []string{"Gladys", "Samantha", "Darrin"}

	// Request a greeting message
	// message, err := greetings.Hello("hjn")
	message, err := greetings.Hellos(names)
	// If an error was returned, print it to the console and
	// exit the program
	if err != nil {
		log.Fatal(err)
	}

	// Get a greeting message and print it.
	// message := greetings.Hello("Gladys")
	fmt.Println(message)
}
