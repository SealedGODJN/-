package com.hjn.huawei;

import java.util.*;

/**
 * 华为机试测试
 * @author HJN
 *
 */
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String array = sc.nextLine();
        char[] line = array.toCharArray();
        ArrayList<Character> fuck = new ArrayList<Character>();
        int check = 0;
        for(int i=0; i<line.length; i++) {
            for(Character c:fuck) {
                if(line[i] == c) check = 1;
            }
            if(check == 0){
                Character temp = new Character(line[i]);
                fuck.add(temp);
            }
        }
        for(Character c:fuck) {
            System.out.print(c);
        }
    }
}

//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Main {
////  public static void main(String[] args) {
////      Scanner sc = new Scanner(System.in);
////      String line = sc.nextLine();
////      char[] c = line.toCharArray();
//////      for(char i:c) {
//////          int temp = (int) i;
//////          System.out.println(temp);
//////      }
////      ArrayList<Integer> num = new ArrayList<Integer>();
////      for(char i:c) {
////          int temp = (int) i;
////          if(temp>=48 && temp <=57) num.add(temp-48);
////      }
////      for(int i=0; i<num.size(); i++) {
////          for(int j=i; j<num.size(); j++) {
////              if(num.get(i) >= num.get(j)) {
////                  Integer temp = num.get(i);
////                  num.set(i,num.get(j));
////                  num.set(j,temp);
////              }
////          }
////      }
////      for(Integer i:num) {
////          System.out.print(i);
////      }
////  }
//  public static int state = 0;
//  public static int t_length = 0;
//  public static int isRecord_5a = 0;
//  public static ArrayList<String> result_all =  new ArrayList<String>();
//  public static ArrayList<String> result = new ArrayList<String>();
//
//  /**
//   * 字符变数字
//   * @param a1
//   * @return
//   */
//  public static int change(int a1) {
//      if(a1 >= 48 && a1 <= 57) {
//          a1 = a1 - 48;
//      } else {
//          a1 = a1 - 97 + 10;
//      }
//      return a1;
//  }
//
//  /**
//   * 16进制——10进制
//   * @param a
//   * @param b
//   * @return
//   */
//  public static int changeTo10(char a,char b) {
//      int a1 = change((int)a);
//      int b1 = change((int)b);
//      int total = a1*16 + b1;
//      return total;
//  }
//
//  public static boolean check_5a(char[] c) {
//      if(c[0] == '5' && c[1] == 'a') {
//          return true;
//      }
//      else return false;
//  }
//
//  public static void main(String[] args) {
//      Scanner sc = new Scanner(System.in);
//      String line = sc.nextLine();
//      String[] token = line.split(" ");
//      for(int i=0; i<token.length; i++) {
//          char[] c = token[i].toCharArray();
////          int num = changeTo10(c[0],c[1]);
////          System.out.println(num);
//          switch (state) {
//              case 0:
//                  if(check_5a(c)) {
//                      state = 1;
//                      if(isRecord_5a == 0)
//                          result.add(token[i]);
//                  }
//                  break;
//              case 1:
//                  if(t_length == 0) {
//                      if(c[0] == '5' && c[1] == 'b') {
//                          result.add(token[i]);
//                          i++; // 跳过下一个
//                          result.add(token[i]);
//                          t_length +=1;
////                          char[] c_next = token[i].toCharArray();
////                          if(c_next[0] == 'b' && c_next[1] == 'a') {
////
////                          } else
//                      } else if(c[0] == '0' && c[1] == '0') {
//                          result.add(token[i]);
//                          i++;
//                          char[] c_next = token[i].toCharArray();
//                          if(check_5a(c_next)) { // 报文终结
//                              result.add(token[i]);
//                              isRecord_5a = 1;
//                              t_length = 0; // 置0
//                              state = 0; // 置0
//                              for(String i_1:result) {
//                                  result_all.add(i_1); // 添加到总结果中
//                              }
//                              result.clear(); // 清空
//                          }
//                          i--;
//                      } else {
//                          result.add(token[i]);
//                          t_length+=1;
//                      }
//                  } else if(t_length != 0) {
//                      result.add(token[i]);
//                      i++;
//                      char[] c_next = token[i].toCharArray();
//                      if(check_5a(c_next)) {
//                          int currentL = changeTo10(c[0],c[1]);
//                          if(currentL == t_length) {
//                              result.add(token[i]);
//                              isRecord_5a = 1;
//                              t_length = 0; // 置0
//                              state = 0; // 置0
//                              for(String i_1:result) {
//                                  result_all.add(i_1); // 添加到总结果中
//                              }
//                              result.clear(); // 清空
//                          } else { // 报文长度出错
//                              for(int i_3 = result.size() -1; i_3>0; i_3--) { // 保留5a
//                                  result.remove(i_3);
//                              }
//                              isRecord_5a = 1;
//                              t_length = 0; // 置0
//                              state = 0; // 置0
//                          }
//                          i--;
//                      } else { // 报文非终结
//                          if(c[0] == '5' && c[1] == 'b') {
//                              result.add(token[i]);
//                              t_length += 1;
//                          } else {
//                              i--;
//                              t_length+=1;
//                          }
//                      }
////                      if(c[0] == '5' && c[1] == 'b') {
////                          result.add(token[i]);
////                          i++; // 跳过下一个
////                          result.add(token[i]);
////                          t_length +=1;
////      //                            char[] c_next = token[i].toCharArray();
////      //                            if(c_next[0] == 'b' && c_next[1] == 'a') {
////      //
////      //                            } else
////                      } else {
////                          int currentL = changeTo10(c[0],c[1]);
////                          if(currentL == t_length) {
////                              result.add(token[i]);
////                              i++;
////                              char[] c_next = token[i].toCharArray();
////                              if(check_5a(c_next)) { // 报文终结
////                                  result.add(token[i]);
////                                  isRecord_5a = 1;
////                                  t_length = 0; // 置0
////                                  state = 0; // 置0
////                                  for(String i_1:result) {
////                                      result_all.add(i_1); // 添加到总结果中
////                                  }
////                              }
////                              i--;
////                          } else {
////                                  result.add(token[i]);
////                                  i++;
////                                  char[] c_next = token[i].toCharArray();
////                                  if(check_5a(c_next)) { // 报文终结
////                                  for(int i_3 = result.size() -1; i_3>0; i_3++) {
////                                      result.remove(i_3);
////                                  }
////                              }
////                          }
////                          result.add(token[i]);
////                          t_length+=1;
////                      }
//                  }
//          }
//      }
//      for(int i = 0; i< result_all.size(); i++) {
//          if(i != result_all.size() -1)
//              System.out.print(result_all.get(i)+" ");
//          else System.out.print(result_all.get(i));
//      }
//  }
//}
