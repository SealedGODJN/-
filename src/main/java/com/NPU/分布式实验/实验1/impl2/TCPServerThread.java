
package com.NPU.分布式实验.实验1.impl2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * 简易ftp服务器TCP线程处理类，用于处理用户输入命令
 *
 * @author liyong
 * @version 1.0
 */
public class TCPServerThread implements Runnable {

    /**
     * 定义TAB字符
     */
    static private String TAB = "\t";

    /**
     * TCPsocket控制类
     */
    private Socket socket;
    /**
     * ftp定义的根路径
     */
    private File rootFile;

    /**
     * 用户当前所在目录
     */
    private File currFile;

    /**
     * 启动FTP线程
     *
     * @param socket   tcp连接
     * @param rootFile ftp的根路径
     */
    public TCPServerThread(Socket socket, File rootFile) {
        this.socket = socket;
        this.rootFile = rootFile;
        this.currFile = rootFile;
    }

    public void run() {
        try {

            FTPServer.screen.println("TCP " + socket.getInetAddress().toString() + ": " + socket.getPort() + ">连接成功");
            // 输入流，读取客户端信息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 输出流，向客户端写信息
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            PrintWriter pw = new PrintWriter(bw, true); // 装饰输出流，true,每写一行就刷新输出缓冲区，不用flush
            String info = null; // 接收用户输入的信息
            while ((info = br.readLine()) != null) {
                // 对接收命令进行解析
                StringTokenizer st = new StringTokenizer(info);
                String order = st.nextToken();

                if (order.equalsIgnoreCase("ls")) {
                    handleLs(pw);
                } else if (order.equalsIgnoreCase("cd")) {
                    try {
                        String path = st.nextToken();
                        handleCd(pw, path);
                    } catch (NoSuchElementException e) {
                        pw.println("Usage: cd  <DIR>");
                        handleEnd(pw);
                    }
                } else if (order.equalsIgnoreCase("get")) {
                    try {
                        String fileName = st.nextToken();
                        handleGet(pw, fileName);
                    } catch (NoSuchElementException e) {
                        pw.println("Usage: get  <File>");
                        handleEnd(pw);
                        handleEnd(pw);
                    }

                } else if (order.equalsIgnoreCase("bye")) {
                    pw.println(currFile.getName() + " > BYE!");
                    handleEnd(pw);
                    break;
                } else {
                    pw.println("unknown command!");
                    handleEnd(pw);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理get命令，将文件全路径名称与文件大小（采用%进行分割）返回客户端。
     *
     * @param ostream  缓冲输出流
     * @param fileName 文件名称
     * @throws Exception
     */
    private void handleGet(PrintWriter pw, String fileName) throws Exception {
        File sendFile = new File(currFile.getAbsolutePath() + "\\" + fileName);
        if (!sendFile.exists() || sendFile.isDirectory()) {
            pw.println("unknown file!");
            handleEnd(pw);
            handleEnd(pw);
        } else {
            handleEnd(pw);
            // 将文件全路径,大小发回给客户端,采用%分割
            pw.println(sendFile.getCanonicalPath() + "%" + (sendFile.length()));

            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    // 启动DatagramSocket 随机可用端口，又称匿名端口
                    DatagramSocket udpSocket = null;
                    // 读取文件采用建议采用BufferedInputStream 效率较高
                    BufferedInputStream in = null;
                    try {
                        // 启动udp
                        // 采用服务器端直接发送给客户端指定端口方式发送文件
                        FTPServer.screen.println("UDP 发送请求文件:" + sendFile.getCanonicalPath());

                        udpSocket = new DatagramSocket();
                        in = new BufferedInputStream(new FileInputStream(sendFile));
                        byte[] buffer = new byte[FTPServer.buffer_size];
                        SocketAddress socketAddres = new InetSocketAddress(socket.getInetAddress(), FTPServer.UDP_PORT);
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, socketAddres);
                        // 接受读取的内容(n就代表的相关数据，只不过是数字的形式)
                        int n = -1, i = 0;
                        // 循环取出数据
                        while ((n = in.read(buffer, 0, buffer.length)) != -1) {
                            packet.setData(buffer, 0, buffer.length);
                            udpSocket.send(packet); // 发送数据
                            TimeUnit.MILLISECONDS.sleep(5);// 确保发送的顺序，根据机器的配置，如果客户端发生丢包，可增大此数值，单位：微秒
                            i++;
                        }
                        FTPServer.screen.println("UDP " + packet.getAddress().toString() + ":" + packet.getPort()
                                + " 发送请求文件:" + sendFile.getCanonicalPath() + "结束！共计发送" + i + "个数据包");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (udpSocket != null)
                            udpSocket.close();

                        if (in != null)
                            try {
                                in.close();
                            } catch (IOException e) {

                            }

                    }
                }

            });

            t.start();
        }
    }

    /**
     * 处理CD命令，如果在根目录或者目录输入错误，不做处理
     *
     * @param ostream 缓冲输出流
     * @param path    目录名称
     * @throws IOException
     */
    private void handleCd(PrintWriter pw, String path) throws IOException {
        path = path.trim();
        if (path == null || path.length() == 0) {
            pw.println("Usage: cd  <DIR>");
            handleEnd(pw);
            return;
        } else if (path.equals("..")) {
            if (!currFile.equals(rootFile))
                currFile = currFile.getParentFile();
        } else {
            File newPath = new File(currFile.getAbsolutePath() + "\\" + path);
            if (newPath.exists() && newPath.isDirectory()) {
                currFile = newPath;
            } else {
                pw.println("unknown dir!");
                handleEnd(pw);
                return;
            }
        }
        pw.println(currFile.getName() + " > OK");
        handleEnd(pw);

    }

    /**
     * 处理LS命令，列出当前目录下的文件
     *
     * @param ostream 缓冲输出流
     * @throws IOException
     */
    private void handleLs(PrintWriter pw) throws IOException {
        File[] files = currFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                pw.println("<dir>" + TAB + file.getName() + TAB + file.length());
            } else {
                pw.println("<file>" + TAB + file.getName() + TAB + file.length());
            }
        }
        pw.println(currFile.getName() + " > OK");
        handleEnd(pw);
    }

    /**
     * 添加\r\n作为输出结束
     *
     * @param ostream 缓冲输出流
     * @throws IOException
     */
    private void handleEnd(PrintWriter pw) throws IOException {
        pw.println();

    }

}
