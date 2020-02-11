package _4.dp;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * coins = [1, 2, 5], amount = 11   3
 * 11 = 5 + 5 + 1
 *
 *
 */
public class coinChange {
    //518. 零钱兑换 I    -  最少硬币数

    /**
     * 可以看成是一个完全背包问题。用一个完全背包装满指定的金额
     * 只有能够凑成dp[j-coin],才能凑成dp[j] wordBreak
     * dp[j] 总金额为j所需要的最少硬币个数
     * 状态转移方程为 dp[j] = Math.min(dp[j], dp[j-coins[i]]+1)
     */
    public int coinChangeI(int[] coins, int amount) {
        if(coins == null || coins.length == 0) return 0; //边界条件判断
        if(amount == 0) return 0;
        int[] dp  = new int[amount + 1];
        Arrays.fill(dp,1,dp.length,Integer.MAX_VALUE); //初始化数组  dp[0]=0
        for(int i=0;i<coins.length;i++){  //遍历每一个硬币
            for(int j=coins[i];j<=amount;j++){ //从硬币面值到目标值amount
                if(dp[j - coins[i]] != Integer.MAX_VALUE){ //条件判断：当能够凑成dp[j-coin]时，dp[j]一定能够凑成
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);//状态转移方程：使用的情况下和使用的情况下的最小硬币数
                }
            }
        }
        if(dp[amount] != Integer.MAX_VALUE)
            return dp[amount];
        return -1;
    }

    /**
     * 518. 零钱兑换 II   - 硬币组合数
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * 示例 1: 输入: amount = 5, coins = [1, 2, 5] 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * d[i] 代表，最多 i 元钱时可兑换的分配方案数量;
     * d[0] 时为初始条件，表示0元可以用0元兑换1次，所以 d[0] = 1
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeII(int[] coins, int amount) {
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;//表示0元：不使用硬币的组合数(1) + 使用硬币的组合数(0)
        for (int coin : coins) { // 遍历所有的硬币数
            for (int i = coin; i <= amount; i++) { //完全背包问题，顺序遍历 从硬币数到目标数
                dp[i] += dp[i - coin]; //当前硬币额度为coin,1.不使用它的情况下组合数为dp[i] 2.使用它的情况下的组合数为dp[i-coin]  相加得到所有的组合数
            }
        }
        return dp[amount];
    }
}
