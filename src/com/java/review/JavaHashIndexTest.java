package com.java.review;

public class JavaHashIndexTest {
	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		long mod1 = 0;
		long times = 10000000000L;
		for(long i = 0; i < times; i++) {
			mod1 = 789789%1024;
		}
		long currentTime2 = System.currentTimeMillis();
		long mod2 = 0;
		for(long i = 0; i < times; i++) {
			mod2 = 789789&(1024-1);
		}
		long currentTime3 = System.currentTimeMillis();
		System.out.println("mod1="+mod1+" cost:"+(currentTime2-currentTime));
		System.out.println("mod2="+mod2+" cost:"+(currentTime3-currentTime2));
	}
}
