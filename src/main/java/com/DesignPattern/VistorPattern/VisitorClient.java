package com.DesignPattern.VistorPattern;

/**
 * 准确识别出Visitor实用的场景，如果一个对象结构不稳定决不可使用，不然在增删元素时改动将非常巨大。
 * 对象结构中的元素要可以迭代访问
 */
public class VisitorClient {

    public void startProject(){
        BigHuYouCompany bigHuYou= new BigHuYouCompany();
        //可以很轻松的更换Visitor，但是要求BigHuYouCompany的结构稳定
        System.out.println("-----------------启动社交APP项目--------------------");
        bigHuYou.startProject(new SocialApp());
        System.out.println("-----------------启动短视频APP项目--------------------");
        bigHuYou.startProject(new LiveApp());
    }
}
