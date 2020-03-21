package _9.search;

//二分法找最后一个负数和第一个正数
public class binarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, -2, -8, -3, -10, 0, 0, 2, 3, 11};
        int res = binarySearch(nums, true);
        System.out.println(nums[res]);
    }
    public static int binarySearch(int[] nums, boolean is_negative){
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] > 0) r = mid - 1;  //缩小右边界
            else if(nums[mid] < 0) l = mid + 1; //缩小左边界
            else{ //等于0
                if(is_negative){  //找最后一个负数
                    r = mid - 1; //负数在0的右边
                    if(nums[r] < 0) return r;
                }else{ //找第一个正数
                    l = mid + 1;//正数在0的左边
                    if(nums[l] > 0) return l;
                }
            }
        }
        return 0;
    }
}
