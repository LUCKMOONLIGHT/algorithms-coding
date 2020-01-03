package _8.greed;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 贪心算法：1.局部最优解 2.全局最优解
 *
 * 第一题是只进行一次交易，相当于 k = 1；  找当前最小值  当前最大值=max(当前值-当前最小值)
 * 第二题是不限交易次数，相当于 k = +infinity（正无穷）；  只要是上升就能盈利 prices[i-1] < prices[i]
 * 第三题是只进行 2 次交易，相当于 k = 2；
 * 剩下两道也是不限次数，但是加了交易「冷冻期」和「手续费」的额外条件，其实就是第二题的变种
 *
 */
public class maxProfit {
    /**
     * 一次交易
     * 1.找最小价格
     * 2.全局最优解:当前值-最小价格
     */
    public int maxProfitI(int[] prices){
        if (prices == null || prices.length == 0) return 0;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int num:prices){
            minPrice = Math.min(minPrice, num);
            maxProfit = Math.max(maxProfit, num - minPrice);
        }
        return maxProfit;
    }

//    int maxProfit_k_1(int[] prices) {
//        int n = prices.length;
//        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
//        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
//            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
//            // dp[i][1] = max(dp[i-1][1], -prices[i])
//            dp_i_1 = Math.max(dp_i_1, -prices[i]);
//        }
//        return dp_i_0;
//    }


    /**
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 1.多次买卖一支股票，当是上升的时候，都能赚；即上升的幅度
     */
    public int maxProfitII(int[] prices){
        if (prices == null || prices.length == 0) return 0;
        int maxProfit = 0;
        for (int i=1;i<prices.length;i++){
            if (prices[i-1] < prices[i]){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }

    /**
     * 状态转移框架 第i天|k次交易（buy的时候把k减一）|0未持有1持有
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = Integer.MIN_VALUE
     *
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     * 选择 buybuy 的时候，把 kk 减小了 1，当然你也可以在 sellsell 的时候减 11，一样的。
     */

    /**
     * 123. 买卖股票的最佳时机 III
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     */
    public int maxProfitIII(int[] prices){
        if (prices == null || prices.length == 0) return 0;
        int max_k = 2;
        int[][][] dp = new int[prices.length][max_k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    /* 处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[prices.length - 1][max_k][0];
    }

    /**
     * 123. 买卖股票的最佳时机  IV
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     */
    public int maxProfitIV(int max_k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        if (max_k > n / 2)
            return maxProfitII(prices);

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    /* 处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];

    }

    /**
     * 123. 最佳买卖股票时机含冷冻期
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     *
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     *
     */
    public int maxProfitV(int[] prices){
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;

    }

    /**
     * 123.  买卖股票的最佳时机含手续费
     * 每次交易要支付手续费，只要把手续费从利润中减去即可
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
     * 解释：相当于买入股票的价格升高了。
     * 在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
     *
     */
    public int maxProfitVI(int[] prices, int fee){
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }
}
