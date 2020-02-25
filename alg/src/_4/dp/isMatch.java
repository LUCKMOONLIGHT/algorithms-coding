package _4.dp;

//10. 正则表达式匹配 - difficult
//'.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
//**注意：'*'匹配零个前面那一个元素即为删除前一个元素** 删除前一个||匹配一次前面那一个元素||匹配二次前面那一个元素
//ab, abc*  TRUE  ab, abc** FALSE
//s = "aa"
//p = "a*"
//输出: true


//思路：
//如果 p.charAt(j) == s.charAt(i) || p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1]；
//如果 p.charAt(j) == '*'：
//如果 p.charAt(j-1) != s.charAt(i) && p.charAt(j - 1) != '.' : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
//如果 p.charAt(i-1) == s.charAt(i) || p.charAt(i-1) == '.'：
//dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a
//or dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
//or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
//
public class isMatch {
    public boolean isMatch(String s,String p){
        if (s == null || p == null) {  //只要有一个为空，匹配不上
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        for (int i = 0; i < p.length(); i++) { // here's the p's length, not s's
            if (p.charAt(i) == '*') { //'*'最小从第2个字符开始
                dp[0][i + 1] = dp[0][i - 1]; // here's y axis should be i+1,a* counts as empty
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配，且不为任意元素，（关键）s[i] == p[j-1]
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];//当前结果等于前前字符匹配的结果
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]); //单个多个没有字符匹配的情况
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */

                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //44.通配符匹配  给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
    //'?' 可以匹配任何单个字符。
    //'*' 可以匹配任意字符串（包括空字符串）。       与10题的区别：'*' 匹配零个或多个前面的那一个元素
    //双指针 利用两个指针进行遍历  3ms O(n) O(1)

    /**
     * 1.如果s和p中字符匹配，则分别自增i和j
     * 2.否则如果p中当前字符为星号，则标记iStar和jStar，同时自增j
     * 3.否则当前字符不匹配，并且也不是*号,如果iStar >= 0，表示之前匹配过星号，因为星号可以匹配任意字符串，所以继续递增i，同时移动j为jStar下一个字符
     * 4.否则返回false
     * 5.当s中字符匹配完，p中字符不能有除星号以外字符
     */
    public boolean isMatchII1(String s, String p) {
        int sn = s.length();
        int pn = p.length();
        int i = 0;
        int j = 0;
        int jStar = -1;
        int iStar = 0;
        while (i < sn) {
            if (j < pn && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < pn && p.charAt(j) == '*') {
                iStar = i;
                jStar = j;
                j++;
            } else if (jStar != -1) {
                iStar++;
                i = iStar;
                j = jStar + 1;
            } else {
                return false;
            }
        }
        while (j < pn) {
            if (p.charAt(j) != '*') return false;
            j++;
        }
        return true;
    }

    //动态规划  12 ms  40ms
    //dp[i][j]表示s到i位置,p到j位置是否匹配!

    /**
     * 状态定义:dp[i][j]：表示 s 的前i个字符是否与 p的前j个字符是否匹配
     * 初始化：
     * 1.dp[0][0]:什么都没有,所以为true
     * 2.第一行dp[0][j],换句话说,s为空,与p匹配,所以只要p开始为*才为true
     * 3.第一列dp[i][0],当然全部为False
     * 动态方程：
     * 1.如果(s[i] == p[j] || p[j] == "?") && dp[i-1][j-1] ,有dp[i][j] = true，或者说dp[i][j] = dp[i-1][j-1]
     * 2.如果p[j] == "*" && (dp[i-1][j] = true || dp[i][j-1] = true) 有dp[i][j] = true 或者说 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]
     * 其中： dp[i-1][j],表示*代表非空任何字符,例如abcd,ab*         dp[i][j-1],表示*代表是空字符,例如ab,ab*
     */
    public boolean isMatchII2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1]; //字符串s[:i]p[:j]是否匹配
        dp[0][0] = true;
        for (int j = 1; j < p.length()+1; j++) {//* 有可能为第一个字符，因此j需从1开始
            if (p.charAt(j-1) == '*') {//初始时,*可以匹配任意字符串，包括空字符串
                dp[0][j] = dp[0][j - 1]; // * is Empty
            }
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') { //前两个字符相等时  （关键）s[i-1] == p[j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {//当前字符为*时（1.空字符  2.匹配非空字符）
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];  // * 代表的是 空字符 || 非空字符
                }
            }
        }
        return dp[s.length()][p.length()];

    }
}
