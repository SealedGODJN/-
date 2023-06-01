package com.NPU.考试0531.第四题;

public class IP {

    /**
     * IP address
     */
    public int IPaddress;

    /**
     * 拥有IP地址的主机
     */
    public String occupier = "";

    /**
     * 状态
     *
     */
    public State status = State.NO;

    /**
     * 过期时刻
     * 默认为0（不过期）
     * 处于待分配和占用状态的 IP 地址拥有一个大于零的过期时刻
     * 在到达该过期时刻时，
     *    若该地址的状态是待分配，
     *      则该地址的状态会自动变为未分配，且占用者清空，过期时刻清零；
     *    否则
     *      该地址的状态会由占用自动变为过期，且过期时刻清零。处于未分配和过期状态的 IP 地址过期时刻为零，即没有过期时刻。
     */
    public int expirationTime = 0;

    public IP(int address) {
        IPaddress = address;
    }
}
