package _9.search;

import java.util.Stack;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 */
public class search {

    //二分查找 O(logn) O(1)
    public int search(int[] nums, int target) {
        Stack<String> stack = new Stack<>();
        if(nums == null || nums.length == 0) return -1;
        int l = 0, r = nums.length - 1, mid;
        while(l <= r){
            mid = l + (r - l)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) r = mid -1;
            else l = mid + 1;
        }
        return -1;
    }
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
    public int searchI(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int l=0,r=nums.length - 1;
        while (l <= r){
            int mid = l + (r-l)/2;
            if (nums[mid] == target) return mid;
            else if(nums[mid] >= nums[l]){ //判断中间节点于l节点的大小，当为递增区间时，在递增区间二分法
                if (nums[l] <= target && target < nums[mid]) r=mid - 1;
                else l = mid + 1;
            }else{//当为递减区间时，在后面的递增区间用二分法
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
    public boolean searchnumberII(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int l=0, r=nums.length - 1;
        while (l <= r){
            int mid = l+(r-l)/2;
            if (nums[mid] == target) return true;
            if(nums[mid] == nums[r] && nums[l] == nums[mid]) {//当中左和中右相等时，元素重复，缩小区间
                l++;
                r--;
            }else if(nums[l] <= nums[mid]){ //前半段为有序段时，在前半段进行二分查找
                if (nums[l] <= target && target < nums[mid]) r = mid -1;
                else l = mid + 1;
            }else{//后半段有序时，在后半段进行有序查找
                if (nums[mid] < target && target <= nums[r]) l=mid + 1;
                else r = mid - 1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        search search = new search();
        int b = search.search(new int[]{-1,0,3,5,9,12},2);
        System.out.println(b);
    }


    //面试题53 - I. 在排序数组中查找数字 I
    //输入: nums = [5,7,7,8,8,10], target = 8
    //输出: 2
    //思路：二分法 1.找最左边的pod  2.找最右边的pos 3.rpos-lpos+1
    public int searchnumberI(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1, lpos = -1, rpos = -1;
        //找最左边的pos
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            //如果存在target，那么hi一定指向最左边target的前一个pos，lo则一定指向target的下标
            if(nums[mid] >= target){
                hi = mid - 1;
            }else{
                lo = mid + 1;
            }
        }
        lpos = lo;
        lo = 0;
        hi = nums.length - 1;
        //找最右边的pos
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] <= target){
                lo = mid + 1;
            }else{
                hi = mid - 1;
            }
        }
        rpos = hi;
        return rpos - lpos + 1;
    }

//    public static void main(String[] args) {
//        _0.array.search search = new _0.array.search();
//        int res = search.search(new int[]{5,7,7,8,8,10}, 8);
//        System.out.println(res);
//    }


    //面试题53 - II. 0～n-1中缺失的数字
    //
    //一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
    // 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
    //输入: [0,1,2,3,4,5,6,7,9]
    //输出: 8
    //思路:二分法，判断nums[mid] = mid，表示左边不缺失，移动l，否则表示左边缺失，移动r，返回l
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] == mid) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }
}
