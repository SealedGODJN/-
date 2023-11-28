package com.NPU.上海出差.模拟事件仿真.队列;

public class Flow {
    /**
     * 独一无二的id
     */
    String id;

    /**
     * 当前加入下一队列的延迟
     */
    int currentDelay;

    /**
     * 总延迟
     */
    int totalDelay;

    /**
     * 消息长度
     */
    int messageSize;
    /**
     * 起点
     */
    String origin;

    /**
     * 终点
     */
    String dest;
}
