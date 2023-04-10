package com.scala.Array

// scala在每个应用程序中，都会自动添加一些引用
// import java.lang._

object ValAndVar {
  def main(args: Array[String]): Unit = {
    // val不可变，声明时必须被初始化
    val myStr = "Hello World"
    println(myStr)

    // 显式声明变量的类型
    val myStr2: String = "Hello World"
    println(myStr2)

    // String = java.lang.String
    val myStr3: java.lang.String = "Hello World"
    println(myStr3)

    //    myStr = "Hello Scala"

    // var可变
    var myPrice: Double = 9.0
    println("修改之前的myPrice: " + myPrice)
    myPrice = 10.6
    println("修改后的myPrice: " + myPrice)


  }
}
