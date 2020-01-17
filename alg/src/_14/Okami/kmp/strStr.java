package _14.Okami.kmp;

//28. 实现 strStr()
//实现字符串匹配，找出B在A中出现的第一个字符的位置
//给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
//思路：双指针
public class strStr {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return 0;
        int m=haystack.length(), n=needle.length();
        for(int i=0;i<m-n+1;i++){
            int j=0;
            for(;j<n;j++){
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
            }
            if(j == n) return i;
        }
        return -1;
    }
}
