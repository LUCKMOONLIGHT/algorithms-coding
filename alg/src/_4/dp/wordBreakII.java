package _4.dp;

import java.util.LinkedList;
import java.util.List;

/**
 * 140.单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 两层迭代i，j
 * 子串i中判断拆分点j组成的子串是否都被包含于dict中
 * 如果被包含了，就将之前保存的子串dp[j]和substring i,j 进行拼接后，保存到list中
 * 将list保存至dp
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(n^3)
 */
public class wordBreakII {
    public List<String> wordBreakII(String s, List<String> wordDict){
        LinkedList<String>[] dp = new LinkedList[s.length() + 1]; //dp[k] 被用来存储用s[0:k−1] 可被拆分成合法单词的句子
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i=1;i<=s.length();i++){
            LinkedList<String> list = new LinkedList<>();
            for (int j=0;j<i;j++){
                if(dp[j].size() > 0 && wordDict.contains(s.substring(j, i))){
                    for (String l:dp[j]){
                        list.add(l + (l.equals("") ? "": " ") + s.substring(j, i)); //将字符串j-i添加到所有s1后面
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }
}
