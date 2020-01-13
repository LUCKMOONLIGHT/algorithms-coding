package _15.series.Game;

import java.util.Scanner;

//博弈问题 - 分金子（360公司2017春招真题）
//A、B两伙马贼意外地在一片沙漠中发现了一处金矿，双方都想独占金矿，但各自的实力都不足以吞下对方，经过谈判后，双方同意用一个公平的方式来处理这片金矿。
// 处理的规则如下：他们把整个金矿分成n段，由A、B开始轮流从最左端或最右端占据一段，直到分完为止。
//马贼A想提前知道他们能分到多少金子，因此请你帮忙计算他们最后各自拥有多少金子?（两伙马贼均会采取对己方有利的策略）
public class stoneGame {
    //样例输入
    //2 测试数据的组数
    //6 数组长度
    //4 7 2 9 5 2    每段金矿的含金量
    //10
    //140 649 340 982 105 86 56 610 340 879
    public static void main(String[] args){
        Scanner sca = new Scanner(System.in);
        int m = sca.nextInt();
        for(int k=1; k<=m; k++){
            int n = sca.nextInt();
            int[] array = new int[n+1];
            for(int i=1; i<=n; i++){
                array[i] = sca.nextInt(); //输入参数
            }
            int[] sum = new int[n+1];  //当前数组长度为i的总和
            sum[0] = 0;
            for(int i=1; i<=n; i++){
                sum[i] = sum[i-1] + array[i];
            }
            //dp[i][j]表示当某个人面对[i, j]区间先取时，能得到的最大值，很明显，答案就是dp[1][n]和sum[n]-dp[1][n]
            int[][] dp = new int[n+2][n+2];
            for(int i=0; i<=n-1; i++){
                for(int j=1; j<=n-i; j++){
                    //当前用户从j+0到j+i的最优值等于线段j+0到j+i的总和减去分别包含两端的最小值
                    dp[j][j+i] =sum[j+i] - sum[j-1] - Math.min(dp[j+1][j+i], dp[j][j+i-1]);
                }
            }
            System.out.println("Case #" + k + ": " + dp[1][n] + " " + (sum[n] - dp[1][n]));
        }
        sca.close();
    }
}
