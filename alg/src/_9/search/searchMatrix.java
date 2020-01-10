package _9.search;

//240. 搜索二维矩阵 II
//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性
//每行的元素从左到右升序排列。
//每列的元素从上到下升序排列。
public class searchMatrix {
    //思路：从左下角开始搜索，大于target时，向上移动，小于target时，向右移动
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int i = matrix.length - 1, j=0;
        while(i>=0 && j<matrix[0].length){
            if(matrix[i][j] > target) i--;
            else if(matrix[i][j] < target) j++;
            else return true;}
        return false;
    }
}
