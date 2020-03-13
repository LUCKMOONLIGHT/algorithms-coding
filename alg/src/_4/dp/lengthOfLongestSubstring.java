package _4.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串 - 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 子串 - 要求连续
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
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int start = 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                start = Math.max(start, map.get(c)); //最近一次不重复出现的起始位置
            }
            res = Math.max(res, i - start + 1);//最长不重复字符
            map.put(c, i+1);
        }
        return res;
    }


    //面试题50. 第一个只出现一次的字符
    //思路：map标记出现次数，for循环找第一次出现的字符
    public char firstUniqChar(String s) {
        if(s == null || s.length() == 0 || "".equals(s)) return ' ';
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.get(c) == 1) return c;
        }
        return ' ';
    }

    public char firstUniqCharII(String s) {
        if (s.equals("")) return ' ';
        //创建‘a'-'z'的字典
        int[] target = new int[26];
        //第一次遍历，将字符统计到字典数组
        for (int i = 0; i < s.length(); i++) {
            target[s.charAt(i) - 'a']++;
        }
        //第二次遍历，从字典数组获取次数
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }

        return ' ';
    }
}
