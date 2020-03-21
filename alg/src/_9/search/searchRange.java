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
//    public int[] searchRange(int[] nums, int target) {
//        int start = binary(nums, target);//找第一次出现的位置
//        int end = binary(nums, target+1) - 1;
//        if(start == nums.length || nums[start] != target){
//            return new int[]{-1,-1};
//        }else{
//            return new int[]{start, Math.max(start, end)};
//        }
//    }
//    public int binary(int[] nums, int target){
//        int l=0,h=nums.length;//注意h的边界
//        while(l<h){
//            int mid = l+(h-l)/2;
//            if(nums[mid]<target) l=mid+1; //不等于的时候大步更新
//            else h=mid;
//        }
//        return l;
//    }

    public int[] searchRange(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1, lpos = -1, rpos = -1;
        //找最左边的pos
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            //如果存在target，那么hi一定指向最左边target的前一个pos，lo则一定指向target的下标
            if(nums[mid] >= target){
                hi = mid - 1; //有重复所以大步更新
            }else{
                lo = mid + 1;  //不等于所以大步更新
            }
        }
        lpos = lo;
        lo = 0;
        hi = nums.length - 1;
        //找最右边的pos
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] <= target){
                lo = mid + 1; //找右边的 有重复 所以大步更新
            }else{
                hi = mid - 1; //不等于所以大步更新
            }
        }
        rpos = hi;
        if(lpos == nums.length || nums[lpos] != target)
            return new int[]{-1,-1};
        return new int[]{lpos, rpos};
    }
}
