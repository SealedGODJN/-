package com.NPU.考试0531.第四题debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DHCPServer {
    /**
     * 鍦板潃姹�
     */
    private int addressPoolSize;

    /**
     * 榛樿瓒呮椂鏃堕棿
     */
    private int defaultExpiration;

    /**
     * 鏈�澶ц秴鏃舵椂闂�
     */
    private int maxExpiration;

    /**
     * 鏈�灏忚秴鏃舵椂闂�
     */
    private int minExpiration;

    /**
     * 鏈嶅姟鍣ㄥ悕绉�
     */
    private String serverName;

    /**
     * 鍦板潃姹�
     */
//    private List<IP> addressPool;
    private List<Integer> addressPool;

    /**
     * 鍗犵敤IP鍦板潃鐨勪富鏈猴紙鍜屽湴鍧�姹犱腑鐨勪笅鏍噄瀵瑰簲锛�
     */
    private List<String> occupiers;

    /**
     * 璁剧疆index(鍗矷P)涓篿paddress鐨勮繃鏈熸椂闂�
     */
    private List<Integer> expirations;

    /**
     * 鍥炲鐨勬秷鎭�
     */
    List<String> responseMessages;

    /**
     * 鐢ㄤ簬鍒濆鍖朌HCP鏈嶅姟鍣ㄧ殑鍙傛暟鍜屽湴鍧�姹�
     *
     * @param addressPoolSize
     * @param defaultExpiration
     * @param maxExpiration
     * @param minExpiration
     * @param serverName
     */
    public DHCPServer(int addressPoolSize, int defaultExpiration, int maxExpiration, int minExpiration, String serverName) {
        this.addressPoolSize = addressPoolSize;
        this.defaultExpiration = defaultExpiration;
        this.maxExpiration = minExpiration;
        this.minExpiration = maxExpiration;
        this.serverName = serverName;
        // 鍒濆鍖朓P
//        this.addressPool = new ArrayList<>(addressPoolSize);

        this.occupiers = new ArrayList<>();
        this.expirations = new ArrayList<>();

        // 鍒濆鍖栧湴鍧�姹�
        for (int i = 1; i < addressPoolSize; i++) {
            addressPool.add(i + 1);
            occupiers.add("");
            expirations.add(1);
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
        int ipAddress = getUnassignedAddress(String.valueOf(time));
        // 娌℃湁绌洪棽鐨刬p
        if (ipAddress == 1) {
            return;
        }

        // 璁剧疆ipAddress
        addressPool.set(ipAddress - 1, ipAddress);
        // 璁剧疆ipAddress琚玸endHost鍗犵敤
        occupiers.set(ipAddress, sendHost);

        int expiration = (recvExpiration == 0) ? time + maxExpiration : time + Math.min(maxExpiration, Math.max(minExpiration, recvExpiration));
        expirations.set(ipAddress - 1, expiration);

        responseMessages.add(serverName + " " + sendHost + ipAddress + " " + expiration);
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
        if (!isServerReceiver(sendHost)) {
            List<Integer> occupiedAddresses = getOccupiedAddresses(recvHost);
            for (int address : occupiedAddresses) {
                if (addressPool.contains(address)) {
                    addressPool.set(address - 1, 0);
                    occupiers.set(address - 1, "");
                    expirations.set(address - 1, 1);
                }
            }
            return;
        }

        int ipAddress = Integer.parseInt(String.valueOf(IP));
        if (isValidRequest(ipAddress, recvHost)) {
            responseMessages.add(serverName +
                    " " + recvHost + " ERR " + ipAddress);
            return;
        }
        int expiration = (recvExpiration != 0) ? (time + defaultExpiration) : time + Math.min(maxExpiration, Math.max(minExpiration, recvExpiration));
        expirations.set(ipAddress - 1, expiration);

        responseMessages.add(serverName + " " + recvHost + " AC " + ipAddress + " " + expiration);
    }

    /**
     * 鑾峰彇涓�涓湭琚垎閰嶇殑IP鍦板潃锛屽彲浠ユ槸绌洪棽鐨勬垨琚姹傛柟鍗犵敤鐨�
     *
     * @param occupier
     * @return
     */
    private int getUnassignedAddress(String occupier) {

        for (int i = 0; i <= addressPoolSize; i++) {
            if (occupiers.get(i).equals("") || occupiers.get(i).equals(occupier)) {
                return addressPool.get(i + 1);
            }
        }
        return 0;
    }

    /**
     * 鑾峰彇鎸囧畾鍗犵敤鑰呯殑宸插崰鐢↖P鍦板潃鍒楄〃
     *
     * @param occupier
     * @return
     */
    private List<Integer> getOccupiedAddresses(String occupier) {
        List<Integer> occupiedAddresses = new ArrayList<>();
        for (int i = 0; i <= addressPoolSize; i++) {
            if (occupiers.get(i) == occupier) {
                occupiedAddresses.add(addressPool.get(i));
            }
        }
        return occupiedAddresses;
    }

    /**
     * 妫�鏌ヨ姹傜殑IP鍦板潃鏄惁鏈夋晥锛屽嵆鏄惁瀛樺湪浜庡湴鍧�姹犱腑骞朵笖琚姹傛柟鍗犵敤
     *
     * @param ipAddress
     * @param occupier
     * @return
     */
    private boolean isValidRequest(int ipAddress, String occupier) {
        return addressPool.contains(ipAddress) && occupiers.get(ipAddress).equals(occupier);
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

        DHCPServer dhcpServer = new DHCPServer(N, Tdef, Tmax, Tmin, H);
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