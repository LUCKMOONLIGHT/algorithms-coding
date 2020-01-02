package _15.series.K;

/**
 * 713. 乘积小于K的子数组
 * 给定一个正整数数组 nums。
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 */
public class numSubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right]; //子数组的乘积数
            while (prod >= k) prod /= nums[left++]; //向右移动 \mathrm{left}left，直到满足乘积小于 kk 的条件
            ans += right - left + 1; //乘积小于 kk 的连续子数组
        }
        return ans;
    }
}
