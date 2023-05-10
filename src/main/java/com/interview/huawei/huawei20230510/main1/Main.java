package com.interview.huawei.huawei20230510.main1;

import java.math.BigInteger;
import java.util.*;

public class Main {
    // 1111111111111 1111111111111 1111111111111 3333333333333
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String[] numsString = line.split(" ");
        BigInteger[] nums = new BigInteger[numsString.length];
        for (int i = 0; i < numsString.length; i++) {
            nums[i] = new BigInteger(numsString[i]);
        }
        Map<Integer, BigInteger> sumMap = new HashMap<>();
        BigInteger sum = new BigInteger("0");
        BigInteger n1 = new BigInteger("0");
        Stack<BigInteger> stack = new Stack<>();
        int length = 0;
        while (length != nums.length) {
            boolean q2 = false;
            if (!stack.isEmpty() && nums[length].equals(n1)) {
                // 出栈
                BigInteger tmp = stack.pop();
                sum = sum.subtract(tmp);
                tmp = tmp.multiply(new BigInteger("2"));
                stack.push(tmp);
                // 每次Push
                n1 = tmp;
                sum = sum.add(tmp);
                sumMap.replace(length, sum);
                length++;
                continue;
            }
            for (Integer i : sumMap.keySet()) {
                if (sumMap.get(i).equals(nums[length])) {
                    q2 = true;
                    int j = i;
                    while (j != 0) {
                        BigInteger tmp = stack.pop();
                        sum = sum.subtract(tmp);
                        j--;
                    }
                    BigInteger temp = nums[length].multiply(new BigInteger("2"));
                    stack.push(temp);
                    // 每次Push
                    n1 = temp;
                    sum = sum.add(temp);
                    for (int k = length; k > length - i + 1; k--) {
                        sumMap.remove(k);
                    }
                    sumMap.replace(length - i + 1, sum);
                    length++;
                    break;
                }
            }
            if (!q2) {
                stack.push(nums[length]);
                // 每次Push
                n1 = nums[length];
                sum = sum.add(nums[length]);
                sumMap.put(stack.size(), sum);
                length++;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
