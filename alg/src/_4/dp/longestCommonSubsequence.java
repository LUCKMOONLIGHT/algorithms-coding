package _4.dp;

/**
 * 1143. 最长公共子序列 - 动态规划
 * 子序列需要考虑i-1,因此dp[n+1][m+1],for i=1 j=1
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 */
public class longestCommonSubsequence {
        public int longestCommonSubsequence(String text1, String text2) {
            int n1 = text1.length();
            int n2 = text2.length();
            int[][] dp = new int[n1 + 1][n2 + 1];
            for(int i=1;i<=n1;i++){
                for(int j=1;j<=n2;j++){
                    if(text1.charAt(i-1) == text2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[n1][n2];
        }
    }
