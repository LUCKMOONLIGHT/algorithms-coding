package _11.MonotonousStack;

import java.util.Stack;

/**
 * 975. 奇偶跳 - 动态规划 + 单调栈
 * 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。
 * 奇数次跳跃条件，右边比它大的最小的数（相等取下标小的）
 * 偶数次跳跃条件，右边比它小的最大的数（相等取下标小的）
 *
 *  问题：
 *  1.确定下一跳的位置
 */
public class oddEvenJumps {
    /**
     * 奇数跳：维护一个单调递减的数组
     * 偶数跳：维护一个单调递增的数组
     * @param A
     * @return
     */
    public int oddEvenJumps(int[] A) {
        return 1;
    }

    public int[] modec(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                res[stack.pop()] = i;
            }
            stack.push(i);
        }
        return res;
    }
}
