package _15.series.palindrome;

//125. 验证回文串
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//输入: "A man, a plan, a canal: Panama"
//输出: true
//思路：1.从字符串中挑选出字母和数字字符  2.使用双指针判断是否为回文
public class isPalindrome {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        for(char c:s.toCharArray()){
            if(c >= 'a' && c <= 'z' || c>= '0' && c <= '9') sb.append(c);
        }
        return checkPalindrome(sb.toString());
    }

    public boolean checkPalindrome(String s){
        int l = 0, r = s.length() - 1;
        while(l < r && s.charAt(l) == s.charAt(r)){
            l++;
            r--;
        }
        return l>=r;
    }
}
