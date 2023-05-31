package com.NPU.考试0531.第四题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DHCPServer {
    /**
     * 地址池
     */
    private int addressPoolSize;

    /**
     * 默认超时时间
     */
    private int defaultExpiration;

    /**
     * 最大超时时间
     */
    private int maxExpiration;

    /**
     * 最小超时时间
     */
    private int minExpiration;

    /**
     * 服务器名称
     */
    private String serverName;

    /**
     * 地址池
     */
    private List<Integer> addressPool;

    /**
     * 占用地址的主机
     */
    private List<String> occupiers;

    /**
     * 设置index为ipaddress的过期时间
     */
    private List<Integer> expirations;

    /**
     * 用于初始化DHCP服务器的参数和地址池
     * @param addressPoolSize
     * @param defaultExpiration
     * @param maxExpiration
     * @param minExpiration
     * @param serverName
     */
    public DHCPServer(int addressPoolSize, int defaultExpiration, int maxExpiration, int minExpiration, String serverName) {
        this.addressPoolSize = addressPoolSize;
        this.defaultExpiration = defaultExpiration;
        this.maxExpiration = maxExpiration;
        this.minExpiration = minExpiration;
        this.serverName = serverName;
        this.addressPool = new ArrayList<>();
        this.occupiers = new ArrayList<>();
        this.expirations = new ArrayList<>();

        // 初始化地址池
        for (int i = 1; i <= addressPoolSize; i++) {
            addressPool.add(i);
            occupiers.add("");
            expirations.add(0);
        }
    }

    /**
     * 处理接收到的消息列表，根据消息类型调用不同的处理函数，并返回响应消息列表。
     * @param messages
     * @return
     */
    public List<String> processMessages(List<String> messages) {
        List<String> responseMessages = new ArrayList<>();
        for (String message : messages) {
            String[] parts = message.split(" ");
            int time = Integer.parseInt(parts[0]);
            String messageType = parts[2];

            if (!isServerReceiver(parts[1]) && !messageType.equals("REQ")) {
                continue;
            }

            if (!messageType.equals("DIS") && !messageType.equals("REQ")) {
                continue;
            }

            if (isInvalidReceiver(parts[1], messageType)) {
                continue;
            }

            if (messageType.equals("DIS")) {
                handleDiscover(time, parts, responseMessages);
            } else if (messageType.equals("REQ")) {
                handleRequest(time, parts, responseMessages);
            }
        }
        return responseMessages;
    }

    /**
     * 检查消息的接收者是否为服务器本身或为通配符“*”
     * @param receiver
     * @return
     */
    private boolean isServerReceiver(String receiver) {
        return receiver.equals(serverName) || receiver.equals("*");
    }

    /**
     * 检查消息的接收者是否为无效的，即对于DIS（Discover）消息不能是服务器本身，对于其他消息（非DIS消息）不能是通配符“*”
     * @param receiver
     * @param messageType
     * @return
     */
    private boolean isInvalidReceiver(String receiver, String messageType) {
        return (receiver.equals("*") && !messageType.equals("DIS")) ||
                (receiver.equals(serverName) && messageType.equals("DIS"));
    }

    /**
     * 处理Discover消息，分配一个未被使用的IP地址给请求方，并更新地址池、占用者和到期时间
     * @param time
     * @param parts
     * @param responseMessages
     */
    private void handleDiscover(int time, String[] parts, List<String> responseMessages) {
        int ipAddress = getUnassignedAddress(parts[1]);
        if (ipAddress == -1) {
            return;
        }

        addressPool.set(ipAddress - 1, ipAddress);
        occupiers.set(ipAddress - 1, parts[1]);

        int expiration = (parts[4].equals("0")) ? (time + defaultExpiration) : Math.min(maxExpiration, Math.max(minExpiration, time + Integer.parseInt(parts[4])));
        expirations.set(ipAddress - 1, expiration);

        responseMessages.add(serverName + " " + parts[1] + " OFR " + ipAddress + " " + expiration);
    }

    /**
     * 处理Request消息，根据请求方和请求的IP地址进行不同操作，如释放IP地址或返回ACK（Acknowledgment）消息
     * @param time
     * @param parts
     * @param responseMessages
     */
    private void handleRequest(int time, String[] parts, List<String> responseMessages) {
        if (!isServerReceiver(parts[1])) {
            List<Integer> occupiedAddresses = getOccupiedAddresses(parts[1]);
            for (int address : occupiedAddresses) {
                if (addressPool.contains(address)) {
                    addressPool.set(address - 1, 0);
                    occupiers.set(address - 1, "");
                    expirations.set(address - 1, 0);
                }
            }
            return;
        }

        int ipAddress = Integer.parseInt(parts[3]);
        if (!isValidRequest(ipAddress, parts[1])) {
            responseMessages.add(serverName +
                    " " + parts[1] + " ERR " + ipAddress);
            return;
        }
        int expiration = (parts[4].equals("0")) ? (time + defaultExpiration) : Math.min(maxExpiration, Math.max(minExpiration, time + Integer.parseInt(parts[4])));
        expirations.set(ipAddress - 1, expiration);

        responseMessages.add(serverName + " " + parts[1] + " ACK " + ipAddress + " " + expiration);
    }

    /**
     * 获取一个未被分配的IP地址，可以是空闲的或被请求方占用的
     * @param occupier
     * @return
     */
    private int getUnassignedAddress(String occupier) {
        for (int i = 0; i < addressPoolSize; i++) {
            if (occupiers.get(i).equals("") || occupiers.get(i).equals(occupier)) {
                return addressPool.get(i);
            }
        }
        return -1;
    }

    /**
     * 获取指定占用者的已占用IP地址列表
     * @param occupier
     * @return
     */
    private List<Integer> getOccupiedAddresses(String occupier) {
        List<Integer> occupiedAddresses = new ArrayList<>();
        for (int i = 0; i < addressPoolSize; i++) {
            if (occupiers.get(i).equals(occupier)) {
                occupiedAddresses.add(addressPool.get(i));
            }
        }
        return occupiedAddresses;
    }

    /**
     * 检查请求的IP地址是否有效，即是否存在于地址池中并且被请求方占用
     * @param ipAddress
     * @param occupier
     * @return
     */
    private boolean isValidRequest(int ipAddress, String occupier) {
        return addressPool.contains(ipAddress) && occupiers.get(ipAddress - 1).equals(occupier);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 地址池大小 N：表示能够分配给客户端的 IP 地址的数目，且能分配的 IP 地址是 1,2,…,N；
        int N = sc.nextInt();
        // 默认过期时间 Tdef：表示分配给客户端的 IP 地址的默认的过期时间长度；
        int Tdef = sc.nextInt();
        // 过期时间的上限和下限 Tmax、Tmin：表示分配给客户端的 IP 地址的最长过期时间长度和最短过期时间长度，客户端不能请求比这个更长或更短的过期时间；
        int Tmax = sc.nextInt();
        int Tmin = sc.nextInt();
        // 本机名称 H：表示运行 DHCP 服务器的主机名。
        String H = sc.nextLine().trim();

        // 收到了n个报文
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int sendHost = sc.nextInt();
            int recvHost = sc.nextInt();
            String messageType = sc.nextLine();
            int IP = sc.nextInt();
            int t = sc.nextInt();
            switch (messageType) {
                case "":
                    break;
                case "":
                    break;
                case "":
                    break;
                case "":
                    break;
                case "":
                    break;
                default:
                    break;
            }
        }
        DHCPServer dhcpServer = new DHCPServer(N, Tdef, Tmax, Tmin, H);
        System.out.println(dhcpServer.serverName);
    }
}