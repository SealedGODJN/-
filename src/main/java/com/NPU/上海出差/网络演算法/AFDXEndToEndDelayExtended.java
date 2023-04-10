package com.NPU.上海出差.网络演算法;

import java.util.Arrays;

/**
 *
 */
public class AFDXEndToEndDelayExtended {

    public static void main(String[] args) {
        // 输入参数：流的流量参数，以及相关的路径和资源信息
        int[] flowArrivalRate = {300, 200, 100}; // 流的到达速率（单位：bit/s）
        int[] flowMaxSize = {1200, 800, 400}; // 每个流的最大帧大小（单位：bit）
        int[] serverServiceRate = {1000, 1500, 2000}; // 每个服务器的服务速率（单位：bit/s）
        int[] virtualLinkBandwidth = {1000, 1500, 2000}; // 每个虚拟链路的带宽（单位：bit/s）
        int[][] flowToServerMapping = { // 流到服务器的映射关系，用二维数组表示
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        int[][] flowToVirtualLinkMapping = { // 流到虚拟链路的映射关系，用二维数组表示
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        // 计算端到端延迟上界
        double[] endToEndDelayBounds = calculateEndToEndDelayBounds(flowArrivalRate, flowMaxSize, serverServiceRate, virtualLinkBandwidth, flowToServerMapping, flowToVirtualLinkMapping);
        System.out.println("端到端延迟上界: " + Arrays.toString(endToEndDelayBounds));
    }

    public static double[] calculateEndToEndDelayBounds(int[] flowArrivalRate, int[] flowMaxSize, int[] serverServiceRate, int[] virtualLinkBandwidth, int[][] flowToServerMapping, int[][] flowToVirtualLinkMapping) {
        int flowCount = flowArrivalRate.length;
        int serverCount = serverServiceRate.length;
        int virtualLinkCount = virtualLinkBandwidth.length;
        double[] endToEndDelayBounds = new double[flowCount];

        for (int i = 0; i < flowCount; i++) {
            double maxDelayForFlow = 0;

            for (int j = 0; j < serverCount; j++) {
                if (flowToServerMapping[i][j] == 1) {
                    double flowArrival = 1.0 / flowArrivalRate[i];
                    double serverService = 1.0 / serverServiceRate[j];
                    double maxDelayForServer = flowMaxSize[i] * (serverService - flowArrival);

                    maxDelayForFlow += maxDelayForServer;
                }
            }

            for (int j = 0; j < virtualLinkCount; j++) {
                if (flowToVirtualLinkMapping[i][j] == 1) {
                    double flowArrival = 1.0 / flowArrivalRate[i];
                    double virtualLinkService = 1.0 / virtualLinkBandwidth[j];
                    double maxDelayForVirtualLink = flowMaxSize[i] * (virtualLinkService - flowArrival);
                    maxDelayForFlow += maxDelayForVirtualLink;
                }
            }

            endToEndDelayBounds[i] = maxDelayForFlow;
        }

        return endToEndDelayBounds;
    }
}
