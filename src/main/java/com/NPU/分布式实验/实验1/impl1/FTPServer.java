
package com.NPU.分布式实验.实验1.impl1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简易FTP服务器,命令传输采用tcp，文件传输采用udp
 *
 * @author liyong
 * @version 1.0
 */
public class FTPServer {

    /**
     * 定义控制台输出
     */
    static PrintWriter screen = new PrintWriter(System.out, true);

    /**
     * TCP监听端口6397
     */
    final static int TCP_PORT = 6397;

    /**
     * UDP监听端口6398
     */
    final static int UDP_PORT = 6398;

    /**
     * 缓冲字节数组大小8192字节
     */
    static int buffer_size = 8192;

    /**
     * TCP socket 控制类
     */
    private ServerSocket serverSocket;

    /**
     * 线程池
     */
    private ExecutorService executorService;

    /**
     * 构造方法，创建TCP socket与线程池，最多4个线程
     *
     * @throws IOException
     */
    public FTPServer() {
        try {
            serverSocket = new ServerSocket(TCP_PORT, 4);
            screen.println("The TCP SERVER START!");
            // 定义线程池
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 启动TCP、UDP服务
     *
     * @param rootFile ftp访问的根路径
     * @throws IOException
     */
    public void service(File rootFile) throws IOException {
        // 启动udp
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    DatagramSocket socket = new DatagramSocket(UDP_PORT);
                    screen.println("The UDP SERVER START!");
                    // 创建数据包，指定接收的数据包的大小
                    DatagramPacket packet = new DatagramPacket(new byte[FTPServer.buffer_size], FTPServer.buffer_size);
                    while (true) {
                        socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
                        //接收到文件请求，交予线程处理
                        Thread thread = new Thread(new UDPThread(socket, packet));
                        thread.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        Socket socket = null;
        while (true) {
            socket = serverSocket.accept();
            // 线程池处理
            executorService.execute(new TCPServerThread(socket, rootFile));
        }

    }

    /**
     * 服务器启动入口，启动方式 FileServer d:\ftp
     *
     * @param args ftp访问的根路径
     */
    public static void main(String[] args) {
        try {
            if (args.length < 1 || args.length > 2) {
                System.err.println("Usage: FileServer <DIR> ");
                return;
            }
            File root = new File(args[0]);
            if (!root.isDirectory()) {
                System.err.println(root.getAbsolutePath() + " does not exist or is not a directory");
                return;
            }
            new FTPServer().service(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
