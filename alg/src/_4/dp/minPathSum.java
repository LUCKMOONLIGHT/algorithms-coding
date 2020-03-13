package _4.dp;

/**
 * 64.最小路径和 - 动态规划（不需要额外存储空间）
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
 *  状态转移：grid(i,j)=grid(i,j)+min(grid(i+1,j),grid(i,j+1))
 *  grid[i][j] 为 右下角到(i,j)结点的最小路径和
 *
 *  从右下至左上，每次取当前数和下、右的数之和的最小值
 *
 *  i+1，i+1 i,i  之间只有两条路可以走
 *  一条是向上；一条是向左  grid(i,j)=grid(i,j)+min(grid(i+1,j),grid(i,j+1))
 *
 *  思路：从下至上，在原数组上进行修改
 */
public class minPathSum {
    public int minPathSum(int[][] grid) {
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)  //i不能加1了
                    grid[i][j] = grid[i][j] +  grid[i][j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)//j不能加1了
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)//i，j都可以加1
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]);
            }
        }
        return grid[0][0];
    }
}
