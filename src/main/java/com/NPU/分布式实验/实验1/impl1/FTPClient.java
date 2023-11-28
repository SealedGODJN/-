package com.NPU.分布式实验.实验1.impl1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.StringTokenizer;

/**
 * ftp 客户端
 *
 * @author liyong
 * @version 1.0
 */
public class FTPClient {
    /**
     * TCP监听端口6397
     */
    private final static int TCP_PORT = 6397;

    /**
     * UDP监听端口6398
     */
    private final static int UDP_PORT = 6398;

    /**
     * 缓冲字节数组大小8192字节
     */
    private static int buffer_size = 8192;

    /**
     * 行结束定义
     */
    private static String CRLF = "\r\n";

    /**
     * 控制台输入,通过BufferedReader进行转换，便于读取字符输入
     */
    static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

    /**
     * 控制台输出
     */
    static PrintWriter screen = new PrintWriter(System.out, true);

    public static void main(String[] args) throws Exception {

        // 传入服务器地址
        if (args.length != 1) {
            System.err.println("Usage: Client <server>");
            System.exit(0);
        }

        start(args[0]);
    }

    private static void start(String serverIP) throws Exception {
        Socket socket = new Socket(serverIP, TCP_PORT);
        screen.println(socket.getInetAddress().toString() + ": " + socket.getPort() + ">连接FTP SERVER成功");
        screen.print(">");
        screen.flush();

        // 创建TCP输出流
        BufferedOutputStream ostream = new BufferedOutputStream(socket.getOutputStream());
        // 创建TCP输入流
        BufferedInputStream istream = new BufferedInputStream(socket.getInputStream());
        byte[] buffer = null;
        boolean isEnd = true;
        int c, bufsize;
        while (true) {
            // 读取键盘输入
            String order = keyboard.readLine();
            // 直接回车不响应
            if (order == null || order.length() < 1) {
                screen.print(">");
                screen.flush();
                continue;
            }
            // 向服务器发送数据，采用\r\n作为输出结束
            ostream.write((order + CRLF).getBytes());
            ostream.flush();

            // 采用字节数组读取服务器传回字节流，采用\r\n\r\n(2个回车换行)作为传输结束
            buffer = new byte[buffer_size];
            isEnd = true;
            bufsize = 0;
            while (isEnd && ((c = istream.read()) != -1) && bufsize < buffer_size) {
                switch (c) {
                    case '\r':
                        break;
                    case '\n':
                        if (bufsize > 0 && buffer[bufsize - 1] == (byte) c) {
                            isEnd = false;
                            break;
                        }
                    default:
                        buffer[bufsize++] = (byte) c;
                }
            }
            // 对字节数组转码，避免中文乱码
            String receive = new String(buffer, 0, bufsize, "utf-8");
            if (order.toLowerCase().startsWith("get")) {
                // 服务器传回“文件全路径名称%文件大小” ，以%作为分隔符
                receive = receive.trim();
                if (receive.indexOf("%") == -1) {
                    screen.println(receive);
                } else {
                    long startTime = System.currentTimeMillis();
                    // 客户端DatagramSocket 随机可用端口，又称匿名端口
                    DatagramSocket udpSocket = new DatagramSocket();
                    SocketAddress socketAddres = new InetSocketAddress(serverIP, UDP_PORT);

                    StringTokenizer st = new StringTokenizer(receive, "%");
                    String fileName = st.nextToken();
                    Long fileSize = Long.parseLong(st.nextToken());
                    // 创建UDP数据包，指定服务器地址
                    DatagramPacket dp = new DatagramPacket(fileName.getBytes(), fileName.length(), socketAddres);
                    // 向服务器端发送数据包
                    udpSocket.send(dp);
                    byte[] receiveByte = new byte[buffer_size];
                    DatagramPacket inputDp = new DatagramPacket(receiveByte, receiveByte.length);
                    // 写入相应的文件,文件如果存在直接覆盖
                    String shortFileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(shortFileName));
                    screen.println("开始接收文件: " + shortFileName);
                    // 根据字节数组大小与文件大小，计算UDP数据包个数
                    for (int i = 0; i <= fileSize / buffer_size; i++) {
                        udpSocket.receive(inputDp); // 接受服务器返回的信息，此方法在接收到数据报之前会一直阻塞
                        // System.out.println(i + " " + inputDp.getLength());
                        out.write(receiveByte, 0, receiveByte.length);
                        out.flush();
                    }
                    udpSocket.close();
                    out.close();
                    long endTime = System.currentTimeMillis();
                    screen.println("文件接收完毕,用时:" + (endTime - startTime) + "ms");

                }

                screen.print(">");
                screen.flush();
            } else if (order.equalsIgnoreCase("bye")) {
                socket.close();
                System.exit(0);
            } else {
                screen.print(receive);
                screen.print(">");
                screen.flush();
            }

        }
    }

}
