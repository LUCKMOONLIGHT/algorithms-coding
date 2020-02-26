package _7.strhash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * matrix[r1][c] [c1 - c2]
 * matrix[r][c2] [r1+1 - r2]
 * matrix[r2][c] [c2 - 1 - c1)
 * matrix[r][c1] [r2 - r1)
 */
public class spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    /**
     * 面试题29. 顺时针打印矩阵
     */

    public int[] spiralOrderII(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new int[0];
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while(r1 <= r2 && c1 <= c2){
            for(int i = c1;i<=c2;i++){
                res[index++] = matrix[r1][i];
            }
            for(int i = r1+1;i<=r2;i++){
                res[index++] = matrix[i][c2];
            }
            if(r1 < r2 && c1 < c2){
                for(int j = c2-1;j>=c1;j--){
                    res[index++] = matrix[r2][j];
                }
                for(int j = r2-1;j>r1;j--){
                    res[index++] = matrix[j][c1];
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }

    //59. 螺旋矩阵 II
    //给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
    //生成一个 n×n 空矩阵 mat，随后模拟整个向内环绕的填入过程
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){ //迭代条件
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++; //更新边界
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        spiralOrder spiralOrder = new spiralOrder();
        List<Integer> res = spiralOrder.spiralOrder(arr);
        System.out.println(Arrays.toString(res.toArray()));
    }

}
