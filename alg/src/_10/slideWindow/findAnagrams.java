package _10.slideWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * s: "cbaebabacd" p: "abc"
 * [0, 6]
 */
public class findAnagrams {
    /**
     * 参考最小覆盖子串，使用数组保存元素出现次数
     * left right两个指针 check函数，克服异动
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if(s == null || p == null || s.length() < p.length()) return res;
        int[] source = new int[256];
        int[] target = new int[256];
        int left=0, right=0;
        for(char c:p.toCharArray()) target[c]++;
        while(right < s.length()){
            if(!valid(source, target)){
                source[s.charAt(right)]++;
                right++;
            }
            while(valid(source, target) || (right - left) > p.length()){
                if(valid(source, target)){
                    res.add(left);
                }
                source[s.charAt(left)]--;
                left++;
            }
        }
        return res;
    }

    public boolean valid(int[] source, int[] target){
        for(int i=0;i<source.length;i++){
            if(source[i] != target[i]) return false;
        }
        return true;
    }
}
