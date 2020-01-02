package _6.dfs.DFS;

/**
 * 计算岛屿的最大面积 - DFS
 *
 * 1.二维for循环
 * 2.dfs 找数组中等于1的数再加一
 */
public class maxAreaOfIsland {

    public int[] dx = {1, -1, 0, 0};
    public int[] dy = {0, 0, 1, -1};

    public void getmaxAreaOfIsland(int[][] grid){
        if (grid == null || grid.length == 0 || grid[0].length == 0) return;
        int max = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                max = Math.max(max, dfs(grid, i, j));
            }
        }

    }
    public int dfs(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return 0;

        int cnt = 1;
        grid[i][j] = 0;
        for (int k=0;k<dx.length;k++){
            cnt += dfs(grid, i+dx[i], j+dx[j]);
        }
        return cnt;
    }
}
