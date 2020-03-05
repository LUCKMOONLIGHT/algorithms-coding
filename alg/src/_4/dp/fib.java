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

    //面试题46. 把数字翻译成字符串
    //给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
    // 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    //
    //输入: 12258
    //输出: 5
    //解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
    public int translateNum(int num) {
        //每次可以选择一个数字或两个数字，用来合并成一个字符，求可以合成多少种字符串
        //类似于爬楼梯和斐波拉契问题
        String s = String.valueOf(num);
        int n = s.length();
        int[] dp = new int[n+1];

        for(int i=0;i<=n;i++){
            if(i<2) dp[i] = 1;
            else {
                int v = Integer.parseInt(s.substring(i-2,i));
                dp[i] = dp[i-1] + (v>=10 && v <=25 ? dp[i-2]:0);
            }
        }
        return dp[n];
    }

    //面试题47. 礼物的最大价值
    //在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
    // 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
    // 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
    //思路：1.初始化第一行和第一列  2.二层循环当前节点加上左上最大节点的值
    public int maxValue(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int r = grid.length, c = grid[0].length;
        for(int i=1;i<r;i++)
            grid[i][0] += grid[i-1][0];
        for(int j=1;j<c;j++)
            grid[0][j] += grid[0][j-1];
        for(int i=1;i<r;i++)
            for(int j=1;j<c;j++)
                grid[i][j] += Math.max(grid[i-1][j], grid[i][j-1]);
        return grid[r-1][c-1];
    }
}
