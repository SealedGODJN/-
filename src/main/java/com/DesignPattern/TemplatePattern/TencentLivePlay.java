package com.DesignPattern.TemplatePattern;

//腾讯直播类
public class TencentLivePlay extends LivePlay  {
    @Override
    public void openRoom() {
        System.out.println("腾讯打开房间");
    }

    @Override
    public void startAudioAndVideoStream() {
        System.out.println("腾讯打开音视频流");
    }

    @Override
    public void stopAudioAndVideoStream() {
        System.out.println("腾讯关闭音视频流");
    }

    @Override
    public void closeRoom() {
        System.out.println("腾讯关闭房间");
    }

    //覆写钩子方法，提供旁路推流功能
    @Override
    public void pushVideoStream() {
        super.pushVideoStream();
        System.out.println("腾讯进行旁路推流");
    }
}
