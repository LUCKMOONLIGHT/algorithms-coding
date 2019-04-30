## 背包问题

问题描述： 
一个背包的总**容量**为V,现在有N类物品,第i类物品的**重量**为weight[i],**价值**为value[i] 
那么往该背包里装东西,怎样装才能使得最终包内物品的总**价值**最大。这里装物品主要由三种装法： 
1、0-1背包：每类物品最多只能**装一次** 

2、多重背包：每类物品都有**个数限制**，第i类物品最多可以装num[i]次               

3、完全背包：每类物品可以**无限次**装进包内



##### **0—1背包** 

思路分析：

首先第i件物品的重量weight[i]必须小于等于容量j才行，即 
1、若weight[i]>j,则第i件物品肯定不能装入容量为j的背包，此时dp[i][j]=dp\[i-1][j] 
2、若weight[i]<=j,则首先明确的是这件物品是可以装入容量为j的背包的，那么如果我们将该物品装入，则有 dp\[i][j]=dp\[i-1][j-weight[i]]+value[i] 

状态转移方程为：

dp[i][j] = (dp\[i-1][j] > (dp\[i-1][j-weight[i]]+value[i]))? dp\[i-1][j]:(dp\[i-1][j-weight[i]]+value[i]) 

```java
/**
     * 0-1背包的优化解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用逆序来实现
     */
    public static int ZeroOnePack2(int V,int N,int[] weight,int[] value){
        //动态规划
        int[] dp = new int[V+1];
        for(int i=1;i<N+1;i++){
            //逆序实现
            for(int j=V;j>=weight[i-1];j--){
                dp[j] = Math.max(dp[j-weight[i-1]]+value[i-1],dp[j]);
            }
        }
        return dp[V];       
    }

```

```java
//多重背包
/**
     * 第三类背包：多重背包
     * 
     * @param args
     */
    public static int manyPack(int V,int N,int[] weight,int[] value,int[] num){
        //初始化动态规划数组
        int[][] dp = new int[N+1][V+1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<V+1;j++){
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(weight[i-1] > j)
                    dp[i][j] = dp[i-1][j];
                else{
                    //考虑物品的件数限制
                    int maxV = Math.min(num[i-1],j/weight[i-1]);
                    for(int k=0;k<maxV+1;k++){
                        dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-k*weight[i-1]]+k*value[i-1]);
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
        return dp[N][V];
    }
```



```java
//三、完全背包
    /**
     * 完全背包的第二种解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用顺序来实现
     */
    public static int completePack2(int V,int N,int[] weight,int[] value){

        //动态规划
        int[] dp = new int[V+1];
        for(int i=1;i<N+1;i++){
            //顺序实现
            for(int j=weight[i-1];j<V+1;j++){
                dp[j] = Math.max(dp[j-weight[i-1]]+value[i-1],dp[j]);
            }
        }
        return dp[V];
    }
```

