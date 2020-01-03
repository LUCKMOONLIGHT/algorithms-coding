package _11.MonotonousStack;

import java.util.Arrays;
import java.util.Stack;

//84. 柱状图中最大的矩形 Hard

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class largestRectangleArea {
    /**
     * 1.用单调栈来记录单调递增的数字，当遇到单调递减的数字时，记录i之前的矩阵面积
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        for (int i = 0; i <=n; i++) {
            //判断i是否达到右边界，影响后面的到达右边界的stack弹出，计算逻辑
            int curHeight = i == n ? -1 : heights[i];
            //维持一个单调栈，从栈底到栈顶是从小到大的顺序，如果要进栈的元素比栈顶的值小，将栈顶的值弹出，计算逻辑
            while (!stack.isEmpty() && curHeight <= heights[stack.peek()]) {
                int stackHeight = heights[stack.pop()];
                // i - stack.peek() - 1 是计算width，这个code需要扣一下(入栈序号-该元素前一个序号-1)
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, stackHeight * width);
            }
            stack.push(i);
        }
        return max;
    }

    /**
     * 85. 最大矩形
     *
     * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     *
     * 思路：将输入转换为一系列的柱状图，然后通过柱状图中的面积求最大矩形面积
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[] dp = new int[matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {

                // update the state of this row's histogram using the last row's histogram
                // by keeping track of the number of consecutive ones

                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            // update maxarea with the maximum area from this row's histogram
            maxarea = Math.max(maxarea, largestRectangleArea(dp));
        }
        return maxarea;

    }

    public int maximalRectangleII(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n]; // initialize left as the leftmost boundary possible
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n); // initialize right as the rightmost boundary possible

        int maxarea = 0;
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            // update height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;//height叠加每一行的高度
                else height[j] = 0;
            }
            // update left
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            // update right
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            // update area
            for (int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
            return maxarea;
    }

    public static void main(String[] args) {
//        char[][] matrix = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix1 = new char[][]{{'0', '1'},{'1', '0'}};
        largestRectangleArea largestRectangleArea = new largestRectangleArea();
//        int res = largestRectangleArea.largestRectangleArea(new int[]{3,1,3,2,2});
        int res = largestRectangleArea.maximalRectangle(matrix1);
//        int res = largestRectangleArea.maximalRectangleII(matrix);
        System.out.println(res);
    }
}
