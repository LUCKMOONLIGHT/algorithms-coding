package _4.dp;

/**
 * 221. 最大正方形 - 动态规划
 * https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 二维动态规划  if true dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
 * 一维动态规划  if true dp[j]=min(dp[j-1],dp[j],prev)dp[j]=min(dp[j−1],dp[j],prev)
 * 时间复杂度：O(mn)
 * 空间复杂度：O(n)，使用了一个一维数组 dp。
 *
 *
 */
public class maximalSquare {

    //二维矩阵
    //if 1 ： dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
    //O(mn) O(mn)
    public int maximalSquareI(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    //一维矩阵 O(mn) O(n)
    //if true dp[j]=min(dp[j-1],dp[j],prev) dp[j]=min(dp[j−1],dp[j],prev)
    public int maximalSquareII(char[][] martrix){
        int row = martrix.length;
        int col = row > 0 ? martrix[0].length : 0;
        int[] dp = new int[col + 1];
        int prev = 0;
        int maxlen = 0;
        for (int i=1;i<=row;i++){
            for (int j=1;j<=col;j++){
                int tmp = dp[j];
                if (martrix[i - 1][j - 1] == '1'){
                    dp[j] = Math.min(Math.min(dp[j - 1], prev),dp[j]) + 1;
                    maxlen = Math.max(dp[j], maxlen);
                }else {
                    dp[j] = 0;
                }
                prev = tmp;
            }
        }
        return maxlen * maxlen;
    }
}
