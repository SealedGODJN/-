package com.scala.WordCount

import java.io.File
import scala.io.Source

object WordCount {
  def main(args: Array[String]): Unit = {
    // 可以用java的类
    val dirfile = new File("/Users/hjn/Desktop/wordcount")
    // 调用java类的方法时，要遵循scala的语法
    val files = dirfile.listFiles
    for (file <- files) println(file)
    val listFiles = files.toList
    val wordsMap = scala.collection.mutable.Map[String, Int]()

    // 匿名函数
    listFiles.foreach(
      file =>
        Source.fromFile(file).getLines().foreach(
          line => line.split(" ").foreach(
            word => {
              if (wordsMap.contains(word)) {
                wordsMap(word) += 1
              } else {
                wordsMap += (word -> 1)
              }
            }
          )
        )
    )
    println(wordsMap)
    println(wordsMap)
    println(wordsMap)
    for ((key, value) <- wordsMap) println(key + ":" + value)
  }
}
