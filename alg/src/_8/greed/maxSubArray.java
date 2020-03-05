package _8.greed;

import java.util.Map;

/**
 * 最大子序和
 * 思路：preMax确定每一个子序列和 res确定最大子序列和
 * 1.preMax确定每一个子序列和
 * 2.res确定最大子序列和
 */
public class maxSubArray {
    public int maxSubArrayI(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int res  = Integer.MIN_VALUE;
        int preMax = 0;
        for (int num:nums){
            preMax = Math.max(preMax, preMax+num);
            res = Math.max(res, preMax);
        }
        return res;
    }

    //面试题42. 连续子数组的最大和
    //1.如果每一个子数组和sum大于0，加上当前num，否则子数组和为sum
    //2.res确定每一个子数组的最大和，求最大子数组和
    public int maxSubArray(int[] nums) {
        int premax = 0;
        int resmax = Integer.MIN_VALUE;
        for(int num:nums){
            if(premax >= 0) premax += num;
            else premax = num;
            resmax = Math.max(resmax, premax);
        }
        return resmax;
    }
}
