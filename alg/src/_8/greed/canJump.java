package _8.greed;

import java.util.Arrays;

/**
 * 动态规划-55.跳跃游戏
 *
 * 定义：如果我们可以从数组中的某个位置跳到最后的位置，就称这个位置是“好坐标”，否则称为“坏坐标”。问题可以简化为第 0 个位置是不是“好坐标”。
 * 这是一个动态规划问题，通常解决并理解一个动态规划问题需要以下 4 个步骤：
 *
 * 利用递归回溯解决问题
 * 利用记忆表优化（自顶向下的动态规划）
 * 移除递归的部分（自底向上的动态规划）
 * 使用技巧减少时间和空间复杂度
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 * 1.方法 1：回溯
 * 这是一个低效的解决方法。我们模拟从第一个位置跳到最后位置的所有方案。从第一个位置开始，模拟所有可以跳到的位置，然后从当前位置重复上述操作，当没有办法继续跳的时候，就回溯。
 *int furthestJump = Math.min(position + nums[position], nums.length - 1);
 *         for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
 *             if (canJumpFromPosition(nextPosition, nums)) {
 *                 return true;
 *             }
 *         }
 *
 * 快速的优化方法是我们可以从右到左的检查 nextposition ，理论上最坏的时间复杂度复杂度是一样的。但实际情况下，对于一些简单场景，这个代码可能跑得更快一些。直觉上，就是我们每次选择最大的步数去跳跃，这样就可以更快的到达终点。
 * 自顶向下的动态规划 从0开始存储状态
 * 自底向上的动态规划 从最后开始存储状态，消除回溯，节省递归开销
 *
 */
public class canJump {
    public boolean canJump(int[] nums){
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        memo[nums.length - 1] = 1;
        for(int i=nums.length - 2;i >= 0; i--){
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++){ //所有能够走的步数，能否达到最后的位置
                if (memo[j] == 1){
                    memo[i] = 1;
                    break;
                }
            }
        }
        return memo[0] == 1;
    }

    /**BEST
     * 贪心：修改最后到达的位置 lastPos = i;
     * 判断lastPos == 0;
     * 判断子位置是不是好位置，更新目标位置的值
     */
    public boolean canJumpII(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

}
