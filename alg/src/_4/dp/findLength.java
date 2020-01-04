package _4.dp;

/**
 * 718. 最长重复子数组
 *  给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 逆序floop  
 *  A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 3
 *  [3, 2, 1]
 */
public class findLength {
    public int findLength(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int res = 0;
        if(A == null || n == 0 || B == null || m == 0) return 0;
        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
