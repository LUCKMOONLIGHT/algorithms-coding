package _4.dp;


public class integerBreak {
    //343. 整数拆分  1<n<56 数字不会超出2^32
    //给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
    //注意到我们每次将一段绳子剪成两段时，剩下的部分可以继续剪，也可以不剪， 因此我们得到了递归函数 F(n)=max(i\times(n-i),i\times F(n-i)),i=1,2,...,n-2F(n)=max(i×(n−i),i×F(n−i)),
    public int integerBreak(int n) {
        int[] memory = new int[n+1];
        memory[2] = 1;
        for (int i = 3; i <= n; i++) {
            for ( int j = 1; j <= i - 1; j++) {
                memory[i] = Math.max(memory[i], Math.max(j * memory[i - j], j * (i - j)));
            }
        }
        return memory[n];
    }

    //面试题14- II. 剪绳子 II  2 <= n <= 1000  会溢出  大数越界情况下的求余问题
    //贪心算法，用动态规划每次不取余会越界，取余会影响大小
    //
    public int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for(int a = n / 3 - 1; a > 0; a /= 2) {
            if(a % 2 == 1) rem = (rem * x) % p;  //二进制位置上为1的子树和相乘
            x = (x * x) % p;
        }
        if(b == 0) return (int)(rem * 3 % p);//3^(a-1) * 3
        if(b == 1) return (int)(rem * 4 % p);//3^(a-1) * 2 * 2
        return (int)(rem * 6 % p); //3^(a-1) * 3 * 2
    }

}
