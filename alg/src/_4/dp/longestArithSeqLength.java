package _4.dp;

import java.util.HashMap;
import java.util.Map;

//1027. 最长等差数列 | 最长等差子串
//动态规划： dp[i][j] 以数字i结尾j为等差值的最长等差数列 其中diff等差值可能为正负，范围无上下限，这里设定上下限为±10000 dp[i][j]最小为2
//输入：[3,6,9,12]
//输出：4
//输入：[9,4,7,2,10]
//输出：3
//输入：[20,1,15,3,10,5,8]
//输出：4    [20,15,10,5]
public class longestArithSeqLength {
    public int longestArithSeqLength(int[] A){
        int[][] dp = new int[A.length][20001]; //设定diff差值区间为正负10000
        if (A.length < 2)
            return 0;
        int max = 0;
        for (int i = 1 ; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                int step = A[i] - A[j] + 10000; //将插值移位,说明j，i之间等差step
                if (dp[j][step] > 0) {//存在等差为step的数列子串
                    dp[i][step] = Math.max(dp[j][step] + 1, dp[i][step]);//等同于最长上升子序列的思路
                } else {
                    dp[i][step] = 2; //最短为2
                }
                max = Math.max(max, dp[i][step]);
            }
        }
        return max;
    }
}
