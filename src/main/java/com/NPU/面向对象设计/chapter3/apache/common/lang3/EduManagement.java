package com.NPU.面向对象设计.chapter3.apache.common.lang3;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EduManagement {
    private List<Student> studentList; // 教育管理部门管理所有的学生

    public EduManagement() {
        // 初始化学生数组
        this.studentList = new ArrayList<>();

        // 添加10个学生
        Student a = new Student("学生1", "2021669911");
        Student b = new Student("学生2", "2021669912");
        Student c = new Student("学生3", "2021669913");
        Student d = new Student("学生4", "2021669914");
        Student e = new Student("学生5", "2021669915");
        Student f = new Student("学生6", "2021669916");
        Student g = new Student("学生7", "2021669917");
        Student h = new Student("学生8", "2021669918");
        Student i = new Student("学生9", "2021669919");
        Student j = new Student("学生10", "2021669920");
        studentList.add(a);
        studentList.add(b);
        studentList.add(c);
        studentList.add(d);
        studentList.add(e);
        studentList.add(f);
        studentList.add(g);
        studentList.add(h);
        studentList.add(i);
        studentList.add(j);
    }

    private void deleteStudent(String id) {
        Student delete = null;
        for (Student student : studentList) {
            if (ReflectDemo.getStudentId(student).equals(id)) {
                delete = student; // 记录待删除的学生
                break;
            }
        }
        if (delete != null) {
            studentList.remove(delete);
            System.out.println("已删除学生：" + id);
        } else {
            System.out.println("未找到学生：" + id);
        }
    }

    /**
     * 随机生成一个字符串，若学生的学号尾号匹配，则让该学生参加论文盲审，将该学生id打印出来
     */
    public void concealedEvaluation() {
        String randomChar = RandomStringUtils.random(1, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Student> studentList = this.getStudentList();

        System.out.println("需要盲审的学生");
        for (Student student : studentList) {
            String id = ReflectDemo.getStudentId(student);
            if (id.substring(9).equals(randomChar)) {
                // 打印需要盲审的学生
                System.out.println(id);
            }
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void readAllStudentEnglishScore() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ListValuedMap<String, String> studentEnglishTestScore = new ArrayListValuedHashMap<>();

        System.out.println("按Enter键可以停止导入英语成绩");
        while (true) {
            String line = br.readLine();
            if (line.equals("")) {
                break;
            }
            String[] elements = line.split(" ");
            String studentId = elements[0];
            for (int i = 1; i < elements.length; i++) {
                studentEnglishTestScore.put(studentId, elements[i]);
            }
            System.out.println("已添加：" + studentId + "的英语六级成绩");
        }
    }

    public static void main(String[] args) throws IOException {
        EduManagement eduManagement = new EduManagement();
//        eduManagement.concealedEvaluation();
        eduManagement.readAllStudentEnglishScore();
    }
}
