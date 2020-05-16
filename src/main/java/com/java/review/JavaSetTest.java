package com.java.review;

import java.util.HashSet;
 
class cat{
	String name;
	public cat(String name) {
		this.name = name;
	}
	public int hashCode() {
		System.out.println("执行hashCode!");
		return 10;
	}
	public boolean equals(Object obj) {
		System.out.println("执行了equals");
		return true;
	}
}

public class JavaSetTest {
	public static void main(String[] args) {
		//hashcode每一个对象都会有一个hashcode值
		cat c1 = new cat("白猫");
		cat c2 = new cat("黑猫");
		HashSet<cat> s = new HashSet<cat>();
		//1、添加一个对象时，会调用对象的hashcode
		//hashcode不同时，c2对象和集合中的hashcode不相同，就不会调用equals
		s.add(c1);
		s.add(c2);
		//System.out.println(c1.hashCode());
		//System.out.println(c1.hashCode());
		//当hashcode相同时，会调用equals
		System.out.println(s);//如果equals返回的是true时，将不会添加到集合内
		
		
	}
 
}