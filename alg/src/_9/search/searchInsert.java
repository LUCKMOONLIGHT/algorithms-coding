package _9.search;


//35. 搜索插入位置
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
public class searchInsert {
    public int searchInsert(int[] nums, int target) {
        int i=0,j=nums.length-1;
        while(i<=j){
            int m = i+(j-i)/2;
            if(nums[m] < target) i = m + 1;
            else if(nums[m] > target) j = m - 1;
            else return m;
        }
        return i;
    }
}
