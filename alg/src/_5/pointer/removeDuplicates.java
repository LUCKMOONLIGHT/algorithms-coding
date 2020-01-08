package _5.pointer;

/**
 * 26. 删除排序数组中的重复项
 * 原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度
 * 原地修改输入数组并在使用 O(1) 额外空间的条件下完成
 *
 * 1.一个指针找前后不一致的地方
 * 2.然后给nums重新赋值
 *
 * nums = [1,1,2]
 * 返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2
 * 不需要考虑数组中超出新长度后面的元素
 */
public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        int cnt = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i] != nums[i-1]) {
                nums[cnt] = nums[i];
                cnt++;
            }
        }
        return cnt;
    }
}
