package _4.dp;

/**
 * 02多重背包
 * 多重背包：每类物品都有个数限制，第i类物品最多可以装num[i]次
 */
public class KnapSack02 {
    public static int manyPack(int[] w,int[] v,int[] num, int C){
        int N = w.length;
        //初始化动态规划数组
        int[][] dp = new int[N+1][C+1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<C+1;j++){
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(w[i-1] > j)
                    dp[i][j] = dp[i-1][j];
                else{
                    //考虑物品的件数限制(当前物品的数量，背包能够装下的数量)
                    int maxV = Math.min(num[i-1],j/w[i-1]);
                    for(int k=0;k<maxV+1;k++){
                        dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-k*w[i-1]]+k*v[i-1]);
                    }
                }
            }
        }
        /*//则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        int j=V;
        String numStr="";
        for(int i=N;i>0;i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            while(dp[i][j]>dp[i-1][j]){
                numStr = i+" "+numStr;
                j=j-weight[i-1];
            }
            if(j==0)
                break;
        }*/
        return dp[N][C];
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3, 2}; //占据容量
        int[] v = {12, 10, 20, 15}; //物品价值
        int[] n = {1, 2, 3, 4}; //物品数量
        System.out.println(manyPack(w, v, n,5));
    }
}
