package _9.search;

/**
 * 540. 有序数组中的单一元素
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 * [1,1,2,3,3,4,4,8,8]
 */
public class singleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        int l=0,h=nums.length-1;
        while(l < h){
            int m = l+(h-l)/2;
            if(m % 2 !=0) m--; //保持m在偶数位
            if(nums[m] == nums[m+1]) l=m+2; //偶数位对相等，说明单数在后面
            else h=m; //当不相等时，说明在前面搜索旋转排序数组
        }
        return nums[l];
    }

    public int singleNonDuplicateII(int[] nums) {
        int a = nums[0];
        for(int i = 1;i<nums.length;i++){
            a ^= nums[i];
        }
        return a;
    }
}
