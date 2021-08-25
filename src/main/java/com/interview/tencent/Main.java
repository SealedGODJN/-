package com.interview.tencent;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt(); // N组测试数据
//        ArrayList<Integer> Q = new ArrayList<Integer>();
//        String[][] actions = new String[N][];
//        for(int i = 0; i<N; i++) {
//            int temp = sc.nextInt();
//            Q.add(temp);
//            String[] action_temp = new String[temp];
//            String zero = sc.nextLine(); // nextInt()没有到行的结束符
//            for(int j=0; j<Q.get(i); j++) {
//                action_temp[j] = sc.nextLine();
//            }
//            actions[i] = action_temp;
//        }
//        for(int i=0; i<N; i++) {
//            ArrayList<String> queue = new ArrayList<String>(); // 模拟队列
//            for(int j=0; j<Q.get(i); j++) {
////                System.out.println(actions[i][j]);
//                String[] cmd = actions[i][j].split(" ");
//                if(cmd[0].equals("PUSH")) {
//                    queue.add(cmd[1]);
//                } else if(cmd[0].equals("TOP")) {
//                    if(queue.size() != 0)
//                        System.out.println(queue.get(0));
//                    else System.out.println("-1");
//                } else if(cmd[0].equals("POP")) {
//                    if(queue.size() != 0)
//                        queue.remove(0);
//                    else System.out.println("-1");
//                } else if(cmd[0].equals("SIZE")) {
//                    System.out.println(queue.size());
//                } else if(cmd[0].equals("CLEAR")) {
//                    queue.clear();
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
//        int a = 2;
//        double b = 0.5;
//        double result = Math.pow(a, b);
//        System.out.println(result);
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N组测试数据
        ArrayList<Integer> Q = new ArrayList<Integer>();
        ArrayList<Integer> A_1 = new ArrayList<Integer>();
        ArrayList<Integer> A_2 = new ArrayList<Integer>();
        ArrayList<Integer> B_1 = new ArrayList<Integer>();
        ArrayList<Integer> B_2 = new ArrayList<Integer>();
        for(int i = 0; i<N; i++) {
            int temp = sc.nextInt();
            Q.add(temp);
            for(int j=0; j<Q.get(i); j++) { // 输入A
                int a1 = sc.nextInt();
                int a2 = sc.nextInt();
                A_1.add(a1);
                A_2.add(a2);
            }
            for(int j=0; j<Q.get(i); j++) { // 输入B
                int b1 = sc.nextInt();
                int b2 = sc.nextInt();
                B_1.add(b1);
                B_2.add(b2);
            }
        }
        for(int i=0; i<N; i++) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            ArrayList<Integer> b = new ArrayList<Integer>();
            ArrayList<Integer> a_b = new ArrayList<Integer>();
            for(int j=0; j<Q.get(i); j++) {
//                System.out.print(A_1.get((i+1)*j)+" ");
//                System.out.println(A_2.get((i+1)*j));
                int a_temp = A_1.get((i+1)*j) + A_2.get((i+1)*j);
                a.add(a_temp);
            }
            for(int j=0; j<Q.get(i); j++) {
//                System.out.print(B_1.get((i+1)*j)+" ");
//                System.out.println(B_2.get((i+1)*j));
                int b_temp = B_1.get((i+1)*j) + B_2.get((i+1)*j);
                b.add(b_temp);
            }
            int min_a_b = Integer.MAX_VALUE;
            int min_A = -1;
            int min_B = -1;
            for(int j=0; j<Q.get(i); j++) {
                for(int k=0; k<Q.get(i); k++) {
                    int temp = Math.abs(a.get(j) - b.get(k));
                    if(temp < min_a_b) {
                        min_a_b = temp;
                        min_A = j;
                        min_B = k;
                    }
                }
            }
            double x = Math.pow(A_1.get((i+1)*min_A) - B_1.get((i+1)*min_B),2);
            double y = Math.pow(A_2.get((i+1)*min_A) - B_2.get((i+1)*min_B),2);
            double distance = Math.pow(x+y, 0.5);
            System.out.printf("%.3f",distance);
//            String result = Double.toString(distance);
//            char[] array = result.toCharArray();
//            int point = 0;
//            for(int m=0; m<array.length; m++) {
//                if(array[m] == '.') {
//                    point = m;
//                }
//            }
//            if((point + 3) < array.length) {
//                if((int)array[point+4] >= 53) {
//                    if((int)array[point+3] == 57) {
//                        if(array[point+2] == 57) {
//                            if(array[point+1] == 57) {
//                                array[point-1] +=1;
//                            } else {
//                                array[point+1] += 1;
//                            }
//                        } else {
//                            array[point+2] += 1;
//                        }
//                    } else {
//                        array[point+3] += 1;
//                    }
//                }
//                for(int m=0; m<= (point +3); m++) {
//                    System.out.print(array[m]);
//                }
//                System.out.println();
//            } else {
//                for(int m=0; m< array.length; m++) {
//                    System.out.print(array[m]);
//                }
//                for(int m=0; m<= (point+3-array.length); m++) {
//                    System.out.print("0");
//                }
//                System.out.println();
//            }
        }
    }
}
