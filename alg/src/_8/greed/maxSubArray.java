package _8.greed;

import java.util.Map;

/**
 * 最大子序和
 * 思路：preMax确定每一个子序列和 res确定最大子序列和
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
}
