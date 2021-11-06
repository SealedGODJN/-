package com.DesignPattern.TemplatePattern;

//金山直播类
public class JinShanLivePlay extends LivePlay  {
    @Override
    public void openRoom() {
        System.out.println("金山打开房间");
    }

    @Override
    public void startAudioAndVideoStream() {
        System.out.println("金山打开音视频流");
    }

    @Override
    public void stopAudioAndVideoStream() {
        System.out.println("金山关闭音视频流");
    }

    @Override
    public void closeRoom() {
        System.out.println("金山关闭房间");
    }
}
