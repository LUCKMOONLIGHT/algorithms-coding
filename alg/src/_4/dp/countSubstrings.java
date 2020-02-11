package _4.dp;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 *
 * dp[i][j] 表示从i到j
 * 1.当l=r 时，单个字符必定为回文
 * 2.当s.charAt(l)==s.charAt(r)时，如果长度r-l为2，必定为回文，如果长度大于2，见dp[l+1][r+1]
 */
public class countSubstrings {
    public int countSubstrings(String s) {
        if(s == null){
            return 0;
        }
        if(s.length() == 0 || s.length() == 1){
            return s.length();
        }
        int n = s.length();
        int result = 0;
        boolean[][] dp = new boolean[n][n]; //作为备忘录
        for(int end = 0; end < n; end++){ //end结束
            for(int start = 0; start <= end; start++){ //start开始长度
                if(start == end){
                    dp[start][end] = true;
                    result++;
                }else{
                    if(s.charAt(start) == s.charAt(end) && (end-start <= 1 || dp[start+1][end-1])){
                        dp[start][end] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }

}
