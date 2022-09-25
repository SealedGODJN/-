package com.leetcode.dp;

public class dp_343 {
    /**
     * 1) dp[i]含义
     * dp[i]是拆分为i个数的最大乘积[错误]
     * dp[i] i被拆分的最大乘积
     * <p>
     * 2) dp递推公式
     * 其实可以从1遍历j，然后有两种渠道得到dp[i].
     * <p>
     * 一个是j * (i - j) 直接相乘。
     * <p>
     * 一个是j * dp[i - j]，相当于是拆分(i - j)，对这个拆分不理解的话，可以回想dp数组的定义
     * <p>
     * j * (i - j) 是单纯的把整数拆分为两个数相乘，而j * dp[i - j]是拆分成两个以及两个以上的个数相乘。
     * <p>
     *
     * <p>
     * 数学证明：
     * 如果 j=1，则 1×(i−1)=i−1，
     * 当 i=2 或 i=3 时有 dp[i]=i−1，
     * 当 i≥4 时有 dp[i]≥i>i−1，
     * 显然当 i≥4 时取 j=1 不可能得到最大乘积，因此 j=1 时是不需要考虑的。
     * </p>
     * <p>
     * 如果 j≥4，dp[i] 是否可能等于 j×(i−j)？
     * 当 i 固定时，要使得 j×(i−j) 的值最大，j 的值应该取 j=i//2，这里的 // 表示整数除法。
     * 当 j≥4 时，若要满足 j=i/2，则 i≥8，此时 i−j≥4，利用上述结论，dp[i−j]≥i−j，因此 j×dp[i−j]≥j×(i−j).
     * 由此可见，当 j≥4 时，计算 dp[i] 只需要考虑j×dp[i−j]，不需要考虑 j×(i−j)。
     * </p>
     * <p>
     * 由此可以对方法一的动态规划进行优化。
     *
     * 边界情况是 n=2，此时唯一的拆分方案是 2=1+1，最大乘积是 1×1=1。
     *
     * 当 i≥3 时，状态转移方程如下：
     *  dp[i] = max( 2 × (i-2), 2 × dp[i-2], 3 × (i-3), 3 × dp[i-3])
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * </p>
     *
     * 3) 初始化
     * dp[0] dp[1] 没有意义
     * <p>
     * 4) 确定遍历顺序
     * 先把dp[i-1]算好，和dp[i]的两次拆分、多次拆分进行大小比较
     * 根据递推公式，i 3->n
     * j 1->i
     * <p>
     * 5) 举例子
     *
     * @return
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        if (n <= 1) return n;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 拆分两次 和 拆分多次 进行比较
                int temp = Math.max(j * (i - j), j * dp[i - j]);
                dp[i] = Math.max(dp[i], temp);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
}
