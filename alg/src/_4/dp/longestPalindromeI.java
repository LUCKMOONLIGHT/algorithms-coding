package _4.dp;

/**
 * 409. 最长回文串
 *
 * 1.将string映射到字母表进行计数
 * 2.取每个位置的偶数位
 * 3.结果小于字符串长度，自加1
 */
public class longestPalindromeI {
    public int longestPalindrome(String s) {
        int[] cnts = new int[58];
        for (int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i) - 'A']++;
        }
        int palindrome = 0;
        for (int cnt : cnts) {
            palindrome += (cnt / 2) * 2;
        }
        if (palindrome < s.length()) {
            palindrome++;
        }
        return palindrome;
    }
}
