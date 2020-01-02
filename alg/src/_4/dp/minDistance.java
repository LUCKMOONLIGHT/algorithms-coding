package _4.dp;

import java.util.Map;

/**
 * 最小的编辑距离 - hard
 * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode/
 * 编辑距离算法被数据科学家广泛应用，是用作机器翻译和语音识别评价标准的基本算法。
 * 子问题：D[n][m] 表示输入单词长度为 n 和 m 的编辑距离
 * D[i][j] 表示 word1 的前 i 个字母和 word2 的前 j 个字母之间的编辑距离
 *我们获得 D[i-1][j]，D[i][j-1] 和 D[i-1][j-1] 的值之后就可以计算出 D[i][j]
 * 如果两个子串的最后一个字母相同，word1[i] = word2[i] 的情况下：
 *
 * D[i][j] = D[i - 1][j - 1]
 *
 * 否则，word1[i] != word2[i] 我们将考虑替换最后一个字符使得他们相同：
 *
 * D[i][j] = 1 + min(D[i - 1][j], D[i][j - 1], D[i - 1][j - 1])
 *
 * dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作
 *
 *
 * dp优化
 * 1.dp[][]备忘录 自底向上
 * 2.dp table
 */
public class minDistance {
    public static int minDistance(String word1, String word2) {
        //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i< word1.length() + 1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j< word2.length() + 1; j++){
            dp[0][j] = j;
        }
        for(int i = 1; i< word1.length() + 1; i++){
            for(int j = 1; j< word2.length() + 1; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){  //相等时，向前移动，不花费距离
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = (Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1])) + 1;//不相等时，插入，删除，替换的最小距离
                    // 插入 dp[i][j-1] 直接在 s1[i] 插入一个和 s2[j] 一样的字符,那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
                    // 删除 dp[i-1][j] 直接把 s[i] 这个字符删掉,前移 i，继续跟 j 对比
                    // 替换 直接把 s1[i] 替换成 s2[j]，这样它俩就匹配了,同时前移 i，j 继续对比
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
