package _0.array;

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
     * 3. 无重复字符的最长子串
     * 如果字符数超过26个字母的情况下，使用map进行保存character integer
     * 记录最近的一个不重复位置start   "abcabcbb"   012
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
                //如果范围i,j内有重复元素，直接跳出i,j范围，并将i变为j+1
                start = Math.max(start, map.get(c));
            }
            res = Math.max(res, i - start + 1);  //减去多了加回来
            map.put(c, i+1);  //保存当前元素的下一个元素，如果有重复的话，直接跳到起始重复元素的下一个元素
        }
        return res;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring lengthOfLongestSubstring = new lengthOfLongestSubstring();
        lengthOfLongestSubstring.lengthOfLongestSubstringII("abcabcbb");
    }
}
