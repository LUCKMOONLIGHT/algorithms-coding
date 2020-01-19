package _4.dp;

//10. 正则表达式匹配 - difficult
//'.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
public class isMatch {
    public boolean isMatchI(String s, String p) {
        if (s == null || p == null) return false;

        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        //"" 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串，其他的是都是false。
        //  奇数位不管什么字符都是false，偶数位为* 时则: dp[0][i] = dp[0][i - 2]
        for (int i = 2; i <= n; i+= 2) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (sc == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

//    if (s == null || p == null) return false;
//
//    int m = s.length(), n = p.length();
//    boolean[][] dp = new boolean[m + 1][n + 1];//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
//    dp[0][0] = true;
//
//    //basecase:空串的初始化 "" 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串，其他的是都是false。
//    //  奇数位不管什么字符都是false，偶数位为* 时则: dp[0][i] = dp[0][i - 2]
//        for (int i = 2; i <= n; i+= 2) {
//        if (p.charAt(i - 1) == '*') {
//            dp[0][i] = dp[0][i - 2]; //偶数位为*，就可以把当前的*和前一个字符删除掉,看前面两个字符的结果即可
//        }
//    }
//
//        for (int i = 1; i <= m; i++) {
//        for (int j = 1; j <= n; j++) {
//            char sc = s.charAt(i - 1);//当前字符串
//            char pc = p.charAt(j - 1);//模式字符串
//            if (sc == pc || pc == '.') { //1.当字符匹配或者p为.时，能够匹配
//                dp[i][j] = dp[i - 1][j - 1];
//            } else if (pc == '*') {//
//                if (dp[i][j - 2]) {//0个匹配;2.1如果p当前为*时,前两个字符能够匹配,则当前必定也能匹配 (### ###b*)
//                    dp[i][j] = true;
//                } else if (sc == p.charAt(j - 2) || p.charAt(j - 2) == '.') {//2.2.前一个字符能够匹配或者前字符为万能匹配时 (##b , ###b *)，或者 ( ##b , ### . * ) (aa a*)
//                    dp[i][j] = dp[i - 1][j];//2.3 多个字符匹配的情况,（###bbbbbbbb，###b*） 此时i-1向前移动
//                }
//            }
//        }
//    }
//
//        return dp[m][n];

    //通配符匹配II  给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
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

    //动态规划  12 ms
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
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < p.length() + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];

    }
}
