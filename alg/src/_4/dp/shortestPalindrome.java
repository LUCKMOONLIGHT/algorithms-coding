package _4.dp;

/**
 * 214.最短回文串 HARD
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 */

/**
 * 解法1：（只能再字符串的开头插入字符） 从字符串开头找到最大的回文子串，然后反转剩余的子串并附加到开头
 * 字符串 \text{“abcbabcab”}“abcbabcab”。从开头找到的最大回文子串是 \text{“abcba”}“abcba”，剩下的部分是 \text{“bcab”}“bcab”。
 * 因此要求的字符串是 \text{“bcab”}“bcab” 的反转（= \text{“bacb”}“bacb”）+ 原字符串（ = \text{“abcbabcab”}“abcbabcab”）= \text{“bacbabcbabcab”}“bacbabcbabcab”
 *def shortestPalindrome(self, s: str) -> str:  abcbabcab
 *         r = s[::-1]    r:bacbabcba s:abcbabcab
 *         for i in range(len(s) + 1):
 *             if s.startswith(r[i:]):
 *                 return r[:i] + s
 *
 */



import java.util.Arrays;

/**
 * 解法2：KMP算法。next匹配
 */
public class shortestPalindrome {
    public static void main(String[] args) {
        shortestPalindrome shortestPalindrome = new shortestPalindrome();
        String res = shortestPalindrome.shortestPalindrome("aacecaaa");
        System.out.println(res);
    }


    public String shortestPalindromeI(String s) {
        if(s == null || s.length() == 0) return "";
        if(s.length() == 1) return s;
        String rs = reverse1(s);
        int i = 0;
        int j = s.length();
        while(true) {
            if((i<j)&&(rs.substring(i).equals(s.substring(0,j-i)))) break;
            else i++;
        }
        return rs.substring(0,i)+s;
    }
    public String reverse1(String str)
    {
        return new StringBuffer(str).reverse().toString();
    }

    public String shortestPalindrome(String s){
        String s_r = reverse(s);
        String s_k = s + '#' + s_r;
        int[] next = new int[s_k.length()];
        int k = 0;
        for (int i=1;i<s_k.length();i++){
            k = next[i - 1];
            while (k > 0 && s_k.charAt(i) != s_k.charAt(k)){
                k = next[k - 1];
            }
            if (s_k.charAt(i) == s_k.charAt(k)){
                next[i] = k + 1;
            }
        }
        return reverse(s.substring(next[s_k.length() - 1])) + s;
    }
    private String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }
}

