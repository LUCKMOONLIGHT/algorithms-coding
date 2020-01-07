package _11.MonotonousStack;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class trap {
    /**
     * 接雨水I    单调栈 - O(n) O(n)
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 建立一个单调递减的栈，如果出现一个元素大的话，必然可以形成凹槽，此时只需要计算凹槽长度和边界的高度差即可。
     * 使用栈来存储条形块的索引下标。
     * 遍历数组：
     * 当栈非空且 height[current]>height[st.top()]
     * 意味着栈中元素可以被弹出。弹出栈顶元素 top。
     * 计算当前元素和栈顶元素的距离，准备进行填充操作
     * distance=current−st.top()−1
     * 找出界定高度
     * bounded_height=min(height[current],height[st.top()])−height[top]
     * 往答案中累加积水量ans+=distance×bounded_height
     * 将当前索引下标入栈
     * 将current 移动到下个位置
     * <p>
     * <p>
     * <p>
     * 1.遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈
     * 2.如果我们发现一个条形块长于栈顶，弹出栈顶元素并且累加答案到 ans
     */

    public int trapI(int[] height) {
        int n = height.length;
        if (n < 3) return 0;

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) { //找到比当前栈顶大的下一个元素，求面积
                int tmp = stack.pop();
                if (!stack.isEmpty()) {//栈中为比tmp高的元素
                    int distance = i - stack.peek() - 1;//求宽度
                    int bounded_height = Math.min(height[i], height[stack.peek()]) - height[tmp];//求高度
                    res += distance * bounded_height;
                }
            }
            stack.push(i); //如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈
        }
        return res;
    }


    /**
     * 双指针法
     * 从左右两边的边界往中间不断进行收缩，收缩的过程中，对每个坐标（一维坐标）能接的雨水进行求解
     * 1.while从左到右双指针
     * 2.从左右指针高度小的开始，求leftmax，计算当前坐标与leftmax的面积，求rightmax，计算当前坐标与rightmax的面积
     *
     * @param height
     * @return
     */
    public int trapII(int[] height) {
        if(height.length < 3) return 0;
        int l = 0, r = height.length - 1;
        int lmax = 0, rmax = 0, res = 0;
        while(l < r){
            if(height[l] <= height[r]){
                lmax = Math.max(lmax, height[l]);
                res += lmax - height[l];
                l++;
            }else{
                rmax = Math.max(rmax, height[r]);
                res += rmax - height[r];
                r--;
            }
        }
        return res;
    }

    //接雨水 II
    //给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。

    /**
     * 2-D的接雨水问题的边界不再是线段的两个端点，而是矩形的一周，
     * 所以我们用优先队列维护所有边界点，收缩时，也不仅仅只有左右两个方向，
     * 而是上下左右四个方向，并且维护一个visit的数组，记录哪些坐标已经被访问过，不然会造成重复求解。
     *
     * @param height
     * @return
     */
    public int trapII(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2)
            return 0;
        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt((Cell cell) -> cell.height));
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            visited[i][0] = visited[i][n - 1] = true;
            queue.add(new Cell(i, 0, heightMap[i][0]));
            queue.add(new Cell(i, n - 1, heightMap[i][n - 1]));
        }
        for (int i = 1; i < n - 1; i++) {
            visited[0][i] = visited[m - 1][i] = true;
            queue.add(new Cell(0, i, heightMap[0][i]));
            queue.add(new Cell(m - 1, i, heightMap[m - 1][i]));
        }
        int result = 0;
        int[][] bounds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] bound : bounds) {
                int row = cell.row + bound[0];
                int col = cell.col + bound[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    result += Math.max(0, cell.height - heightMap[row][col]);
                    visited[row][col] = true;
                    queue.add(new Cell(row, col, Math.max(cell.height, heightMap[row][col])));
                }
            }
        }
        return result;
    }
    private static class Cell {
        private int row;
        private int col;
        private int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        trap trap = new trap();
        int res = trap.trapII(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(res);
    }
}
