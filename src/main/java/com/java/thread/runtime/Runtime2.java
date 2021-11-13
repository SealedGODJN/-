package com.java.thread.runtime;

import java.io.IOException;

public class Runtime2 {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("shutdown -s -t 100");
    }
}
