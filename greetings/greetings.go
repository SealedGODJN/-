package greetings

import (
	"errors"
	"fmt"
	"math/rand"
	"time"
)

// Hello returns a greeting for the named person.
func Hello(name string) (string, error) {
	// If no name was given, return an error with a message
	if name == "" {
		return "", errors.New("empty name")
	}
	// Return a greeting that embeds the name in a message.
	// message := fmt.Sprintf("Hi, %v. Welcome!", name)
	message := fmt.Sprintf(randomFormat(), name)
	return message, nil
}

// init sets initial values for variables used in the function
func init() {
	rand.Seed(time.Now().UnixNano())
}

// randomFormat returns one of a set of greeting messages. The returned
// message is selected at random
func randomFormat() string {
	// A slice of message formats.
	formats := []string{ // the size of the array underlying the slice can be dynamically changed.
		"Hi, %v. Welcome",
		"Great to see you, %v!",
		"Hail, %v! Well met!",
	}
	// A slice is a descriptor of an array segment. It consists of a pointer to the array, the length of the segment, and its capacity (the maximum length of the segment).
	// Slicing does not copy the slice’s data. It creates a new slice value that points to the original array.
	/*
		d := []byte{'r', 'o', 'a', 'd'}
		e := d[2:]
		// e == []byte{'a', 'd'}
		e[1] = 'm'
		// e == []byte{'a', 'm'}
		// d == []byte{'r', 'o', 'a', 'm'}
	*/

	// Return a randomly selected message format by specifying
	// a random index for the slice of formats
	return formats[rand.Intn(len(formats))]
}
