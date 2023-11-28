package com.NPU.上海出差.FA算法;

public class test {

    /**
     * maximum frame size<br>
     * 单位：bit
     */
    private int Fmax;

    /**
     * endSystem Source->regulatorLogic->redundancyController->txQueue->mac<br>
     * <br>
     * 在regulatorLogic中，对帧之间的时间间隔进行规整（按照间隔时间，一个一个发送出去）<br>
     * <br>
     * lastSendTime-curTime>=BAG时，才能发送下一个数据包<br>
     * <br>
     * 两个连续的帧Frame之间最小的时间间隔<br>
     * 两个连续的帧的时间间隔>=BAG（=BAG时，为最大带宽）<br>
     * 单位:ms
     */
    private int BAG;

    /**
     * 1、确定性：在每个节点，流量整形（控制BAG）和police unit（监管单元？流量管制）
     * 2、每个虚链路都被指定一个最大带宽
     * 3、
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
