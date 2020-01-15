package _5.pointer;

//1163. 按字典序排在最后的子串
//给你一个字符串 s，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
//输入："abab"
//输出："bab"
//解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"
//
public class lastSubstring {
    //思路：最后的子串 == 最大的子串，即最大字母到结尾的字符串
    //1.指针找最大子串
    public String lastSubstring(String s) {
        int j = 0;
        int n = s.length();
        char[] chars = s.toCharArray();
        for(int i=1;i<n;i++){
            int base = 0;
            while(i+base<n && chars[i+base] == chars[j+base]) base++;//两个子串元素重复，向后比较
            if(i+base<n && chars[i+base] > chars[j+base]) j=i;//j标记最大子串的起始位置
            if(i+base == n) break; //如果子串长度超过最大长度，停止循环
        }
        return s.substring(j);
    }

    public static void main(String[] args) {
        lastSubstring lastSubstring = new lastSubstring();
        String res = lastSubstring.lastSubstring("cacacb");
        System.out.println(res);
    }
}
