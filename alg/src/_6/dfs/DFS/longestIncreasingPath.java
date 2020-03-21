package _6.dfs.DFS;

//329. 矩阵中的最长递增路径
//给定一个整数矩阵，找出最长递增路径的长度。
//对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
//思路：1.暴力dfs  2.meno 备忘录dfs （用一个集合来避免一次深度优先搜索中的重复访问）O(mn) O(mn) 3.dp   O(mn)O(mn)
public class longestIncreasingPath {
    private int[] dx = new int[]{1,-1,0,0};
    private int[] dy = new int[]{0,0,1,-1};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int res = 0;
        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++){
                res = Math.max(res, dfs(matrix, dp, i, j));
            }
        }
        return res;
    }

    public int dfs(int[][] matrix, int[][] dp, int i, int j){
        if(dp[i][j] != 0) return dp[i][j]; // 查缓存，如果缓存中有直接return
        int res = 0;//初始化当前长度
        for(int k = 0;k < dx.length;k++){  //上下左右四个相邻单元格的结果
            int x = i + dx[k];
            int y = j + dy[k];

            if(x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] < matrix[x][y]){//满足条件，dfs
                res = Math.max(res, dfs(matrix, dp, x, y));
            }
        }
        dp[i][j] = res + 1;//备忘录结果缓存：当前长度能够达到的最大递增长度
        return res + 1;//返回当前长度
    }
}
