package com.how2j.jdbc;

import java.sql.*;
import java.util.Iterator;
import java.util.Scanner;

public class TestJDBC {
    public static void main(String[] args) {
//        Connection c = null;
//        Statement s = null;
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("数据库驱动加载成功");
//
//            c = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/coursesupport?characterEncoding=utf-8",
//                    "root", "coursesupport"
//            );
//
//            System.out.println("获取连接对象" + c);
//
//            s = c.createStatement();
//            System.out.println("获取Statement对象" + s);
//
////            String sql = "INSERT INTO assistant_course VALUES(1,1,1,20110101)";
//            String sql = "SELECT * FROM assistant_course";
//            s.execute(sql);
//            ResultSet result =  s.executeQuery(sql);
//
//            System.out.println("插入语句执行成功");
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
////            if(s!=null) {
////                try{
////                    s.close();
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
////            if(c!=null) {
////                try{
////                    c.close();
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
//        }


//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        //try-with-resources语句是一种声明了一种或多种资源的try语句。
//        // 资源是指在程序用完了之后必须要关闭的对象。
//        // try-with-resources语句保证了每个声明了的资源在语句结束的时候都会被关闭。
//        // 任何实现了java.lang.AutoCloseable接口的对象，和实现了java.io.Closeable接口的对象，都可以当做资源使用。
//        try (
//                Connection c = DriverManager.getConnection(
//                        "jdbc:mysql://127.0.0.1:3306/coursesupport?characterEncoding=UTF-8&useSSL=false",
//                        "root", "coursesupport"
//                );
//                Statement s = c.createStatement();
//        )
//        {
//            String sql = "SELECT * FROM assistant_course";
//            ResultSet resultSet = s.executeQuery(sql);
//            while(resultSet.next()) {
//                int id = resultSet.getInt("id");
//                int course_id = resultSet.getInt("course_id");
//                int assistant_id = resultSet.getInt("assistant_id");
//                String join_date = resultSet.getString("join_date");
//                System.out.printf("%d \t %d \t %d \t %s \t", id, course_id, assistant_id, join_date);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //try-with-resources语句是一种声明了一种或多种资源的try语句。
        // 资源是指在程序用完了之后必须要关闭的对象。
        // try-with-resources语句保证了每个声明了的资源在语句结束的时候都会被关闭。
        // 任何实现了java.lang.AutoCloseable接口的对象，和实现了java.io.Closeable接口的对象，都可以当做资源使用。
        try (
                Connection c = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/coursesupport?characterEncoding=UTF-8&useSSL=false",
                        "root", "coursesupport"
                );
                Statement s = c.createStatement();
        ) {
//            int N = 10; // 插入10条数据
//            insertNData(c, N);

            deleteTop10(c);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void insertNData(Connection c, int N) throws SQLException {
        c.setAutoCommit(false);
        String sql = "INSERT INTO assistant_course(id,course_id,assistant_id,join_date) values(null,?,?,20110202)";
        PreparedStatement ps = c.prepareStatement(sql);
        int i = 0;
        for(; i < N; i++) {
            ps.setInt(1,i);
            ps.setInt(2,i);
            ps.execute();
        }
        System.out.println("插入数据成功");
    }

    private static void deleteTop10(Connection c) throws SQLException {
        String query = "SELECT id FROM assistant_course limit 10";
        PreparedStatement ps = c.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        c.setAutoCommit(false);
        String sql = "DELETE FROM assistant_course where id = ?";
        ps = c.prepareStatement(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            ps.setInt(1, id);
            ps.execute();
            System.out.printf("试图删除id= %d 的数据\n",id);
        }


//        String sql = "DELETE FROM assistant_course limit 10";
//        ps = c.prepareStatement(sql);
//        ps.execute();

        System.out.println("请确认是否要删除这些数据？(Y/N))");
        Scanner sc = new Scanner(System.in);
        String check = sc.nextLine();
        if(check.equals("Y")) {
            c.commit();
            System.out.println("提交删除");
        } else c.rollback(); // 回滚操作？
    }
}
