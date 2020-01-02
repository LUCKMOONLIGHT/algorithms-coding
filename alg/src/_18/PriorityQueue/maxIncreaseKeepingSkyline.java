package _18.PriorityQueue;

/**
 * 807. 保持城市天际线
 *  求每个元素可增加的高度，保持原天际线的前提下
 * 输入： grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
 * 输出： 35
 *
 * 1.用两个数组分别记录每一行或者每一列的最大值，也就是所谓的天际线。
 * 2.进行两次扫描，第一次求得数组，第二次更新可增加的高度。
 * 3.由于要保持原有天际线不变，对grid[i][j]进行更新时，应用当前行的最大值和当前列的最大值中较小的一个，再用这个数减去当前高度，就得到当前建筑可增加的高度。
 *
 */
public class maxIncreaseKeepingSkyline {
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int ans = 0;
        int len = grid.length;
        //求每行和每列的最大值
        int [] rowMax = new int[len];
        int [] columnMax = new int [len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j < len; j++) {
                if(grid[i][j] > rowMax[i])
                    rowMax[i] = grid[i][j];
                if(grid[i][j] > columnMax[j])
                    columnMax[j] = grid[i][j];
            }
        }
        //更新可增加的大小
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                ans += Math.min(rowMax[i],columnMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
