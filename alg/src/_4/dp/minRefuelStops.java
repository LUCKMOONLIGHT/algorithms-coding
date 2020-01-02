package _4.dp;

/**
 * 871. 最低加油次数 - dp
 * dp[i] 表示加i次油能够到达的最远距离
 * 当dp[i] >= target 时，求最小的i
 * target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]] 2
 * O（n^2）
 */
public class minRefuelStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        long[] dp = new long[N + 1];
        dp[0] = startFuel;
        for (int i = 0; i < N; ++i)  //经过的加油站的数量
            for (int t = i; t >= 0; --t) //加油的次数
                if (dp[t] >= stations[i][0]) //当能够到达加油站时，加油
                    dp[t+1] = Math.max(dp[t+1], dp[t] + (long) stations[i][1]); //保存最大的距离target

        for (int i = 0; i <= N; ++i)
            if (dp[i] >= target) return i; //for循环判断最小的i能够到达target
        return -1;
    }
}
