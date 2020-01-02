package _9.search;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。请找出其中最小的元素。
 * [3,4,5,1,2] 1
 */
public class findMin {
    /**
     * 1.找降序段，进行二分法
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        //一个元素时，返回其自身
        if (nums.length == 1) return nums[0];
        int l = 0, r = nums.length - 1;
        //升序的话，返回第一个元素
        if (nums[0] < nums[r]) return nums[0];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            //分别比较前后两个元素，判断是否满足条件
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];
            if (nums[mid - 1] > nums[mid]) return nums[mid];
            //说明0-mid中出现了先升后将的趋势
            if (nums[0] > nums[mid]) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * 请找出其中最小的元素。
     * 注意数组中可能存在重复的元素。
     * 1.判断降序序列在左右段的哪一边
     * 2.关键：重复元素的判断 r--
     * @param nums
     * @return
     */
    public int findMinII(int[] nums) {
        if(nums.length == 1) return nums[0];
        int l=0,r=nums.length - 1;
        while(l < r){
            int mid = l+(r-l)/2;
            if(nums[mid] > nums[r]) l = mid + 1; //包含降序
            else if(nums[mid] < nums[r]) r = mid;//不包含降序
            else r--; //遇到重复元素，r-- [3,3,1,3]
        }
        return nums[l];
    }
}

    /*if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //注意right的取值，比较的值
            if (nums[mid] <= nums[right]) right = mid;
            else left = mid + 1;
        }
        return nums[left];
    */
