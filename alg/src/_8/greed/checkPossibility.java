package _8.greed;

/**
 * 665. 非递减数列 - 贪心
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 */
public class checkPossibility {
    /**
     * 1.nums[i-1] <= nums[i] 非递减时 continue
     * 2.递减时存在两种情况 nums[i-2] > nums[i] nums[i]=nums[i-1] i-1肯定大于i-2
     *                    nums[i-2] <= nums[i] nums[i-1] = nums[i] 防止i变大
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i=1;i<nums.length;i++){
            if(nums[i-1] <= nums[i]) continue;
            count++;
            if(i>=2 && nums[i-2] > nums[i]) nums[i]=nums[i-1];
            else nums[i-1] = nums[i];
        }
        return count <= 1;
    }
}
