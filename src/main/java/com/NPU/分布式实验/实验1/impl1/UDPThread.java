package com.NPU.分布式实验.实验1.impl1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;

/**
 * udp传输文件线程类
 *
 * @author liyong
 * @version 1.0
 */
public class UDPThread implements Runnable {

    /**
     * UDP socket类
     */
    private DatagramSocket socket = null;
    /**
     * UDP 数据包类
     */
    private DatagramPacket packet = null;

    /**
     * 请求发送文件绝对路径
     */
    private String filePath = null;

    /**
     * 构造方法，数据包内，包含请求文件全路径名称
     *
     * @param socket socket类
     * @param packet 数据包类
     */
    public UDPThread(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = new DatagramPacket(new byte[FTPServer.buffer_size], FTPServer.buffer_size, packet.getAddress(),
                packet.getPort());
        this.filePath = new String(packet.getData(), 0, packet.getLength()).trim();
    }

    @Override
    public void run() {
        try {
            // 获取客户端信息
            FTPServer.screen
                    .println("UDP " + packet.getAddress().toString() + ":" + packet.getPort() + " 发送请求文件:" + filePath);
            // 读取文件采用建议采用BufferedInputStream 效率较高
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[FTPServer.buffer_size];
            // 接受读取的内容(n就代表的相关数据，只不过是数字的形式)
            int n = -1, i = 0;
            // 循环取出数据
            while ((n = in.read(buffer, 0, buffer.length)) != -1) {
                packet.setData(buffer, 0, buffer.length);
                socket.send(packet); // 回复数据
                TimeUnit.MICROSECONDS.sleep(100);// 确保发送的顺序，根据机器的配置，如果客户端发生丢包，可增大此数值，单位：微秒
                i++;
            }
            FTPServer.screen.println("UDP " + packet.getAddress().toString() + ":" + packet.getPort() + " 发送请求文件:"
                    + filePath + "结束！共计发送" + i + "个数据包");
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
