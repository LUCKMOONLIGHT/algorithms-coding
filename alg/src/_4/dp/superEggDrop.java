package _4.dp;

        import java.util.HashMap;
        import java.util.Map;

/**
 * 887. 鸡蛋掉落  - 基于最优策略的动态规划
 *  你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 *  每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 *  你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破
 *  每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 *  你的目标是确切地知道 F 的值是多少。
 *  无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *  最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？包括扔碎了的和没扔碎的
 *  找摔不碎鸡蛋的最高楼层 F
 *
   「状态」很明显，就是当前拥有的鸡蛋数 K 和需要测试的楼层数 N。随着测试的进行，鸡蛋个数可能减少，楼层的搜索范围会减小，这就是状态的变化。
   「选择」其实就是去选择哪层楼扔鸡蛋。回顾刚才的线性扫描和二分思路，二分查找每次选择到楼层区间的中间去扔鸡蛋，而线性扫描选择一层层向上测试。不同的选择会造成状态的转移。
    现在明确了「状态」和「选择」，动态规划的基本思路就形成了：肯定是个二维的 dp 数组或者带有两个状态参数的 dp 函数来表示状态转移；外加一个 for 循环来遍历所有选择，择最优的选择更新状态：

    在第 i 层楼扔了鸡蛋之后，可能出现两种情况：鸡蛋碎了，鸡蛋没碎。注意，这时候状态转移就来了：
 * 如果鸡蛋碎了，那么鸡蛋的个数 K 应该减一，搜索的楼层区间应该从 [1..N] 变为 [1..i-1] 共 i-1 层楼；
 * 如果鸡蛋没碎，那么鸡蛋的个数 K 不变，搜索的楼层区间应该从 [1..N] 变为 [i+1..N] 共 N-i 层楼。
 * 最坏情况下int imax = max(dp(K - 1, i - 1), # 碎 dp(K, N - i)      # 没碎) + 1 # 在第 i 楼扔了一次
 * 至少  res = min（imax）
 *
 *
 *
 */
public class superEggDrop {
    /**
     * 1.递归
     * 假设在N层的高楼中有K个鸡蛋，这个时候我们在n层扔了一个鸡蛋，那么这一次动作，把整个高楼其实就分成了两部分，一部分是1楼到n楼，
     * 这是一个层高为n的新楼，我们暂时叫它一号楼；另一部分是n+1到N楼，这是一栋新产生的层高为N-(n+1)+1=N-n的新楼，我们叫他二号楼。
     * 然后，我们来看刚扔下去的鸡蛋，如果它碎了，说明楼层太高（起码高于F），那么F应该是在一号楼，那么我们就带着剩下的K-1个鸡蛋去
     * 一号楼继续，当我们站在一号楼的某一层的时候，其实和最开始是一样的（递归的信号）。如果鸡蛋没碎，说明楼层不够高（低于F），
     * 此时我们要去二号楼，但是这里有一点点需要注意的，就是我们在二号楼的某一层的时候，其实该层在原始的楼里是要比当前楼层高n层的，
     * 其他同理。最后，因为我们是要找无论 F 的初始值如何的条件下的查找次数，所以我们要在一号楼和二号楼各自的查找次数中选择那个最大的值
     *
     * 动态规划算法的时间复杂度就是子问题个数 × 函数本身的复杂度。
     * 函数本身的复杂度就是忽略递归部分的复杂度，这里 dp 函数中有一个 for 循环，所以函数本身的复杂度是 O(N)
     * 子问题个数也就是不同状态组合的总数，显然是两个状态的乘积，也就是 O(KN)
     * 所以算法的总时间复杂度是 O(K*N^2), 空间复杂度 O(KN)。
     *
     */
    public int superEggDrop1(int K, int N){
        if(N == 0 || N == 1 || K == 1) return N;

        int imin = N;
        for (int i=1;i<=N;i++){
            int imax = Math.max(superEggDrop1(K, N - i), superEggDrop1(K - 1, i - 1));//# 最坏情况下的递归和状态转移
            imin = Math.min(imin, 1 + imax);  //至少：返回这个状态下的最优结果
        }
        return imin;
    }


    /**
     * 空间换时间解法 - 把中间节点存储起来
     * 利用了一个二维数组存储了部分计算结果（空间复杂度O(KN)），使得时间复杂度降低到了O(KN^2)
     */
    public int superEggDrop2(int K, int N) {
        int[][] middleResults = new int[K + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            middleResults[1][i] = i; // only one egg
            middleResults[0][i] = 0; // no egg
        }
        for (int i = 1; i <= K; i++) {
            middleResults[i][0] = 0; // zero floor
        }

        for (int k = 2; k <= K; k++) { // start from two egg
            for (int n = 1; n <= N; n++) {
                int tMinDrop = N * N;
                for (int x = 1; x <= n; x++) {
                    tMinDrop = Math.min(tMinDrop, 1 + Math.max(middleResults[k - 1][x - 1], middleResults[k][n - x]));
                }
                middleResults[k][n] = tMinDrop;
            }
        }

        return middleResults[K][N];
    }

    /**
     * 基于二分查找的动态规划法
     */
    Map<Integer, Integer> cache = new HashMap<>();

    public int superEggDrop3(int K, int N) {
        if (N == 0)
            return 0;
        else if (K == 1)
            return N;

        Integer key = N * 1000 + K; // K <= 100
        if (cache.containsKey(key))
            return cache.get(key);

        int low = 1, high = N;
        while (low + 1 < high) {
            int middle = (low + high) / 2;
            int lowVal = superEggDrop3(K - 1, middle - 1);
            int highVal = superEggDrop3(K, N - middle);

            if (lowVal < highVal)
                low = middle;
            else if (lowVal > highVal)
                high = middle;
            else
                low = high = middle;
        }
        int minimum = 1 + Math.min(
                Math.max(superEggDrop3(K - 1, low - 1), superEggDrop3(K, N - low)),
                Math.max(superEggDrop3(K - 1, high - 1), superEggDrop3(K, N - high))
        );

        cache.put(key, minimum);

        return cache.get(key);
    }

    /**
     * 自底向上，每次找到Xa
     */

    public int superEggDrop4(int K, int N) {
        // Right now, dp[i] represents dp(1, i) 只有一个鸡蛋的场景
        int[] dp = new int[N+1]; //楼层
        for (int i = 0; i <= N; ++i)
            dp[i] = i;
        // 两个或者更多鸡蛋的场景
        for (int k = 2; k <= K; ++k) {
            // Now, we will develop dp2[i] = dp(k, i)
            int[] dp2 = new int[N+1];
            int x = 1;
            for (int n = 1; n <= N; ++n) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1]))
                    x++;

                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
            }

            dp = dp2;
        }

        return dp[N];
    }

    /**
     * 逆序思维- dp[i][j]表示 i 个鸡蛋在 j 次尝试下可以测出的最多的层数
     * 当dp[i][j]  >= N 时，返回结果值m
     * dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1] + 1
     * 本次扔之后可能测出来的层数 + 本次扔之前已经测出来的层数
     * 时间复杂度 O(KlogN)
     */

    class Solution {
        public int superEggDrop(int K, int N) {
            int[][] dp = new int[K + 1][N + 1];
            for (int m = 1; m <= N; m++) {
                dp[0][m] = 0; // zero egg
                for (int k = 1; k <= K; k++) {
                    //如果鸡蛋碎了，为了使 dp[i][j] 测出的层数最多，我们当然希望 i-1 个鸡蛋在后面的 j-1 次尝试中测出的层数最多，这是一个子问题，即 dp[i-1][j-1]
                    //如果鸡蛋没碎，我们同样要用 i 个鸡蛋在 j-1 次尝试中测出最多的层数，即 dp[i][j-1]
                    dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
                    if (dp[k][m] >= N) {
                        return m;
                    }
                }
            }
            return N;
        }
    }

}
