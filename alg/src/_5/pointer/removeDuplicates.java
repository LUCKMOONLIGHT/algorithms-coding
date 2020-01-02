package _5.pointer;

/**
 * 删除排序数组中的重复项
 */
public class removeDuplicates {
    public int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int slow = 0;
        int fast = 1;
        while (fast < nums.length){
            if (nums[slow] == nums[fast]){
                fast ++;
            }else {
                swap(nums, ++slow, fast++);
            }
        }
        return slow + 1;
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
