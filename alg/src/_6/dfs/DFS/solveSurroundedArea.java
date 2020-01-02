package _6.dfs.DFS;

/**
 * 被围绕的区域
 * 1.给一个二维的矩阵，包含 'X' 和 'O', 找到所有被 'X' 围绕的区域，并用 'X' 填充满。
 * 2.与矩阵边界连接的O不能变
 *
 * 解决思路：
 * 1.首先判断边界条件
 * 2.然后将边界点作为起始点，将与边界点O相邻的O置为M
 * 3.将所有的O置为X，将左右的M置为O
 */
public class solveSurroundedArea {
    public int[] dx = {1, -1, 0, 0};
    public int[] dy = {0, 0, 1, -1};
    public void getsolve(char[][] grid){
        if (grid == null || grid.length == 0) return;
        int row = grid.length;
        int col = grid[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(i == 0 || j == 0 || i == row - 1 || j == col - 1){
                    selectM(grid, i, j);
                }
            }
        }

        for (int i = 0;i<row;i++){
            for (int j=0;j<col;j++){
                if(grid[i][j] == 'O'){
                    grid[i][j] = 'X';
                }else if(grid[i][j] == 'M'){
                    grid[i][j] = 'O';
                }
            }
        }
    }
    public void selectM(char[][] grid, int i, int j){
        if(grid == null || grid.length == 0 || grid[0].length == 0 || grid[i][j] != 'O') return;

        grid[i][j] = 'M';

        for (int k=0;k<grid.length;k++){
            selectM(grid, i+dx[k], j+dy[k]);
        }


    }

}
