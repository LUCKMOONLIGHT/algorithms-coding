package _12.Math;

/**
 * 357. 计算各个位数不同的数字个数 - 动态规划
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * n=1 0-9
 * n=2 100-9-10=81
 * 动态转移方程： dp[i]=dp[i-1]*(11-i)
 */
public class countNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n){
        if(n==0)
            return 1;
        if(n>10){
            n=10;
        }//大于10没有意义了,因为十个数字都用了.
        if(n==1){
            return 10;
        }
        if(n==2){
            return 91;
        }
        int dp[]=new int [11];
        dp[1]=10;
        dp[2]=81;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]*(11-i);
        }


        int  sum=0;
        for(int i=1;i<=n;i++){
            sum+=dp[i];
        }
        return sum;
    }
}
