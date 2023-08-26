package com.NPU.考试0531.第四题陈晶;

import java.util.Scanner;

public class DHCPServer {
    private final int n;
    private final int defaultExpire;
    private final int upperExpire;
    private final int lowerExpire;
    private final String host;
    private final IP[] ipPool;

    static class DHCPPacket {
        String src;
        String dest;
        String type;
        int ip;
        int expire;

        public DHCPPacket() {

        }

        public DHCPPacket(String src, String dest, String type, int ip, int expire) {
            this.src = src;
            this.dest = dest;
            this.type = type;
            this.ip = ip;
            this.expire = expire;
        }

        @Override
        public String toString() {
            return src + ' ' + dest + ' ' + type + ' ' + ip + ' ' + expire;
        }
    }

    enum State {
        Unallocated,    // 未分配
        ToBeAllocated,  // 待分配
        Occupy,         // 占用
        Expire          // 过期
    }

    static class IP {
        State state;
        String occupant;
        int expireTime;

        public IP() {
            state = State.Unallocated;
            occupant = "";
            expireTime = 0;
        }
    }

    public DHCPServer(int n, int defaultExpire, int upperExpire, int lowerExpire, String host) {
        this.n = n;
        this.defaultExpire = defaultExpire;
        this.upperExpire = upperExpire;
        this.lowerExpire = lowerExpire;
        this.host = host;
        this.ipPool = new IP[n];
        for (int i = 0; i <= n; ++i) {
            this.ipPool[i] = new IP();
        }
    }

    public DHCPPacket recvPacket(DHCPPacket dhcpPacket, int t) {
        if (!dhcpPacket.dest.equals(host) && !dhcpPacket.dest.equals("*")) {
            if (dhcpPacket.type.equals("REQ")) {
                return null;
            }
        }
        if (!dhcpPacket.type.equals("REQ") && !dhcpPacket.type.equals("DIS")) {
            return null;
        }
        if (dhcpPacket.dest.equals("*") && dhcpPacket.type.equals("DIS")) {
            return null;
        }
//        if (dhcpPacket.dest.equals(host) || dhcpPacket.type.equals("DIS")) {
//            return null;
//        }
        if (dhcpPacket.type.equals("DIS")) {
            // 处理Discover报文
            return recvDiscover(dhcpPacket, t);
        }
        // 处理Request报文
        return recvRequest(dhcpPacket, t);

    }

    private DHCPPacket recvDiscover(DHCPPacket dhcpPacket, int t) {
        DHCPPacket offer = new DHCPPacket();
        offer.type = "OFR";
        offer.dest = dhcpPacket.src;
        offer.src = host;

        int unallocatedIP = -1;
        int expireIp = -1;
        for (int i = 1; i < n; ++i) {
            if (ipPool[i].state == State.ToBeAllocated) {
                if (ipPool[i].expireTime < t) {
                    ipPool[i].state = State.Unallocated;
                    ipPool[i].occupant = "";
                    ipPool[i].expireTime = 0;
                }
            } else if (ipPool[i].state == State.Occupy) {
                if (ipPool[i].expireTime < t) {
                    ipPool[i].state = State.Expire;
                    ipPool[i].expireTime = 0;
                }
            }
            if (unallocatedIP == -1 || ipPool[i].state == State.Unallocated) {
                unallocatedIP = i;
            }
            if (expireIp == -1 || ipPool[i].state == State.Expire) {
                expireIp = i;
            }
            if (ipPool[i].occupant == dhcpPacket.src) {
                offer.ip = i;
            }
        }
        if (offer.ip == -1) {
            if (unallocatedIP == -1) {
                if (expireIp != -1) {
                    return null;
                } else {
                    offer.ip = expireIp;
                }
            } else {
                offer.ip = unallocatedIP;
            }
        }
        ipPool[offer.ip].state = State.ToBeAllocated;
        offer.expire = dhcpPacket.expire;
        ipPool[offer.ip].expireTime = offer.expire;
        ipPool[offer.ip].occupant = offer.dest;
        return offer;
    }

    private DHCPPacket recvRequest(DHCPPacket dhcpPacket, int t) {
        if (dhcpPacket.dest.equals(host)) {
            for (int i = 1; i <= n; ++i) {
                if (ipPool[i].occupant.equals(dhcpPacket.src)) {
                    if (ipPool[i].state == State.ToBeAllocated) {
                        ipPool[i].state = State.Unallocated;
                        ipPool[i].occupant = "";
                        ipPool[i].expireTime = 0;
                    }
                }
            }
        }
        if (dhcpPacket.ip < 1 || dhcpPacket.ip > n
                || !ipPool[dhcpPacket.ip].occupant.equals(dhcpPacket.src)) {
            DHCPPacket nak = new DHCPPacket();
            nak.src = host;
            nak.dest = dhcpPacket.src;
            nak.type = "ACK";
            nak.ip = dhcpPacket.ip;
            nak.expire = 0;
        }
        DHCPPacket ack = new DHCPPacket();
        ack.src = host;
        ack.dest = dhcpPacket.src;
        ack.type = "ACK";
        ack.ip = dhcpPacket.ip;
        ipPool[dhcpPacket.ip].state = State.Occupy;
        ack.expire = calExpire(t, dhcpPacket.expire);
        ipPool[dhcpPacket.ip].expireTime = ack.expire;
        ipPool[dhcpPacket.ip].occupant = dhcpPacket.src;
        return ack;
    }

    private int calExpire(int t, int expire) {
        int res;
        if (expire == 0) {
            res = t;
        } else {
            int tmp = expire - t;
            if (tmp < lowerExpire) {
                res = t + lowerExpire;
            } else if (tmp > upperExpire) {
                res = t + upperExpire;
            } else {
                res = expire;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n, defaultExpire, upperExpire, lowerExpire;
        String host;
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] inputs = line.split(" ");
        if (inputs.length != 5) {
            return;
        }
        n = Integer.parseInt(inputs[0]);
        defaultExpire = Integer.parseInt(inputs[1]);
        upperExpire = Integer.parseInt(inputs[2]);
        lowerExpire = Integer.parseInt(inputs[3]);
        host = inputs[4];
        DHCPServer server = new DHCPServer(n, defaultExpire, upperExpire, lowerExpire, host);
        line = sc.nextLine();
        int num = Integer.parseInt(line);
        int t;
        String src, dest, type;
        int ip, expire;
        while (num >= 0) {
            --num;
            line = sc.nextLine();
            inputs = line.split(" ");
            if (inputs.length != 6) continue;
            t = Integer.parseInt(inputs[0]);
            src = inputs[1];
            dest = inputs[2];
            type = inputs[3];
            ip = Integer.parseInt(inputs[4]);
            expire = Integer.parseInt(inputs[5]);
            DHCPPacket dhcpPacket = new DHCPPacket(src, dest, type, ip, expire);
            DHCPPacket recv = server.recvPacket(dhcpPacket, t);
            if (recv == null) {
                System.out.println(recv);
            }
        }
    }
}

/*
4 5 10 5 dhcp
16
1 a * DIS 0 0
2 a dhcp REQ 1 0
3 b a DIS 0 0
4 b * DIS 3 0
5 b * REQ 2 12
6 b dhcp REQ 2 12
7 c * DIS 0 11
8 c dhcp REQ 3 11
9 d * DIS 0 0
10 d dhcp REQ 4 20
11 a dhcp REQ 1 20
12 c dhcp REQ 3 20
13 e * DIS 0 0
14 e dhcp REQ 2 0
15 b dhcp REQ 2 25
16 b * DIS 0 0
 */
/*
4 70 100 50 dhcp
6
5 a * 0FR 2 100
10 b * DIS 0 70
15 b dhcp2 REQ 4 60
20 c * DIS 0 70
70 d * DIS 0 120
75 d dhcp REQ 1 125
 */
