package com.NPU.上海出差.模拟事件仿真.队列;

public class Flow {
    /**
     * 独一无二的id
     */
    String id;

    /**
     * 产生的时间
     */
    long generateTime;

    /**
     * 总延迟
     */
    long totalDelay;

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
