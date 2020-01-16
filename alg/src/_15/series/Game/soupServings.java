package _15.series.Game;

//808. 分汤

/**
 * 提供 100ml 的汤A 和 0ml 的汤B。
 * 提供 75ml 的汤A 和 25ml 的汤B。
 * 提供 50ml 的汤A 和 50ml 的汤B。
 * 提供 25ml 的汤A 和 75ml 的汤B。
 *
 * 需要返回的值： 汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。
 *
 * N = 50  0.625  0.25 *(1 + 1 + 0.5 + 0)= 0.625  状态转移方程
 */
public class soupServings {
    /**
     * 当N比较小的时候，我们显然可以使用动态规划来计算答案，这个非常简单。
     * 当N比较大的时候，我们可以看到A每次平均减小2.5个单位，B每次平均减小1.5个单位。
     * 所以当N非常大的时候，A可以说几乎绝对先比B先分完。
     * 计算一个临界值，当N>500个单位的时候，直接输出0.999999
     *
     */

    public double soupServings(int N) {
        N = N/25 + (N%25 > 0 ? 1 : 0); //由于四种分配操作都是 25 的倍数，因此我们可以将 N 除以 25
        if (N >= 500) return 1.0; //当 N >= 500 * 25 时，所求概率已经大于 0.999999 了,它和 1 的误差都小于 10^-6

        double[][] memo = new double[N+1][N+1];
        for (int s = 0; s <= 2*N; ++s) {
            for (int i = 0; i <= N; ++i) {
                int j = s-i;
                if (j < 0 || j > N) continue;
                double ans = 0.0;
                if (i == 0) ans = 1.0;//在 N 非常大的时候，A 会有很大的概率比 B 先分配完，所有概率应该非常接近 1
                if (i == 0 && j == 0) ans = 0.5; //A,B同时分完的概率为0.5
                if (i > 0 && j > 0) { //状态转移方程
                    ans = 0.25 * (memo[M(i-4)][j] + memo[M(i-3)][M(j-1)] +
                            memo[M(i-2)][M(j-2)] + memo[M(i-1)][M(j-3)]);
                }
                memo[i][j] = ans;

            }
        }
        return memo[N][N];
    }

    public int M(int x) { return Math.max(0, x); }
}
