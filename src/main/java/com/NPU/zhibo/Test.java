package com.NPU.zhibo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 对加密的直播源url进行还原，变成未加密的状态，
 * 但是斗鱼的加密形式很多，目前我只发现了这两种。后续如果再有新的发现，我会继续更新本工具。
 */
public class Test implements ActionListener {
    private JButton btn1;//开始解析按钮
    private JButton btn2;//退出程序按钮
    private TextField url1;//输入解析文本框
    private TextField result;//输出解析地址文本框

    //程序运行入口
    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        //界面设置
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("斗鱼直播源解析 by故事而已");
        frame.setLocation(700, 400);
        frame.setSize(400, 200);

        //生成容器
        Container con = frame.getContentPane();//生成容器
        con.setLayout(new GridLayout(3, 1));

        //版面设置
        JPanel pan1 = new JPanel();//生成一个新的版面
        JLabel urlStr = new JLabel("解析");
        pan1.add(urlStr);
        url1 = new TextField(40);
        url1.setText("在此输入需要解析的直播源地址");
        pan1.add(url1);
        con.add(pan1);

        JPanel pan2 = new JPanel();//版面2
        JLabel urlResult = new JLabel("解析结果");
        pan2.add(urlResult);
        result = new TextField(40);
        pan2.add(result);
        con.add(pan2);

        JPanel pan3 = new JPanel();//版面3
        btn1 = new JButton("开始解析");
        btn2 = new JButton("退出");
        btn1.addActionListener(this);//绑定监听
        btn2.addActionListener(this);//绑定监听
        pan3.add(btn1);
        pan3.add(btn2);
        con.add(pan3);
        //关闭窗口事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //按钮事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            method();//点击开始解析按钮,调用解析方法
        } else if (e.getSource() == btn2) {
            System.exit(1);//点击关闭按钮,退出程序
        }
    }

    //解析地址的方法
    private void method() {
        try {

            //x浏览器嗅探到直播源解析
            String url = url1.getText();
            if (url.startsWith("http://")) {
                //解析方案1
                String[] split = url.split("\\?");
                String sub = split[0].substring(split[0].indexOf("."));
                String source = "http://tx2play1" + sub + "?" + split[1].substring(split[1].indexOf("token"));
                source = source.replace("/playlist.m3u8", ".flv");
                //输出解析地址
                result.setText(source);
            }
            //chrome获取到的直播源解析
            if (url.startsWith("https://")) {
                //解析方案2
                String regex = "\\.flv";
                String[] split = url.split(regex);
                String part1 = split[0].substring(split[0].lastIndexOf("/"));
                String part2 = split[1].substring(split[1].indexOf("token"));
                String source = "https://tx2play1.douyucdn.cn/live" + part1 + ".flv?" + part2;
                result.setText(source);
            }
        } catch (Exception e) {
            result.setText("该地址暂时无法解析");//输出异常信息
        }
    }
}

