package com.NPU.上海出差.模拟事件仿真.队列;

import java.util.ArrayList;
import java.util.List;

public class Flow {
    /**
     * 独一无二的id
     */
    public String id;

    //==================================================================================================================

    /**
     * 产生的时间
     */
    long generateTime;

    /**
     * 总延迟
     */
    String totalDelay;

    /**
     * 消息长度（与技术延迟有关）
     */
    int messageSize;

    //==================================================================================================================

    /**
     * 起点
     */
    String source;

    /**
     * 终点
     */
    String dest;

    /**
     * next channel id
     */
    List<String> nextChannelId;

    /**
     * 被handle一次，加1
     */
    int currentIndex = 0;

    public Flow() {
        this.nextChannelId = new ArrayList<>();
    }
}
