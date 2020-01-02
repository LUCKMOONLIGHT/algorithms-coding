package _4.dp;

/**
 * 最长回文子串：动态规划
 * 子串：必须连续，不能跳跃
 * 子序列：可以不连续
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 1. 当j - i >= 2  s.charAt(i) == s.charAt(j)
 * 2. 当j - i < 2   dp[i + 1][j - 1]= True s.charAt(i) == s.charAt(j)
 * 3. 保存最大的res
 *
 * 动态规划的递归方程
 * f(i, j)= true , i = j
 *          s[i] = s[j] j=i+1 偶数
 *          s[i] = s[j] and f(i+1,j-1) j>i+1 奇数
 */
public class longestPalindrome {
    public String longestPalindrome(String s){
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        int max = 0;
        String res = null;
        boolean[][] dp = new boolean[n][n];
        for (int j = 0;j<n;j++){
            for (int i = 0;i<=j;i++){
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);
                /**
                 * dp[i][j] 表示i到j之间的文字是否为回文
                 * 1.当 s.charAt(i) == s.charAt(j) 两端相等 且 dp[i + 1][j - 1] 内缩也相等时
                 * 2.当 s.charAt(i) == s.charAt(j) 两端相等 且 长度为 1 时
                 */
                if(dp[i][j] && (j - i + 1 >= max)){
                    max = j - i + 1;
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }
}
