package _4.dp;

//91. 解码方法
//一条包含字母 A-Z 的消息通过以下方式进行了编码：
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//给定一个只包含数字的非空字符串，请计算解码方法的总数。
//
public class numDecodings {
    //输入: "12"
    //输出: 2
    //解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
    //注意0没有编码  170  70都不能编码
    //可拆分时：f(n) = f(n-1)   可组合时：f(n) = f(n-2)    既可拆分又可组合时：f(n) = f(n-1) + f(n-2)
    public int numDecodings(String str){
        int len = str.length();
        if(len == 0 || (len == 1 && str.charAt(0) == '0')) return 0;
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 0; i < len;i++){
            int temp = 0;
            if(str.charAt(i) != '0') temp += dp[1];  //单个可以解码  f(n) = f(n-1)
            if(i > 0 && (str.charAt(i-1) == '1' || (str.charAt(i-1) == '2' && str.charAt(i) <= '6'))) //即可组合又可解码 f(n) = f(n-2)
                temp += dp[0];
            dp[0] = dp[1]; // f(n-2) = f(n-1)
            dp[1] = temp; //f(n) = f(n-1) + f(n-2)
        }

        return dp[1];
    }
}
