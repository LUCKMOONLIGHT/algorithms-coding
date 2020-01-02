package _10.slideWindow;

/**
 * 424.替换后的最长重复字符
 * 将任意位置上的字符替换成另外的字符，总共可最多替换 k 次
 * 找到包含重复字母的最长子串的长度
 * s = "ABAB", k = 2   4
 */
public class characterReplacement {
    /**
     * 滑动窗口
     * 1.left right记录滑动窗口位置
     * 2.dict记录字符出现的次数
     * 3.更新窗口中的最大重复次数
     * 4.当k无法覆盖不重复数时，缩小left
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() == 0) return 0;
        int[] dict = new int[256];
        int left = 0, right = 0, res = 0, maxlen = 0;
        while(right < s.length()){
            dict[s.charAt(right)]++;
            maxlen = Math.max(maxlen, dict[s.charAt(right)]);
            while((right - left + 1 - maxlen) > k){ //当k无法完全覆盖时，缩小left
                dict[s.charAt(left++)]--;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
