package _4.dp;

/**
 * 62.不同路径（UniquePaths） - 动态规划
 *
 * 题目：机器人位于m*n的左上角，每次只能向下或者向右移动一步。到达网络的右下角，总共有多少条路径
 *
 * 思路：当前位置的路线数就是从左边来的路数与从上面来的路数之和。
 * arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
 */
public class uniquePaths {
    //没有障碍物
    public int uniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(i ==0 || j == 0){
                    dp[i][j] =1;
                }else {
                    //当前路径数只与左上角和右上角和有关
                    dp[i][j] = dp[i-1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m -1][n - 1];
    }

    //63. 不同路径 II   有障碍物
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //1.判断第一行第一列，如果有1的话，该行列全为0，否则为1
        //2.判断其余行列，如果有1的话，该行为0，否则为dp[i][j-1]+dp[i-1][j]
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;
        //如果第一个点为障碍物,直接返回0
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        //初始化第一行和第一列，如果当前为0，上一个为1，初始化为1，否则为0
        //初始化，如果当前结点为0，能走，当前的状态由上一个位置的状态决定，如果为1不能走，当前的状态为0
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    //如果当前位置可以走，当前位置的路径数等于左上角加上右上角
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[R - 1][C - 1];
    }

    //980. 不同路径 III
    //在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。
    //输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
    //输出：2
    //解释：我们有以下两条路径：
    //1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
    //2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
    public int uniquePathsIII(int[][] grid) {
        int sum = 1;//空白格+起始点的个数
        int r = 0;
        int c = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) { //统计要走的路径
                    sum++;
                } else if(grid[i][j] == 1) { //找到起始路径
                    r = i;
                    c = j;
                }
            }
        }
        return dfs(grid, r, c, sum);  //sum要走的数  rc起始位置  当遇到2时，sum是否走完
    }


    public int dfs(int[][] g, int i, int j, int sum) {
        if(i < 0 || i >= g.length || j < 0 || j >= g[0].length || g[i][j] == -1) {
            return 0;
        }

        if(g[i][j] == 2) return 0 == sum ? 1 : 0;
        int fix = 0;
        g[i][j] = -1;//当前路径已访问
        //上下左右访问
        fix += dfs(g, i + 1, j, sum - 1);
        fix += dfs(g, i - 1, j, sum - 1);
        fix += dfs(g, i, j + 1, sum - 1);
        fix += dfs(g, i, j - 1, sum - 1);
        g[i][j] = 0;//还原不影响其他dfs
        return fix;
    }


}
