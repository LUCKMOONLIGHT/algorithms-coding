package _4.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串 - 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 字串 - 要求连续
 * 无重复 - 使用char数组记录存在的字符
 * 输入: "abcabcbb"
 * 输出: 3
 */
public class lengthOfLongestSubstring {
    /**
     * 如果只有英文字母的话
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringI(String s) {
        if(s.isEmpty()) return 0;
        if(s.trim().isEmpty()) return 1;
        int[] alpha = new int[27];
        for(int i=0;i<s.length();i++){
            alpha[s.charAt(i) - 'a']++;
        }
        int cnt = 0;
        for(int i=0;i<alpha.length;i++){
            if(alpha[i] != 0) cnt ++;
        }
        return cnt;
    }

    /**
     * 如果字符数超过26个字母的情况下，使用map进行保存character integer
     * 记录最近的一个不重复位置start
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringII(String s) {
        int n = s.length(), arr =0;
        Map<Character,Integer> map = new HashMap<>();
        for(int end=0,start=0;end<n;end++){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                start = Math.max(map.get(c),start); //记录最大的不重复的位置
            }
            arr = Math.max(arr,end - start + 1);
            map.put(c,end + 1);//更新存储的位置

        }
        return arr;
    }
}
