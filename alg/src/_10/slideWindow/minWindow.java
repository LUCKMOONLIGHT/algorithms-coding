package _10.slideWindow;

import java.util.HashMap;

/**
 * 最小窗口子序列
 * 给定字符串S和T，在S中寻找最小连续子串W，使得T是W的子序列。如果没有找到返回""，如果找到多个最小长度的子串，返回左 index 最小的
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 *
 *
 */
public class minWindow {
    /**
     * 暴力方法 - O（s * t） O(1)
     * @param S
     * @param T
     * @return
     */
    public String minWindowSubSeq(String S, String T) {
        int min = -1, idx = -1;
        for(int i = 0;i < S.length();i++){
            if(S.charAt(i) != T.charAt(0)) continue;
            int len = check(S,T,i);
            if(len <= 0) break;
            if(min == -1 || len < min){
                idx = i;
                min = len;
            }
        }
        if(min == -1) return "";
        return S.substring(idx, idx + min);
    }
    public int  check(String S, String T, int start) {
        int i=start,j=0;
        while(i < S.length() && j < T.length()){ //子序列不要求连续，故while找到为止
            if(S.charAt(i) == T.charAt(j)){
                j++;
            }
            i++;
        }
        if(j == T.length()) return i - start;
        return -1;
    }

    /**
     * 最小窗口子串
     * @param S
     * @param T
     * @return
     * 左右逼近 - hashmap
     * S = "ADOBECODEBANC", T = "ABC"
     *  * Output: "BANC"
     *
     */
    public String minWindowSubString(String s, String t) {
        String string = "";
        //hashmap来统计t字符串中各个字母需要出现的次数
        HashMap<Character,Integer> map = new HashMap<>();
        for (char c : t.toCharArray())
            map.put( c, map.containsKey(c) ? map.get(c)+1 : 1);
        //用来计数 判断当前子字符串中是否包含了t中全部字符
        int count = 0;
        //记录当前子字符串的左右下标值
        int left = 0;
        int right = 0;
        //记录当前最小子字符串的大小以及第一最后字符的下标值
        int min = Integer.MAX_VALUE;
        int minLeft = 0;
        int minRight = 0;
        for (; right < s.length() ; right++) {
            char temp = s.charAt(right);
            if (map.containsKey(temp)){//向后遍历出所包含t的字符串
                count = map.get(temp) > 0 ? count+1 : count;
                map.put(temp,map.get(temp)-1);
            }
            while (count == t.length()){//得出一个符合条件的子字符串
                if (right-left < min){//更新min minLeft minRight 信息
                    min = right - left;
                    minLeft = left;
                    minRight = right;
                }
                char c = s.charAt(left);
                if (map.containsKey(c)){//向左收缩 判断所删减的字符是否在map中
                    if (map.get(c) >= 0)count --;//count--时需要判断一下是否需要--
                    map.put(c,map.get(c)+1);
                }
                left++;
            }
        }
        return min == Integer.MAX_VALUE ?  "" : s.substring(minLeft,minRight+1);
    }

    /**
     * 76. 最小覆盖子串
     *给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     *
     * 初始，left指针和right指针都指向S的第一个元素.
     *
     * 将 right 指针右移，扩张窗口，直到得到一个可行窗口，亦即包含T的全部字母的窗口。
     *
     * 得到可行的窗口后，将left指针逐个右移，若得到的窗口依然可行，则更新最小窗口大小。
     *
     * 若窗口不再可行，则跳转至 2。
     *
     * 可行窗口判断：使用hashmap计数，也可以用int[26]计数，判断指定位置的大小
     *
     */
    public String minWindowII(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int left = 0, right = 0, len = Integer.MAX_VALUE;
        String res = "";
        int[] source = new int[256];
        int[] target = new int[256];
        for (char c : t.toCharArray()) target[c]++;
        while (right < s.length()) {
            if (!valid(source, target)) {
                source[s.charAt(right)]++;
                right++;
            }
            while (valid(source, target)) {
                if (right - left < len) {
                    len = Math.min(len, right - left);
                    res = s.substring(left, right);
                }
                source[s.charAt(left)]--;
                left++;
            }

        }
        return res;
    }

    //判断source每个字符在窗口中出现的次数与target的大小，包含的话true，不包含的话false
    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] < target[i]) return false;
        }
        return true;
    }

}
