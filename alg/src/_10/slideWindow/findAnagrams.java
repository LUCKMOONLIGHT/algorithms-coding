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

    //双指针，右指针加入source数组，左指针删除source数组
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || p == null || s.length() < p.length()) return res;
        int[] target = new int[256];
        int[] source = new int[256];

        for(char c:p.toCharArray()) target[c]++;
        int l = 0,r = 0;
        while(r<s.length()){
            if(!valid(target, source)) source[s.charAt(r++)]++; //如果target，source不是异位词，继续添加r位置的字符，进行计数
            while(valid(target, source) || (r - l) > p.length()){//当是异位词，且长度大于要求时，添加保存结果，并减去最左边的数字，缩小窗口
                if(valid(target, source)) res.add(l);
                source[s.charAt(l++)]--;
            }
        }
        return res;
    }

    //检查target与source的异动字符是否相等
    public boolean valid(int[] target, int[] source){
        for(int i=0;i<target.length;i++){//减少source是否包含source中的数字
            if(target[i] != source[i]) return false;
        }
        return true;
    }
}
