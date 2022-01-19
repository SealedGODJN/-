package com.java_Interview_Reference.hashcode_2;

import com.alibaba.excel.EasyExcel;
import com.how2j.java.interfaceAndExtend.Test;
import com.java_Interview_Reference.hashcode_1.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class TestEasyExcel {
    //private static final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);// slf4j日志记录器

    /**
     * slf4j日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(TestEasyExcel.class.getName());

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String fileName = System.getProperty("user.dir") + "testBeanMap_EasyExcel.xlsx";
        EasyExcel.write(fileName).sheet("sheet1").head(head()).doWrite(dataList());
    }

    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("姓名");
        List<String> head1 = new ArrayList<>();
        head1.add("年龄");
        List<String> head2 = new ArrayList<>();
        head2.add("生日");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private static List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<>();
            data.add("张三");
            data.add(25);
            data.add(new Date());
            list.add(data);
        }
        return list;
    }
}


//        Set<String> words;
//        words = FileUtil.readWordList(System.getProperty("user.dir")+"\\java面经-小傅哥\\103976个英语单词库.txt");
////
////        Map<Integer, Integer> map = new HashMap<>(16);
////        for(String word : words) {
////            // 使用扰动函数
////            // size 应该小于原数组长度
//////            int idx = Disturb.disturbHashIdx(word, 128);
//////            int idx = Disturb.disturbHashIdx(word, words.size());
////            // 不适用扰动函数
////            int idx = Disturb.hashIdx(word, 128);
//////            int idx = Disturb.hashIdx(word, words.size());
////            if (map.containsKey(idx)) {
////                Integer integer = map.get(idx);
////                // 记录每个key对应的string,观察扰动函数的效果
////                map.put(idx, integer+1);
////            } else {
////                map.put(idx, 1);
////            }
////        }
////
////        List<List<Integer>> result = new ArrayList<>();
////
////        for(Integer i: map.values()) {
////            List<Integer> disturb = new ArrayList<>();
////            disturb.add(i);
////            result.add(disturb);
////        }
//
////        String fileName = "D:\\IDEA_PROGECT\\helloworld\\java面经-小傅哥\\" + "disturb.xlsx";
//        String fileName = System.getProperty("user.dir") + "\\java面经-小傅哥\\disturb.xlsx"; // 在工作目录下生产excel
//        EasyExcel.write(fileName).sheet("sheet1").doWrite(Arrays.asList(words));
//
////        String fileName = "D:\\IDEA_PROGECT\\helloworld\\java面经-小傅哥\\" + "disturb1.xlsx";
////        EasyExcel.write(fileName).sheet("sheet1").doWrite(result);
