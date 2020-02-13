package _4.dp;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139.单词拆分I - 动态规划(将字符串拆分成为子问题)
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * dp[i] : 表示字符串0-i是否满足被空格拆分后在字典中出现
 * 从 0 - end - 1，依次迭代判断
 * 当空格前面的子问题为true，且字符串后部分包含在字典中，则dp[i] = true;
 * dp[j] && word.contains(s.substring(j, i))
 */
public class wordBreak {
    public boolean wordBreak(String s, List<String> wordDict){
        int n = s.length();
        boolean[] dp = new boolean[s.length() + 1];  //dp[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
        dp[0] = true;
        for (int i = 1; i <= n; i++) { //字符长度i
            for (int j = 0; j < i; j++) { //每个拆分位置j
                if(dp[j] && wordDict.contains(s.substring(j, i))){ //0-j个字符是否在字典中出现过，剩余字符串j-i是否在字符串中出现过
                    dp[i] = true;//拆分字串全部在dict中出现过
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
