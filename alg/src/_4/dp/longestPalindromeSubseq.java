package _4.dp;

/**
 * 最长回文子序列- 动态规划（从底至上） db[i][j]代表从字符索引i到字符索引j的最长回文子序列
 * 子序列可以不连续的，可以跳过某些单词，子串是必须连续的
 *
 * 思路：
 * 从后向前两个for循环迭代，判断局部子串的最长子序列
 * 如果首位相等，那么长度等于上一个长度+2，同时兼容奇数和偶数的情况
 *
 * 状态
 * f[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少。
 * 转移方程
 * 如果 s 的第 i 个字符和第 j 个字符相同的话
 * f[i][j] = f[i + 1][j - 1] + 2
 *
 * 如果 s 的第 i 个字符和第 j 个字符不同的话
 * f[i][j] = max(f[i + 1][j], f[i][j - 1])
 * 然后注意遍历顺序，i 从最后一个字符开始往前遍历，j 从 i + 1 开始往后遍历，这样可以保证每个子问题都已经算好了。
 *
 * 初始化
 * f[i][i] = 1 单个字符的最长回文序列是 1
 *
 * 结果
 * f[0][n - 1]
 *
 */
public class longestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if(n < 2) return n;
        int[][] dp = new int[n][n];
        for(int i=n-1;i>=0;i--){
            dp[i][i] = 1;
            for(int j=i+1;j<n;j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }
}
