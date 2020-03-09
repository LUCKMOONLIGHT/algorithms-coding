package _12.Math;

//面试题60. n个骰子的点数
//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率
//你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率
//输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
//输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
public class twoSum {
    //分析：1.投掷 n 个骰子，所有点数出现的总次数是 6^n，因为一共有 n 枚骰子，每枚骰子的点数都有 6 种可能出现的情况
    //问题转换：2.计算出投掷完 n 枚骰子后每个点数出现的次数

    //动态规划
    //1.表示状态
    //2.找出状态转移方程
    //3.边界处理


    //找规律：
    //1、定义一个pd，长度为 n * 6 + 1；0的位置录入1（代表没有骰子的时候，概率为1）；
    //2、每次增加一个骰子，若当前骰子个数为 i ，更新pd数组（从i到 6i 的位置）。
    //例如：已经计算出一个骰子的情况，pd[0~6]信息可以确定为[1,1/6,1/6,1/6,1/6,1/6,1/6],增加一个骰子的情况下更新pd，
    //pd需要更新的位置信息为pd[2~12]，设当前位置为j(2 <= j <= 12)，遍历已知一个骰子情况下更改的的pd[1~6]的信息，
    // 计算pd[j]的信息，设遍历位置为k，条件为k < j（只有k小于j的情况下才有可能存在：k + 一个小于6的数 = j）
    // && k <= 6（遍历直到一个骰子情况下的pd记录的最终index）。
    //3、最终将pd的[n, n6]的信息结果返回即可
    //1ms  37.9MB
    public double[] twoSum(int n) {
        double[] pd = new double[n * 6 + 1];
        pd[0] = 1;
        for (int i = 1; i <= n; i++) {   //骰子个数,每次增加一个骰子
            for (int j = i * 6; j >= i; j--) {  //骰子点数
                pd[j] = 0; //清空记录
                for (int k = i - 1; k < j && k <= (i - 1) * 6; k++) {  //
                    if (j - k <= 6) {
                        pd[j] += pd[k] * 1.0 / 6;
                    }
                }
            }
        }
        double[] ans = new double[n * 6 - n + 1];
        for (int i = 0; i < ans.length; i++) ans[i] = pd[i + n];
        return ans;
    }


    //dp[i][j]  i个骰子点数为j的次数
    public double[] twoSumII(int n) {
        final int face = 6;  //骰子面数
        final int pointNum = face * n; //骰子总和数
        final double totalNum = Math.pow(6, n); //总方案数
        int[][] dp = new int[n + 1][pointNum + 1]; //i个骰子点数为j的次数

        for (int i = 1; i <= face; i++) //初始化，一个骰子每一面的次数都为1
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++) //从2个骰子开始，到n个骰子
            for (int j = i; j <= pointNum; j++)     //使用 i 个骰子最小点数为 i ，计算不同的和j，出现的次数。j最小为i，最大为6n
                for(int k=1;k<=6;k++)///计算和j出现的次数为第i堆从1~6，和前i-1堆，j-（1~6）出现的次数
                    ////dp[i][j]=dp[i-1][j-1]+dp[i-1][j-2]+dp[i-1][j-3]+dp[i-1][j-4]+dp[i-1][j-5]+dp[i-1][j-6]
                    if(j-k>=0) dp[i][j] += dp[i - 1][j - k];

        double[] res = new double[n * 5 + 1];
        int index = 0;
        for (int i = n; i <= pointNum; i++) { //最小点数n，最大点数opintNum
            res[index++] = dp[n][i] / totalNum;
        }
        return res;
    }

    public static void main(String[] args) {
        twoSum twoSum = new twoSum();
        twoSum.twoSum(2);
    }
}
