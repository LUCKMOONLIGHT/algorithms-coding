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
            if(nums[m] == nums[m+1]) l=m+2; //当偶数位两位相等时，右移两位
            else h=m; //当不相等时，说明在前面
        }
        return nums[l];
    }
}
