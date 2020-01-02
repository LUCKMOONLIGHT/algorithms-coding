package _6.dfs.DFS;

/**
 * DFS：深度优先遍历
 * 1.栈：保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点，使用递归栈
 * 2.标记：和BFS一样同样需要对已经遍历过的节点进行标记
 *
 * 岛屿的个数
 * 用一个二维数组代表一张地图，全由“0”和“1”组成，其中“0”代表水域，“1”代表小岛，
 *  小岛“1”被水域“0”所包围，当小岛土地“1”在水平和垂直方向相连接时，认为是同一块土地。求这张地图中小岛的数量。
 *
 *  1.二维递归，dfs将相连的岛屿标记为0
 *  2.计算不想连的岛屿的数量
 */
public class numIslands {
    public int[] dx = {1, -1, 0, 0};
    public int[] dy = {0, 0, 1, -1};
    public int getnumIslands(char[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        boolean[][] mark = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == '1'){
                    dfs(grid, i ,j); //递归将相连的岛屿置为零
                    count ++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j){
        if(i< 0 || i > grid.length || j < 0 || j > grid[0].length || grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0';
        for (int k=0;k<dx.length;k++){
            dfs(grid, i+dx[k], j+dy[k]);
        }
    }
}
