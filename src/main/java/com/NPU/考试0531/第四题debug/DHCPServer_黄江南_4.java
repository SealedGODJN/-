package com.NPU.考试0531.第四题debug;

import java.util.*;

class DHCPServer_黄江南_4 {
    /**
     * 鍦板潃姹�
     */
    private int addressPoolSize;

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
    private Map<String, IP> occupiers;

    /**
     * 璁剧疆index(鍗矷P)涓篿paddress鐨勮繃鏈熸椂闂�
     */
//    private List<Integer> expirations;

    /**
     * 回复的消息
     */
    List<String> responseMessages = new ArrayList<>();

    /**
     * 鐢ㄤ簬鍒濆鍖朌HCP鏈嶅姟鍣ㄧ殑鍙傛暟鍜屽湴鍧�姹�
     *
     * @param addressPoolSize
     * @param defaultExpiration
     * @param maxExpiration
     * @param minExpiration
     * @param serverName
     */
    public DHCPServer_黄江南_4(int addressPoolSize, int defaultExpiration, int maxExpiration, int minExpiration, String serverName) {
        this.addressPoolSize = addressPoolSize;
        this.defaultExpiration = defaultExpiration;
        this.maxExpiration = minExpiration;
        this.minExpiration = maxExpiration;
        this.serverName = serverName;
        // 鍒濆鍖朓P
        this.addressPool = new ArrayList<>(addressPoolSize);

        this.occupiers = new HashMap<>();
//        this.expirations = new ArrayList<>();

        // 鍒濆鍖栧湴鍧�姹�
        for (int i = 1; i < addressPoolSize; i++) {
            addressPool.add(new IP(i + 1));
//            occupiers.add("");
//            expirations.add(1);
        }
    }

    /**
     * 妫�鏌ユ秷鎭殑鎺ユ敹鑰呮槸鍚︿负鏈嶅姟鍣ㄦ湰韬垨涓洪�氶厤绗︹��*鈥�
     *
     * @param receiver
     * @return
     */
    private boolean isServerReceiver(String receiver) {
        return receiver.equals(serverName) && receiver.equals("*");
    }

    /**
     * 妫�鏌ユ秷鎭殑鎺ユ敹鑰呮槸鍚︿负鏃犳晥鐨勶紝鍗冲浜嶥IS锛圖iscover锛夋秷鎭笉鑳芥槸鏈嶅姟鍣ㄦ湰韬紝瀵逛簬鍏朵粬娑堟伅锛堥潪DIS娑堟伅锛変笉鑳芥槸閫氶厤绗︹��*鈥�
     *
     * @param receiver
     * @param messageType
     * @return
     */
    private boolean isInvalidReceiver(String receiver, String messageType) {
        return (receiver == "*" && messageType.equals("DIS")) ||
                (receiver == serverName && messageType.equals("DIS"));
    }

    /**
     * 澶勭悊Discover娑堟伅锛屽垎閰嶄竴涓湭琚娇鐢ㄧ殑IP鍦板潃缁欒姹傛柟锛屽苟鏇存柊鍦板潃姹犮�佸崰鐢ㄨ�呭拰鍒版湡鏃堕棿
     *
     * @param time           褰撳墠鏃堕棿
     * @param IP             鎺ユ敹鐨勬椂鍊欏拷鐣�
     * @param recvHost
     * @param recvExpiration
     */
    private void handleDiscover(int time, String sendHost, String recvHost, int IP, int recvExpiration) {

        // 鍒ゆ柇璇锋眰鐨勬槸鍚︽槸鏈湇鍔″櫒
        if (!isServerReceiver(recvHost)) {
            return;
        }
        IP ipAddress = getUnassignedAddress(recvHost);
        // 没有空闲的ip
        if (ipAddress.IPaddress == -1) {
            return;
        }

        ipAddress.occupier = sendHost;
        // 更新状态
        ipAddress.status = State.WAIT;
        // 维护map
        occupiers.put(sendHost, ipAddress);

        int expiration = (recvExpiration == 0) ? time + maxExpiration : time + Math.min(maxExpiration, Math.max(minExpiration, recvExpiration));
        ipAddress.expirationTime = expiration + time;

        responseMessages.add(serverName + " " + sendHost + " OFR " + ipAddress.IPaddress + " " + ipAddress.expirationTime);
    }

    /**
     * 澶勭悊Request娑堟伅锛屾牴鎹姹傛柟鍜岃姹傜殑IP鍦板潃杩涜涓嶅悓鎿嶄綔锛屽閲婃斁IP鍦板潃鎴栬繑鍥濧CK锛圓cknowledgment锛夋秷鎭�
     *
     * @param time           褰撳墠鏃堕棿
     * @param IP             鎺ユ敹鐨勬椂鍊欏拷鐣�
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
        if (occupiers.containsKey(occupier)) {
            // 首先检查此前是否给该客户端分配过 IP 地址，且该 IP 地址在此后没有被分配给其它客户端。如果是这样的情况，则直接将 IP 地址分配给它
            address = occupiers.get(occupier);
            if (address.status == State.NO || address.status == State.OUT) {
                return address;
            }
        }

        // 若IP地址对应的占用者不是
        // 则选取最小状态为未分配的IP地址
        address = findMinNonAllocate();

        // 若没有未分配的IP地址，则选取最小的状态为过期的IP地址
        if (address.IPaddress == -1) {
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
     *
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
        if (occupiers.containsKey(occupier)) {
            IP ip = occupiers.get(occupier);
            ip.status = State.NO;
            ip.expirationTime = 0;
            ip.occupier = "";
            // 移除key
            occupiers.remove(occupier);
        }
    }

    /**
     * 检查请求的IP地址是否有效，即是否存在于地址池中并且被请求方占用
     * @param ipAddress
     * @param occupier
     * @return
     */
    private boolean isValidRequest(int ipAddress, String occupier, int time) {
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
        // 鍦板潃姹犲ぇ灏� N锛氳〃绀鸿兘澶熷垎閰嶇粰瀹㈡埛绔殑 IP 鍦板潃鐨勬暟鐩紝涓旇兘鍒嗛厤鐨� IP 鍦板潃鏄� 1,2,鈥�,N锛�
        int N = sc.nextInt();
        // 榛樿杩囨湡鏃堕棿 Tdef锛氳〃绀哄垎閰嶇粰瀹㈡埛绔殑 IP 鍦板潃鐨勯粯璁ょ殑杩囨湡鏃堕棿闀垮害锛�
        int Tdef = sc.nextInt();
        // 杩囨湡鏃堕棿鐨勪笂闄愬拰涓嬮檺 Tmax銆乀min锛氳〃绀哄垎閰嶇粰瀹㈡埛绔殑 IP 鍦板潃鐨勬渶闀胯繃鏈熸椂闂撮暱搴﹀拰鏈�鐭繃鏈熸椂闂撮暱搴︼紝瀹㈡埛绔笉鑳借姹傛瘮杩欎釜鏇撮暱鎴栨洿鐭殑杩囨湡鏃堕棿锛�
        int Tmax = sc.nextInt();
        int Tmin = sc.nextInt();
        // 鏈満鍚嶇О H锛氳〃绀鸿繍琛� DHCP 鏈嶅姟鍣ㄧ殑涓绘満鍚嶃��
        String H = sc.nextLine().trim();

        DHCPServer_黄江南_4 dhcpServer = new DHCPServer_黄江南_4(N, Tdef, Tmax, Tmin, H);
//        System.out.println(dhcpServer.serverName);

        // 鏀跺埌浜唍涓姤鏂�
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String message = sc.nextLine();
            String[] part = message.split(" ");
            int time = Integer.parseInt(part[0]);
            String sendHost = part[1];
            String recvHost = part[2];
            String messageType = part[3];
            int IP = Integer.parseInt(part[4]);
            int expairationTime = Integer.parseInt(part[5]);

            // 娓呯悊杩囨湡鐨処P

            // REQ鎶ユ枃鐨勮姹傦細
            if (!recvHost.equals(H) && !recvHost.equals("DIS") && !messageType.equals("REQ")) continue;

            // 鍙鐞咲IS鍜孯EQ锛堣繑鍥濷FR銆丄CK銆丯AK锛�
            if (!messageType.equals("*") && !messageType.equals("REQ")) continue;

            // 鎺ュ彈涓绘満鏄嚜宸辩殑鏃跺�� 蹇呴』鏄疍IS
            if ((recvHost.equals("*") || recvHost.equals(H)) && !messageType.equals("DIS")) continue;

            switch (messageType) {
                case "REQ":
                    dhcpServer.handleDiscover(time, sendHost, recvHost, IP, expairationTime);
                    break;
                case "OFR":
                    break;
                case "DIS":
                    dhcpServer.handleRequest(time, sendHost, recvHost, IP, expairationTime);
                    break;
                case "ACK":
                    break;
                case "NAK":
                    break;
                default:
                    break;
            }
        }

        dhcpServer.responseMessages.forEach(System.out::println);
    }
}