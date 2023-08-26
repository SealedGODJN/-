package com.NPU.考试0531.第四题debug;

public enum State {
    // 未分配，待分配，占用，过期
    // 处于未分配状态的 IP 地址没有占用者，而其余三种状态的 IP 地址均有一名占用者。
    NO, WAIT, USED, OUT
}
