package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 51. N皇后 - 回溯
 *
 *n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 思路：参照数独，全排列
 * 使用哈希表分别记录 “列状态” 、 “主对角线状态” 、 “副对角线状态”
 *
 * 思路：每一行每个棋子都有四种放法，使用条件剪枝，使用备忘录标记重复
 */
public class solveNQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) { //候选位置
            nums[i] = i;
        }

        Set<Integer> col = new HashSet<>();  //列状态
        Set<Integer> master = new HashSet<>(); //主对角线状态
        Set<Integer> slave = new HashSet<>();  //副对角线状态
        Stack<Integer> stack = new Stack<>();

        backtrack(nums, 0, n, col, master, slave, stack, res);
        return res;
    }

    private void backtrack(int[] nums, int row, int n,
                           Set<Integer> col,
                           Set<Integer> master,
                           Set<Integer> slave,
                           Stack<Integer> stack,
                           List<List<String>> res) {

        if (row == n) {  //满足条件
            List<String> board = convert2board(stack, n);
            res.add(board);
            return;
        }

        // 针对每一列，尝试是否可以放置
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !master.contains(row + i) && !slave.contains(row - i)) { //剪枝
                stack.add(nums[i]);
                col.add(i);
                master.add(row + i);
                slave.add(row - i);

                backtrack(nums, row + 1, n, col, master, slave, stack, res);

                slave.remove(row - i);
                master.remove(row + i);
                col.remove(i);
                stack.pop();
            }
        }
    }

    private List<String> convert2board(Stack<Integer> stack, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : stack) { //每行每一个Q的候选填位置
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) { //每行填充.
                stringBuilder.append(".");
            }
            stringBuilder.replace(num, num + 1, "Q");
            board.add(stringBuilder.toString());
        }
        return board;
    }


    public static void main(String[] args) {
        int n = 4;
        solveNQueens solution = new solveNQueens();
        List<List<String>> res = solution.solveNQueens(n);
        System.out.println(res);
    }

}