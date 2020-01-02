package _2.linked;

/**
 * 下一个排列 - 一遍扫描，2次交换
 * 描述：实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 1.找到第一对两个连续的数字 a[i] 和 a[i-1]，它们满足 a[i]>a[i-1]
 * 2.从右开始找到第一个 nums[j] > nums[i]的数字，然后交换swap
 * 3.需要重新排列 a[i-1] 右边的数字，以获得下一个最小的字典排列
 */
public class nextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
