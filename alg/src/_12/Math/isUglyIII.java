package _12.Math;

/**
 * 313 - 超级丑数 - 三指针
 * 编写一段程序来查找第 n 个超级丑数。
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 *  1 是任何给定 primes 的超级丑数,所以for循环从i开始
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 *
 *
 * 1.求质数中的最小数
 * 2.将最小数的索引+1
 */
public class isUglyIII {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        int[] idx = new int[primes.length];
        dp[0] = 1;
        for (int i=1;i<n;i++){
            dp[i] = primes[0]*dp[idx[0]];
            for (int j=1;j<primes.length;j++){
                dp[i] = Math.min(dp[i], primes[j]*dp[idx[j]]);
            }
            for (int k=0;k<primes.length;k++){
                if (dp[i] == primes[k]*dp[idx[k]]) idx[k]++;
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        isUglyIII isUglyIII = new isUglyIII();
        int res = isUglyIII.nthUglyNumber(5, 2,11,13);
        System.out.println(res);
    }

    /**
     * 重新定义丑数：a，b，c为非连续质数，且差距较大时
     * 请你帮忙设计一个程序，用来找出第 n 个丑数。
     * 丑数是可以被 a 或 b 或 c 整除的 正整数。
     * 1.二分查找 2.容斥原理
     * 对于任意数字n，判断小于k的丑数有多少个？
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        long l = 1;
        long r = 2_000_000_000;
        long lcmAB = a / gcd(a, b) * b; //最小公倍数
        long lcmAC = a / gcd(a, c) * c;
        long lcmBC = b / gcd(b, c) * c ;
        long lcmABC = lcmAB / gcd(lcmAB, c) * c;
        while(l < r) {
            long mid = (r + l) / 2;
            long sum = mid / a + mid / b + mid / c;
            sum -= mid / lcmAB + mid /lcmAC + mid / lcmBC;
            sum += mid / lcmABC;
            if(sum < n) //sum为丑数个数
                l = mid + 1;
            else
                r = mid;
        }
        return (int)l;
    }

    public long gcd(long p, long q) { //最大公约数
        if(p == 0) return q;
        return gcd(q % p, p);
    }
}
