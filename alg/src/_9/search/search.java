package _9.search;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 */
public class search {
    /**
     * O(logn) 二分法
     * 1.mid 直接等于target
     * 2.在左半边递增区间
     * 3.在右半边递增区间
     * 终止条件 l>=r 判断nums[l]==target
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int l=0,r=nums.length - 1;
        while (l <= r){
            int mid = l + (r-l)/2;
            if (nums[mid] == target) return mid;
            else if(nums[mid] >= nums[l]){ //鉴别递增区间
                if (nums[l] <= target && target < nums[mid]) r=mid - 1;
                else l = mid + 1;
            }else{
                if(nums[mid] < target && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 搜索旋转排序数组 II 可能存在重复元素
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     *
     * 关键：重复元素的判断。判断为重复元素l++ r--
     *
     * 每次二分，左半部分和右半部分至少有一边是有序的，以此为条件可以分成两种情况：
     * //1、左半边是有序的
     * //(1) target落在左半边
     * //(2) otherwise
     * //2、右半边是有序的
     * //(1) target落在右半边
     * //(2) otherwise
     *
     * nums = [2,5,6,0,0,1,2], target = 0  true
     * nums = [2,5,6,0,0,1,2], target = 3  false
     * @param nums
     * @param target
     * @return
     */
    public boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int l=0, r=nums.length - 1;
        while (l <= r){
            int mid = l+(r-l)/2;
            if (nums[mid] == target) return true;
            if(nums[mid] == nums[r] && nums[l] == nums[mid]) {//元素重复，缩小长度
                l++;
                r--;
            }else if(nums[l] <= nums[mid]){ //在有序段中进行二分法
                if (nums[l] <= target && target < nums[mid]) r = mid -1;
                else l = mid + 1;
            }else{
                if (nums[mid] < target && target <= nums[r]) l=mid + 1;
                else r = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        search search = new search();
        boolean b = search.searchII(new int[]{2,5,6,0,0,1,2},0);
        System.out.println(b);
    }
}
