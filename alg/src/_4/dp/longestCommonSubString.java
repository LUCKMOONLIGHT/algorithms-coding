package _4.dp;

/**
 * 最长公共子串 - 动态规划
 * 1.填表法 从顶至下 不相等从0开始计数
 */
public class longestCommonSubString {
    public static int findLSubString(char[] s1,char[] s2) {
    int res = 0;
    int[][] dp = new int[s1.length][s2.length];
    for (int i = 0; i < s1.length; i++) {
        for (int j = 0; j < s2.length; j++) {
            if (s1[i] == s2[j]) {
                dp[i][j] = (i == 0 || j == 0) ? 1 : dp[i - 1][j - 1] + 1;
                res = Math.max(res, dp[i][j]);
            }
            dp[i][j] = 0;
        }
    }
    return res;
    }
}
