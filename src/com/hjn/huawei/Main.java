package com.hjn.huawei;

import java.util.*;

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