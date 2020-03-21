package _9.search;

public class total {
    public static void main(String[] args) {

    }

    //二分查找 O(logn) O(1)
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;  //非空判断
        int l = 0, r = nums.length - 1, mid; //初始三指针
        while(l <= r){//循环条件，需要将数组循环到空为止
            mid = l + (r - l) / 2;//中间节点的计算
            //三种条件，当前指针指向的数大于target，小于target，等于target
            if(nums[mid] == target) return mid;//循环程序的出口，截至条件
            if(nums[mid] > target) r = mid -1;//给定值key一定在左边，并且不包括当前这个中间值
            else l = mid + 1;//给定值key一定在右边，并且不包括当前这个中间值
        }
        return -1;//默认结果
    }

    //允许数据重复，找出第一个大于key的数
    public int searchI(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        int l = 0, r = nums.length - 1, mid;
        while(l < r){ //允许数据重复，当数组中只剩下两个数，结果一定在l
            mid = l + (r - l) / 2;
            if(nums[mid] > target) r = mid - 1;  // 解一定只可能在arr[low,mid-1]，即左边
            else if(nums[mid] < target) l = mid + 1;  // 解一定只可能在arr[mid+1,high]，即右边
            else r = mid;  //mid有可能是解，也可能在arr[low,mid-1]即左边,但可以肯定的是解一定只可能在arr[low,mid]中
        }
        if(nums[l] == target) return l;
        return -1;
    }

    //查找第一个等于或者大于Key的元素的位置
    public int searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int l = 0, r = nums.length - 1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid - 1;
            else if (nums[mid] <= target) l = mid + 1;
        }
        return l;
    }

    //查找第一个大于key的元素的位置
    //查找最后一个等于或者小于key的元素的位置
    //查找最后一个小于key的元素的位置
    //快速判断基于当前mid位置不是解得情况   大步移动 mid+1 不确定一定 mid

}
