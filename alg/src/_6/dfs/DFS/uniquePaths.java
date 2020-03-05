package _6.dfs.DFS;

public class uniquePaths {
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
        //找到终点，如果到达终点时，已经访问了所有，返回一条路径
        if(g[i][j] == 2) return 0 == sum ? 1 : 0;
        int fix = 0;
        g[i][j] = -1;//当前路径已访问
        //上下左右访问
        fix += dfs(g, i + 1, j, sum - 1);
        fix += dfs(g, i - 1, j, sum - 1);
        fix += dfs(g, i, j + 1, sum - 1);
        fix += dfs(g, i, j - 1, sum - 1);
        g[i][j] = 0;//还原不影响其他dfs
        //需要从不同的方向进行dfs，每个方向都需要重新打标记
        return fix;
    }

    //面试题13. 机器人的运动范围
    //从坐标 [0,0] 到坐标 [m-1,n-1]，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19，请问该机器人能够到达多少个格子？
    int res;
    public int movingCount(int m, int n, int k) {
        if(k == 0) return 1;
        res = 0;
        int[][] bool = new int[m][n];
        dfs(m, n, 0, 0, k, bool);
        return res;
    }

    public void dfs(int m, int n, int i, int j, int k, int[][] bool){
        if(i<0||i>=m||j<0||j>=n||bool[i][j] == 1) return;
        if(k < (i%10 + i/10 + j%10 + j/10)) return;
        res++; //可以访问的位置数
        bool[i][j] = 1; //标记访问
        dfs(m,n,i+1,j,k,bool);
        dfs(m,n,i-1,j,k,bool);
        dfs(m,n,i,j+1,k,bool);
        dfs(m,n,i,j-1,k,bool);
        //一次性dfs记录所有能够访问的节点，不需要还原访问标记
    }
}
