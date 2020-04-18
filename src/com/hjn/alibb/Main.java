package com.hjn.alibb;
//
//import java.util.Scanner;
//import java.util.ArrayList;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        ArrayList<Integer> lines = new ArrayList<Integer>(10);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//        	for(int i = 0; i<10; i++) {
//        		if(in.nextInt() == 65) {
//        			lines.add(i,1);
//        		}
//        		else lines.add(i, in.nextInt());
//        	}
//        	
//        	for(int i=0;i<10;i++) {
//        		for(int j=i+1;j<10;j++) {
//        			int initial = lines.get(i);
//        			if(initial>lines.get(j)) {
//        				int temp = lines.get(j);
//        				lines.add(j, initial);
//        				lines.add(i,temp);
//        			}
//        		}
//        	}
//        	
//            System.out.println();
//        }
//    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        String x;
//        ArrayList<Integer> record = new ArrayList<Integer>(n); // 记录每一行的最大值
//        ArrayList<String> lines = new ArrayList<String>();
//        for(int i = 0; i <= n; i++){
//            x = sc.nextLine();
//            lines.add(i,x);
////            record.add(i,Integer.parseInt(x));
//        }
//        for(int i = 0; i<= n; i++) {
//        	System.out.println(lines.get(i));
////        	System.out.println(record.get(i));
//        }
//        System.out.println();
//    }
//	
//}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        float N = in.nextFloat();
        float L = in.nextFloat();
        float i=L;
        while(i<=100) {
            float a = (2*N-(i-1)*i)/(2*i);
            float sum = 0;
            float[] record = new float[(int)i];
            if(a<=0) continue;
            if(Math.ceil(a) == a) {
                for(int j=1; j<=i-1; j++) {
                	sum+=a;
                	record[j-1] = a;
//                    System.out.print((int)a+" ");
                    a += 1;
                }
                sum+=a;
                record[record.length-1]=a;
//                System.out.println((int)a);
                if(sum == N) {
                	for(int k=0; k<i; k++) {
                		if(k+1==i) System.out.println((int)record[k]);
                		else System.out.print((int)record[k]+" ");
                	}
                	System.out.println(record.length);
                	break;
                }
            }
            i+=1;
        }
        if(i==101) System.out.println("No");
    }	
}