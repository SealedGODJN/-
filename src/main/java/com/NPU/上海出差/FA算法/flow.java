package com.NPU.上海出差.FA算法;

import java.util.List;

/**
 * 流f(i)在节点h，可能有k个packet排在它之前
 * <p>
 * 1）Smin(h,i)是从节点h入口到出口最短的处理时间
 * 2）Smax(h,i)是从节点h入口到出口（前面k个packet排完队）的处理时间
 */
public class flow {

    /**
     * 针对每个node 都有一个Smin
     */
    private List<Integer> Smin;

    /**
     * 针对每个node 都有一个Smax
     */
    private List<Integer> Smax;


}
