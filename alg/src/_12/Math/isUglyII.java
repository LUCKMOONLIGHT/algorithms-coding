package _12.Math;

/**
 * 丑数II - 三指针
 * 只包含质因子2、3和5  14因为包含7，所以不能为丑数
 * 丑数p = 2 ^ x * 3 ^ y * 5 ^ z
 * 求第N个丑数
 *
 * 思路1：最小堆
 * 因为丑数是2, 3, 5的倍数，我们不断把它们的倍数压入栈中，再按顺序弹出！
 * 时间复杂度：nlogn
 *
 * 思路2：动态规划
 * 质因数只包含2，3，5，那么丑数必然是由某个丑数乘以{2,3,5}得出来的
 * dp[i] 表示第i个丑数
 * dp[i] = min(2 * dp[l_2], 3 * dp[l_3], 5 * dp[l_5]) 取最小的丑数
 * 当去到丑数时，取最小的2，3，5倍数增长
 * 时间复杂度：O(n)O(n)
 */
public class isUglyII {
    public int isUglyII(int n){
        int[] dp = new int[n];
        dp[0] = 1;
        int l2 = 0;
        int l3 = 0;
        int l5 = 0;
        for (int i=1;i<n;i++){
            int t2 = 2*dp[l2];
            int t3 = 3*dp[l3];
            int t5 = 5*dp[l5];
            dp[i] = Math.min(t2, Math.min(t3, t5));
            if (dp[i] == t2) l2++;
            if(dp[i] == t3) l3++;
            if (dp[i] == t5) l5++;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        isUglyII isUglyII = new isUglyII();
        int res = isUglyII.nthUglyNumber(11);
        System.out.println(res);
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int la = 1;
        int lb = 1;
        int lc = 1;
        for (int i=1;i<n;i++){
            int t1 = 2*la;
            int t2 = 3*lb;
            int t3 = 5*lc;
            dp[i] = Math.min(t1, Math.min(t2, t3));
            if (dp[i] == t1) la++;
            if (dp[i] == t2) lb++;
            if (dp[i] == t3) lc++;
        }
        return dp[n-1];
    }
}
