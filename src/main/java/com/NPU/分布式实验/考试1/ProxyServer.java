package com.NPU.分布式实验.考试1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * class <em>ProxyServerThread</em> represents a thread of execution in the SSD8
 * proxy server.
 */
class ProxyServerThread implements Runnable {

    /**
     * Network new line.
     */
    static private String CRLF = "\r\n";

    /**
     * Size of byte buffer.
     */
    static private int buffer_size = 8192;

    /**
     * Set up the standard output.
     */
    static PrintWriter screen = new PrintWriter(System.out, true);

    /**
     * Each thread needs a socket and a thread of execution.
     */
    Socket socket = null;
    Thread t;

    /**
     * class <em>ProxyServerThread</em> constructor
     */
    ProxyServerThread(Socket s) {
        socket = s;
        t = new Thread(this, "Proxy Server Thread");
        t.start();

    }

    /**
     * Convert new lines to network new lines.
     */
    private static String changeLF(String request, String method, String uri) {
        String result = "";
        String temp;

        /**
         * Create a string tokenizer based on the new line character.
         */
        StringTokenizer st = new StringTokenizer(request, "\n");
        temp = st.nextToken();

        /**
         * Pad each line of the request with network new lines.
         */
        result = method + " " + getURL(uri) + " " + "HTTP/1.0" + CRLF;
        while (st.hasMoreTokens()) {
            temp = st.nextToken();
            result += (temp + CRLF);
        }
        result += (CRLF + CRLF);
        return (result);
    }

    /**
     * Return the url from the requested uri.
     */
    private static String getURL(String uri) {
        if (uri.startsWith("http://")) {
            return uri.substring(uri.indexOf("/", 7));
        } else {
            return uri;
        }
    }

    /**
     * Return the host name and port name.
     */
    private static String getHostAndPort(String uri) {
        if (uri.startsWith("http://")) {
            return uri.substring(7, uri.indexOf("/", 7));
        } else {
            return "";
        }
    }

    /**
     * Return the remote host name.
     */
    private static String getRemoteHost(String uri) {
        String hostAndPort = getHostAndPort(uri);
        if (hostAndPort.indexOf(":") != -1) {
            return (hostAndPort.substring(0, hostAndPort.indexOf(":")));
        } else {
            return (hostAndPort);
        }
    }

    /**
     * Return the remote port number if one is in the uri. Retrun 80 as a
     * default if no others are specified.
     */
    private static int getRemotePort(String uri) {
        String hostAndPort = getHostAndPort(uri);
        if (hostAndPort.indexOf(":") != -1) {
            return ((new Integer(hostAndPort.substring(hostAndPort.indexOf(":") + 1))).intValue());
        } else {
            return 80;
        }
    }

    /**
     * Create a full response header.
     */
    private static String makeResponse(int code, String msg, String header) {
        return "HTTP/1.0" + " " + code + " " + msg + CRLF + header + CRLF;
    }

    /**
     * Build the appropriate MIME type.
     */
    private static String makeMIME(String type, int len) {
        String mh = "Server: SSD8ProxyServer/1.0" + CRLF + "Content-type: " + type + CRLF + "Content-length: " + len
                + CRLF;
        return mh;
    }

    /**
     * Generate a simple html file to add to an error response header.
     */
    private static String error(int code, String msg) {
        String html_page = "<HTML>" + "<BODY>" + CRLF + "<H1>" + code + " " + msg + "</H1>" + "</BODY>" + "</HTML>"
                + CRLF;
        String mh = makeMIME("text/html", html_page.length());
        String hr = makeResponse(code, msg, mh);
        return hr + html_page;
    }

    /**
     * Run method to do all of the work.
     */
    public void run() {
        Socket rsocket = null;
        byte[] buffer;
        int c = 0;
        try {

            /**
             * Create the input and output streams for the socket.
             */
            BufferedOutputStream ostream = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream istream = new BufferedInputStream(socket.getInputStream());
            // first, read a request
            boolean inRequest = true;
            int bufsize = 0;
            buffer = new byte[buffer_size];

            /**
             * Read in the request.
             */
            while (inRequest && ((c = istream.read()) != -1) && bufsize < buffer_size) {
                switch (c) {
                    case '\r':
                        break;
                    case '\n':
                        if (bufsize > 0 && buffer[bufsize - 1] == (byte) c) {
                            inRequest = false;
                            break;
                        }
                    default:
                        buffer[bufsize++] = (byte) c;
                }
            }
            /**
             * Parse the request.
             */
            String request = new String(buffer, 0, bufsize - 1);
            StringTokenizer st = new StringTokenizer(new String(buffer));
            System.out.println("request:" + request);
            /**
             * Get the method, uri, version, remote host and remote port.
             */
            String method = st.nextToken();
            String uri = st.nextToken();
            String version = st.nextToken();

            String remote_host = getRemoteHost(uri);
            int remote_port = getRemotePort(uri);

            /**
             * Handle a GET request.
             */
            if (method.equals("GET")) {

                /**
                 * Change the connection header field
                 */
                request = changeLF(request, method, uri);

                /**
                 * Open the sockets and create the input and output streams.
                 */
                rsocket = new Socket(remote_host, remote_port);
                BufferedOutputStream rostream = new BufferedOutputStream(rsocket.getOutputStream());
                BufferedInputStream ristream = new BufferedInputStream(rsocket.getInputStream());

                /**
                 * Send the request
                 */
                buffer = request.getBytes();
                rostream.write(buffer, 0, request.length() - 1);
                rostream.flush();
                int bytesRead;

                /**
                 * Write the response directly to the client.
                 */
                while ((bytesRead = ristream.read(buffer)) != -1) {
                    ostream.write(buffer, 0, bytesRead);
                }

                /**
                 * Close the socket and flush the output stream.
                 */
                rsocket.close();
                ostream.flush();
            } else {
                /**
                 * If this was not a GET request, print an error 501
                 * "Not Implemented"
                 */
                String errorCode = error(501, "Not Implemented");
                buffer = errorCode.getBytes();
                ostream.write(buffer, 0, errorCode.length());
                ostream.flush();
            }
            socket.close();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                rsocket.close();
                socket.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

/**
 * class <em>ProxyServer</em> represents a Web Proxy Server.
 */
public class ProxyServer {

    /**
     * The proxy server needs a server socket to accept in-coming connections.
     */
    static private ServerSocket serverSocket;

    /**
     * Proxy is to listen on port 8000
     */
    static private int port = 8080;

    /**
     * main method
     */
    public static void main(String[] args) {
        Socket socket = null;

        /**
         * Create the server socket for listening on port 8000
         */
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        /**
         * Loop for ever accepting connections. When a connection is
         * established, fork off a new thread to handle it.
         */
        while (true) {
            try {
                /**
                 * Block while listening for a new connection
                 */
                socket = serverSocket.accept();

                /**
                 * Create a new proxyserverthread to handle the request.
                 */
                new ProxyServerThread(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
