package _4.dp;

//面试题10- I. 斐波那契数列
//F(0) = 0,   F(1) = 1  F(N) = F(N - 1) + F(N - 2) ， 求fib的n项
//思路；使用动态规划，使用2个元素存储即可，当前元素的值只跟前两个元素有关系
public class fib {
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            int tmp = (dp[1] + dp[0])%1000000007;;
            dp[0] = dp[1];
            dp[1] = tmp;
        }
        return dp[1];
    }
}
