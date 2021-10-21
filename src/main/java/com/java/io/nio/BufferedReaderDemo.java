package com.java.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferedReaderDemo {
    public static void main(String[] args) {
        Path file = null;
        BufferedReader bufferedReader = null;
        String relativelyPath = System.getProperty("user.dir");
        try {
            file = Paths.get(relativelyPath + "/testout.txt");
            InputStream inputStream = Files.newInputStream(file);

            bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));
            System.out.println("Reading the Line of testout.txt file: \n" + bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
