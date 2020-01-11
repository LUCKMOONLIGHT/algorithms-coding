package _4.dp;

/**
 * 03完全背包
 * 完全背包：每类物品可以无限次装进包内
 */
public class KnapSack03 {
    /**
     * 01背包问题是在前一个子问题（i-1种物品）的基础上来解决当前问题（i种物品），向i-1种物品时的背包添加第i种物品；
     * 而完全背包问题是在解决当前问题（i种物品）, 向i种物品时的背包添加第i种物品
     * 因此这里是f[i][y-weight[i]]+value[i], 而不是f[i-1][y-weight[i]]+value[i]
     */
    public static String completePack(int[] weight,int[] value, int C){
        int N = weight.length;
        //初始化动态规划数组
        int[][] dp = new int[N+1][C+1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<C+1;j++){
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(weight[i-1] > j)
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-weight[i-1]]+value[i-1]); //向i种物品时的背包添加第i种物品
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][C];
        int j=C;
        String numStr="";
        for(int i=N;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            while(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-weight[i-1];
            }
            if(j==0)
                break;
        }
        return numStr;
    }

    /**
     * 完全背包的第二种解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用顺序来实现
     */
    public static int completePack2(int[] weight,int[] value, int C){
        int N = weight.length;
        //动态规划
        int[] dp = new int[C+1];
        for(int i=1;i<N+1;i++){ // 遍历物品的容量

            for(int j=weight[i-1];j<C+1;j++){ //顺序实现,从当前物品的容量开始到最大容量
                dp[j] = Math.max(dp[j-weight[i-1]]+value[i-1],dp[j]);
            }
        }
        return dp[C];
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3, 2}; //占据容量
        int[] v = {12, 10, 20, 15}; //物品价值
        System.out.println(completePack(w, v, 5));
    }
}
