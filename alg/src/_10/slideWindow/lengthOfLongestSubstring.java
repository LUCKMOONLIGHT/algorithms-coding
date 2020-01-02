package _10.slideWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长字串
 */
public class lengthOfLongestSubstring {

    /**
     * HashSet保存元素
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int fast = 0, slow = 0, len = s.length();
        int res = 0;
        while (slow < len && fast < len) {
            if (!set.contains(s.charAt(fast))) {
                set.add(s.charAt(fast++));
                res = Math.max(res, fast - slow);
            } else {
                set.remove(s.charAt(slow++));
            }
        }
        return res;
    }


    /**
     * hashmap保存元素出现的位置
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int i = 0, len = s.length(), res = 0;
        Map<Character, Integer> hashmap = new HashMap<>();
        for (int j = 0; j < len; j++) {
            //使用hashmap作为存储，每次i的值取最大的
            if (hashmap.containsKey(s.charAt(j))) {//如果包含的话，说明重复了
                i = Math.max(hashmap.get(s.charAt(j)), i);//最大的不重复的字符序列的开始位置
            }
            //计算下标的距离，记录下标的绝对物理index
            res = Math.max(res, j - i + 1);
            hashmap.put(s.charAt(j), j + 1);
        }
        return res;
    }

    /**
     * 数组保存元素出现过的位置
     * int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
     * int [128] 用于ASCII码
     * int [256] 用于扩展ASCII码
     */
    public int lengthOfLongestSubstring3rd(String s) {
        int i = 0, len = s.length(), res = 0;
        int[] arr = new int[128];
        for (int j = 0; j < s.length(); j++) {
            i = Math.max(arr[s.charAt(j)], i);//最大的不重复的字符序列的开始位置
            res = Math.max(j - i + 1, res);//最大的不重复的字符序列长度
            arr[s.charAt(j)] = j + 1;//保存字符出现的最新位置
        }
        return res;
    }
}
