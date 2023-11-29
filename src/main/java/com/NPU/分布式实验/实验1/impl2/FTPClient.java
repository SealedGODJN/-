package com.NPU.分布式实验.实验1.impl2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;
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

        // 启动UPD端口
        DatagramSocket udpSocket = new DatagramSocket(UDP_PORT);

        Socket socket = new Socket(serverIP, TCP_PORT);
        screen.println(socket.getInetAddress().toString() + ": " + socket.getPort() + ">连接FTP SERVER成功");
        screen.print(">");
        screen.flush();

        // 客户端输出流，向服务器发消息
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        // 客户端输入流，接收服务器消息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(bw, true); // 装饰输出流，及时刷新
        Scanner in = new Scanner(System.in); // 接受用户信息
        String order = null;
        // 读取键盘输入
        while ((order = in.nextLine()) != null) {

            // 直接回车不响应
            if (order == null || order.length() < 1) {
                screen.print(">");
                screen.flush();
                continue;
            }
            // 向服务器发送数据，采用\r\n作为输出结束
            pw.println(order);
            String receive = null;
            while ((receive = br.readLine()) != null) {
                if (receive.equals(""))
                    break;

                screen.println(receive);

            }

            if (order.toLowerCase().startsWith("get")) {
                // 服务器传回“文件全路径名称%文件大小” ，以%作为分隔符
                String receiveFile = br.readLine();
                if (!receiveFile.equals("")) {
                    long startTime = System.currentTimeMillis();

                    StringTokenizer st = new StringTokenizer(receiveFile, "%");
                    String fileName = st.nextToken();
                    Long fileSize = Long.parseLong(st.nextToken());

                    // 创建UDP数据包，接收数据包
                    DatagramPacket inputDp = new DatagramPacket(new byte[buffer_size], buffer_size);
                    // 写入相应的文件,文件如果存在直接覆盖
                    String shortFileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(shortFileName));
                    screen.println("开始接收文件: " + shortFileName);
                    // 根据字节数组大小与文件大小，计算UDP数据包个数
                    for (int i = 0; i <= fileSize / buffer_size; i++) {
                        // System.out.println(i+" "+inputDp.getLength());
                        udpSocket.receive(inputDp); // 接受服务器返回的信息，此方法在接收到数据报之前会一直阻塞
                        // System.out.println(i+" "+inputDp.getLength());
                        out.write(inputDp.getData(), 0, inputDp.getData().length);
                        out.flush();
                    }

                    out.close();
                    udpSocket.disconnect();
                    long endTime = System.currentTimeMillis();
                    screen.println("文件接收完毕,用时:" + (endTime - startTime) + "ms");

                }

                screen.print(">");
                screen.flush();
            } else if (order.equalsIgnoreCase("bye")) {
                socket.close();
                udpSocket.close();
                System.exit(0);
            } else {

                screen.print(">");
                screen.flush();
            }
        }
    }
}
