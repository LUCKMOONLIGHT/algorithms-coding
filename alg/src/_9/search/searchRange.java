package _9.search;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * nums = [5,7,7,8,8,10], target = 8
 * [3,4]
 */
public class searchRange {
    /**
     * 利用二分思想先找其左边界，再找其右边界即可，
     * 注意找左边界的时候，由右侧逼近；找右边界的时候，由左侧逼近，即可。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = binary(nums, target);
        int end = binary(nums, target+1) - 1;
        if(start == nums.length || nums[start] != target){
            return new int[]{-1,-1};
        }else{
            return new int[]{start, Math.max(start, end)};
        }
    }
    public int binary(int[] nums, int target){
        int l=0,h=nums.length;//注意h的边界
        while(l<h){
            int mid = l+(h-l)/2;
            if(nums[mid]<target) l=mid+1;
            else h=mid;
        }
        return l;
    }
}
