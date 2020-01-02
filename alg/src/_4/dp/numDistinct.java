package _4.dp;

/**
 * 115. 不同的子序列 - 动态规划
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 *https://leetcode-cn.com/problems/distinct-subsequences/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-27/
 *
 * dp[i][j] 代表 T 前 i 字符串可以由 S j 字符串组成最多个数.
 *
 * 所以动态方程:
 *
 * 当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
 *
 * 当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
 *
 */
public class numDistinct {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) dp[0][j] = 1;
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[t.length()][s.length()];
    }
    public int numDistinct2(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();
        int[] dp = new int[s_len + 1];
        for (int i = 0; i <= s_len; i++) {
            dp[i] = 1;
        }
        for (int t_i = 1; t_i <= t_len; t_i++) {
            int pre = dp[0];
            dp[0] = 0;
            for (int s_i = 1; s_i <= s_len; s_i++) {
                int temp = dp[s_i];
                if (t.charAt(t_i - 1) == s.charAt(s_i - 1)) {
                    dp[s_i] = dp[s_i - 1] + pre;  //对应于两种情况，选择当前字母和不选择当前字母
                } else {
                    dp[s_i] = dp[s_i - 1]; //不选择当前字母
                }
                pre = temp;
            }
        }
        return dp[s_len];
    }

}
