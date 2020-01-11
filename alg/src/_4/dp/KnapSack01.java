package _4.dp;

/**
 * 01背包问题
 * 0-1背包：每类物品最多只能装一次
 * 给定n个重量为w，价值为v的物品和容量为c的背包，求这个物品中一个最有价值的子集，使得在满足背包的容量的前提下，包内的总价值最大
 *
 * dp[i][j]表示前i件物品能装入容量为j的背包中的物品价值总和的最大值
 *
 *
 * 状态转移：
 * for 第i个物品
 *     for 第j个容量大小
 *         如果放不下 weight[i-1] > j  : dp[i][j] = dp[i-1][j]
 *         如果放得下: dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i-1]]+value[i-1]) 放或者不放
 */
public class KnapSack01 {
    public static int knapSackI(int[] w, int[] v, int C){
        int N = w.length;
        //初始化动态规划数组
        int[][] dp = new int[N+1][C+1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){ //每件物品
            for(int j=1;j<C+1;j++){//背包容量
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(w[i-1] > j)//不满足条件不能装入背包
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i-1]]+v[i-1]);
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][C];
        //逆推找出装入背包的所有商品的编号
        int j=C;
        String numStr="";
        for(int i=N;i>0;i--){ //物品数量
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-w[i-1]; //减掉i-1的空间容量
            }
            if(j==0) {
                break;
            }
        }
        System.out.println(numStr);
        return dp[N][C];
    }
    /**
     * 0-1背包的优化解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用逆序来实现
     */
    public static int knapSackII(int[] w, int[] v, int C) {
        //动态规划
        int N = w.length;
        int[] dp = new int[C+1];
        for(int i=1;i<N+1;i++){//遍历物品的容量
            //逆序实现，防止被覆盖
            for(int j=C;j>=w[i-1];j--){//逆序 背包的容量 - 物品所需容量
                dp[j] = Math.max(dp[j-w[i-1]]+v[i-1],dp[j]);
            }
        }
        return dp[C];
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3, 2}; //占据容量
        int[] v = {12, 10, 20, 15}; //物品价值
        System.out.println(knapSackII(w, v, 5));
    }
}
