
package com.NPU.分布式实验.实验1.impl1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 简易ftp服务器TCP线程处理类，用于处理用户输入命令
 *
 * @author liyong
 * @version 1.0
 */
public class TCPServerThread implements Runnable {

    /**
     * 定义换行符
     */
    static private String CRLF = "\r\n";
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
            // 定义输入输出输出流，建议采用Buffered相关类进行缓存读取，效率较高
            BufferedOutputStream ostream = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream istream = new BufferedInputStream(socket.getInputStream());
            FTPServer.screen.println("TCP " + socket.getInetAddress().toString() + ": " + socket.getPort() + ">连接成功");

            /*
             * 采用字节数组读取输入信息
             * 也可转换成BufferedReader方式直接读取用户命令
             * BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
             * br.readLine();
             *
             */
            byte[] buffer = null;
            boolean inEnd = true;
            int c, bufsize = 0, last = 0;
            while (true) {
                buffer = new byte[FTPServer.buffer_size];
                inEnd = true;
                bufsize = 0;
                last = 0;
                // 采用字节数组读取输入数据，定义\r\n为传输结束
                while (inEnd && ((c = istream.read()) != -1) && bufsize < FTPServer.buffer_size) {
                    switch (c) {
                        case '\r':
                            last = '\r';
                            break;
                        case '\n':
                            if (bufsize > 0 && last == '\r') {
                                inEnd = false;
                                break;
                            }
                        default:
                            buffer[bufsize++] = (byte) c;
                    }
                }
                // 对接收命令进行解析
                StringTokenizer st = new StringTokenizer(new String(buffer, 0, bufsize));
                String order = st.nextToken();
                if (order.equalsIgnoreCase("ls")) {
                    handleLs(ostream);
                } else if (order.equalsIgnoreCase("cd")) {
                    try {
                        String path = st.nextToken();
                        handleCd(ostream, path);
                    } catch (NoSuchElementException e) {
                        ostream.write("Usage: cd  <DIR>".getBytes());
                    }
                } else if (order.equalsIgnoreCase("get")) {
                    try {
                        String fileName = st.nextToken();
                        handleGet(ostream, fileName);
                    } catch (NoSuchElementException e) {
                        ostream.write("Usage: get  <File>".getBytes());
                    }
                } else if (order.equalsIgnoreCase("bye")) {
                    ostream.write((currFile.getName() + " > BYE!").getBytes());
                    handleEnd(ostream);
                    break;
                } else {
                    ostream.write("unknown command!".getBytes());
                }
                handleEnd(ostream);
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
    private void handleGet(BufferedOutputStream ostream, String fileName) throws Exception {
        File sendFile = new File(currFile.getAbsolutePath() + "\\" + fileName);
        if (!sendFile.exists() || sendFile.isDirectory()) {
            ostream.write("unknown file!".getBytes());
        } else {
            // 将文件全路径,大小发回给客户端,采用%分割
            ostream.write((sendFile.getCanonicalPath() + "%" + (sendFile.length())).getBytes());
        }

    }

    /**
     * 处理CD命令，如果在根目录或者目录输入错误，不做处理
     *
     * @param ostream 缓冲输出流
     * @param path    目录名称
     * @throws IOException
     */
    private void handleCd(BufferedOutputStream ostream, String path) throws IOException {
        path = path.trim();
        if (path == null || path.length() == 0) {
            ostream.write("Usage: cd  <DIR>".getBytes());
            return;
        } else if (path.equals("..")) {
            if (!currFile.equals(rootFile))
                currFile = currFile.getParentFile();
        } else {
            File newPath = new File(currFile.getAbsolutePath() + "\\" + path);
            if (newPath.exists() && newPath.isDirectory()) {
                currFile = newPath;
            } else {
                ostream.write("unknown dir!".getBytes());
                return;
            }
        }
        ostream.write((currFile.getName() + " > OK").getBytes());

    }

    /**
     * 处理LS命令，列出当前目录下的文件
     *
     * @param ostream 缓冲输出流
     * @throws IOException
     */
    private void handleLs(BufferedOutputStream ostream) throws IOException {
        File[] files = currFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                ostream.write(("<dir>" + TAB + file.getName() + TAB + file.length() + CRLF).getBytes("utf-8"));
            } else {
                ostream.write(("<file>" + TAB + file.getName() + TAB + file.length() + CRLF).getBytes("utf-8"));
            }
        }
        ostream.write((currFile.getName() + " > OK").getBytes());
    }

    /**
     * 添加\r\n\r\作为输出结束，并刷新输出流缓存
     *
     * @param ostream 缓冲输出流
     * @throws IOException
     */
    private void handleEnd(BufferedOutputStream ostream) throws IOException {
        ostream.write(CRLF.getBytes());
        ostream.write(CRLF.getBytes());
        ostream.flush();
    }

}
