package com.NPU.考试0531.第四题;

import java.util.*;

class DHCPServer {

    /**
     * 默认超时时间
     */
    private final int defaultExpiration;

    /**
     * 最大超时时间
     */
    private final int maxExpiration;

    /**
     * 最小超时时间
     */
    private final int minExpiration;

    /**
     * 服务器名称
     */
    private final String serverName;

    /**
     * 地址池
     */
    private final List<IP> addressPool;

    /**
     * key: Occupier（占用IP的发送主机）
     * value: IP
     */
    private Map<String, IP> addressMapOccupier;

    /**
     * 回复的消息
     */
    List<String> responseMessages = new ArrayList<>();

    /**
     * 用于初始化DHCP服务器的参数和地址池
     * @param addressPoolSize
     * @param defaultExpiration
     * @param maxExpiration
     * @param minExpiration
     * @param serverName
     */
    public DHCPServer(int addressPoolSize, int defaultExpiration, int maxExpiration, int minExpiration, String serverName) {
        this.defaultExpiration = defaultExpiration;
        this.maxExpiration = maxExpiration;
        this.minExpiration = minExpiration;
        this.serverName = serverName;
        // 初始化IP
        this.addressPool = new ArrayList<>();
        for (int i = 0; i < addressPoolSize; i++) {
            addressPool.add(new IP(i + 1));
        }
        addressMapOccupier = new HashMap<>();
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
     * @param time 当前时间
     * @param IP 接收的时候忽略
     * @param recvHost
     * @param recvExpiration
     */
    private void handleDiscover(int time, String sendHost, String recvHost, int IP, int recvExpiration) {

        // 判断请求的是否是本服务器
        if (!isServerReceiver(recvHost)) {
            return;
        }
        IP ipAddress = getUnassignedAddress(recvHost);
        // 没有空闲的ip
        if (ipAddress.IPaddress == -1) {
            return;
        }
        // 设置占用者
        ipAddress.occupier = sendHost;
        // 更新状态
        ipAddress.status = State.WAIT;
        // 维护map
        addressMapOccupier.put(sendHost, ipAddress);

        // 设置过期时间
        // time是当前时刻
        int expiration = recvExpiration == 0 ? defaultExpiration : Math.min(maxExpiration, Math.max(minExpiration, recvExpiration - time));
        ipAddress.expirationTime = expiration + time;

        responseMessages.add(serverName + " " + sendHost + " OFR " + ipAddress.IPaddress + " " + ipAddress.expirationTime);
    }

    /**
     * 处理Request消息，根据请求方和请求的IP地址进行不同操作，如释放IP地址或返回ACK（Acknowledgment）消息
     * @param time 当前时间
     * @param IP 接收的时候忽略
     * @param recvHost
     * @param recvExpiration
     */
    private void handleRequest(int time, String sendHost, String recvHost, int IP, int recvExpiration) {
        if (!isServerReceiver(recvHost)) {
            // 首先判断该客户端是否选择本服务器分配的地址：如果不是，则在本服务器上解除对那个 IP 地址的占用
            removeOccupiedAddresses(sendHost);
            return;
        }

        // 否则则再次确认分配的地址有效，并向客户端发送 Ack 报文，表示确认配置有效
        if (!isValidRequest(IP, sendHost, time)) {
            responseMessages.add(serverName + " " + recvHost + " NAK " + IP + " 0");
            return;
        }
        // 设置过期时间
        // time是当前时刻
        IP ip = addressPool.get(IP - 1);
        int expiration = recvExpiration == 0 ? defaultExpiration : Math.min(maxExpiration, Math.max(minExpiration, recvExpiration - time));
        ip.expirationTime = expiration + time;
        // 更新状态
        ip.status = State.USED;
        responseMessages.add(serverName + " " + recvHost + " ACK " + IP + " " + ip.expirationTime);
    }

    /**
     * 获取一个未被分配的IP地址，可以是空闲的或被请求方占用的
     *
     * @param occupier 占用者
     * @return
     */
    private IP getUnassignedAddress(String occupier) {
        IP address = new IP(-1);
        // 1、若存在IP地址对应的占用是发送主机
        // 则返回该IP地址
        if (addressMapOccupier.containsKey(occupier)) {
            // 首先检查此前是否给该客户端分配过 IP 地址，且该 IP 地址在此后没有被分配给其它客户端。如果是这样的情况，则直接将 IP 地址分配给它
            address = addressMapOccupier.get(occupier);
            if (address.status == State.NO || address.status == State.OUT) {
                return address;
            }
        }

        // 若IP地址对应的占用者不是
        // 则选取最小状态为未分配的IP地址
        address = findMinNonAllocate();

        // 若没有未分配的IP地址，则选取最小的状态为过期的IP地址
        if (address.IPaddress == - 1) {
            address = findMinExpairation();
            return address;
        }
        // 若没有 return -1
        return address;
    }

    /**
     * 选取最小状态为未分配的IP地址
     * @return
     */
    private IP findMinExpairation() {
        for (IP ip : addressPool) {
            if (ip.status == State.OUT) {
                // 数组中IP的顺序不变
                return ip;
            }
        }
        return new IP(-1);
    }

    /**
     * 选取最小状态为未分配的IP地址
     * @return
     */
    private IP findMinNonAllocate() {
        for (IP ip : addressPool) {
            if (ip.status == State.NO) {
                // 数组中IP的顺序不变
                return ip;
            }
        }
        return new IP(-1);
    }

    /**
     * 移除指定占用者的已占用IP
     * @param occupier
     * @return
     */
    private void removeOccupiedAddresses(String occupier) {
        if (addressMapOccupier.containsKey(occupier)) {
            IP ip = addressMapOccupier.get(occupier);
            ip.status = State.NO;
            ip.expirationTime = 0;
            ip.occupier = "";
            // 移除key
            addressMapOccupier.remove(occupier);
        }
    }

    /**
     * 检查请求的IP地址是否有效，即是否存在于地址池中并且被请求方占用
     * @param ipAddress
     * @param occupier
     * @return
     */
    private  boolean isValidRequest(int ipAddress, String occupier, int time) {
        IP ip = addressPool.get(ipAddress - 1);
        // IP处于使用状态，则占有者必须是occupier
        if (ip.status == State.USED && ip.occupier.equals(occupier)) {
            return addressPool.get(ipAddress - 1).expirationTime >= time;
        }
        // 当前IP处于非使用状态
        return ip.status != State.USED;
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

        DHCPServer dhcpServer = new DHCPServer(N, Tdef, Tmax, Tmin, H);

        // 收到了n个报文
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String message = sc.nextLine();
            String[] part = message.split(" ");
            // 当前时刻
            int time = Integer.parseInt(part[0]);
            String sendHost = part[1];
            String recvHost = part[2];
            String messageType = part[3];
            int IP = Integer.parseInt(part[4]);
            int expairationTime = Integer.parseInt(part[5]);

            // 清理过期的IP
            dhcpServer.cleanExpairationIP(time);

            // REQ报文的要求：
            if (!recvHost.equals(H) && !recvHost.equals("*") && !messageType.equals("REQ")) continue;

            // 只处理DIS和REQ（返回OFR、ACK、NAK）
            if (!messageType.equals("DIS") && !messageType.equals("REQ")) continue;

            // 接受主机是* 必须是DIS
            if (recvHost.equals("*") && !messageType.equals("DIS")) continue;

            // 客户端在发送DIS报文时，不会指定特定主机？
            if (recvHost.equals(H) && messageType.equals("DIS")) continue;

            switch (messageType) {
                case "DIS":
                    dhcpServer.handleDiscover(time, sendHost, recvHost, IP, expairationTime);
                    break;
                case "REQ":
                    dhcpServer.handleRequest(time, sendHost, recvHost, IP, expairationTime);
                    break;
                default:
                    break;
            }
        }

        dhcpServer.responseMessages.forEach(System.out::println);
    }

    /**
     * 清理过期的IP
     * @param time 当前时刻
     */
    private void cleanExpairationIP(int time) {
        for(IP ip : addressPool) {
            if (ip.status == State.USED && ip.expirationTime <= time) {
                ip.status = State.OUT;
            }
        }
    }
}