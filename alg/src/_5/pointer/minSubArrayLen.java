package _5.pointer;

/**
 * Leetcode 209:长度最小的子数组
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的子数组。如果不存在符合条件的子数组，返回 0。
 *
 * 方法：使用滑动窗口，双指针的办法
 * 1.当sum小于target的时候，右指针右移
 * 2.当sum大于target的时候，左指针左移
 * 3.当满足条件时，选择最小的长度
 * O（n）
 */
public class minSubArrayLen {
    public int getminSubArrayLen(int[] nums, int targets){
        if (nums.length == 0 || nums == null) return 0;
        int left = 0, right = 0, sum = 0;
        int res = Integer.MAX_VALUE;

        while(right < nums.length){
            if (sum < targets && right < nums.length){
                sum += nums[right++];
            }else if (sum >= targets && left >=0 ){
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
