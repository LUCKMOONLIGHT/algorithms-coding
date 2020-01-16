package _4.dp;

//10. 正则表达式匹配 - difficult
public class isMatch {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return true;
        int m  = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i=2;i<=n;i+=2){
            if(p.charAt(i-1) == '*') dp[0][i] = dp[0][i-2];
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                char sc = s.charAt(i-1);
                char pc = p.charAt(j-1);
                if(sc == pc || pc == '.') dp[i][j] = dp[i-1][j-1];
                else if(pc == '*'){
                    if(dp[i][j-2]) dp[i][j] = true;
                    else if(sc == p.charAt(j-1) || p.charAt(j-1) == '.') dp[i][j] = dp[i-1][j];
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
}
