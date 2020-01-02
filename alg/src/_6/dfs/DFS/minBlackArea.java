package _6.dfs.DFS;

/**
 * 包含全部黑色像素的最小矩形
 *
 *
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * 6
 * 思路1：
 * 两层for循环遍历，遇到1，更新min_l,max_r,min_t,max_d
 * area  =  (max_r - min_l + 1) * (max_d - min_t + 1)
 *
 * 思路2：
 * DFS 寻找1 更新min_l,max_r,min_t,max_d
 * area  =  (max_r - min_l + 1) * (max_d - min_t + 1)
 */
public class minBlackArea {
    public int minBlackAreaI(int[][] matrix){
        int min_l = Integer.MAX_VALUE,max_r = 0,min_t = Integer.MAX_VALUE,max_d = 0;
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == 1){
                    min_l = Math.min(min_l, i);
                    max_r = Math.max(max_r, i);
                    min_t = Math.min(min_t, j);
                    max_d = Math.max(max_d, j);
                }
            }
        }
        return (max_r - min_l + 1) * (max_d - min_t + 1);
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int min_l = Integer.MAX_VALUE,max_r = 0,min_t = Integer.MAX_VALUE,max_d = 0, max = 0;
    public int minBlackAreaII(int[][] matrix){

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                dfs(matrix, i, j);
                System.out.println(min_l +" "+max_r+" "+min_t+" "+max_d);
                max = Math.max(max, (max_r - min_l + 1) * (max_d - min_t + 1));
            }
        }
        return max;
    }

    public void dfs(int[][] matrix, int x, int y){
        if(x < 0 || x >=matrix.length || y < 0 || y >=matrix[0].length || matrix[x][y] != 1) return;
        min_l = Math.min(min_l, x);
        max_r = Math.max(max_r, x);
        min_t = Math.min(min_t, y);
        max_d = Math.max(max_d, y);
        matrix[x][y] = 2;
        for (int i=0;i<dx.length;i++){
            for (int j=0;j<dy.length;j++){
                dfs(matrix,x+dx[i], y+dy[j]);
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,0,1,0},{0,1,1,0},{0,1,0,0}};
        minBlackArea minBlackArea = new minBlackArea();
        int res = minBlackArea.minBlackAreaII(matrix);
        System.out.println(res);
    }
}
