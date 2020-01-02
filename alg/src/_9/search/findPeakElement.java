package _9.search;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 */
public class findPeakElement {
    /**
     * 由于题目假设nums[-1]=nums[n]=-∞。所以，我们从0开始往后遍历元素，如果某个元素大于其后面的元素，则该元素就是峰值元素。（但是它时O(n)，不符合题意）
     *
     * O(logN)一般考虑二分搜索。有如下规律：
     *
     * 规律一：如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素
     *
     * 规律二：如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int l=0,r=nums.length-1;
        while(l < r){
            int mid=l+(r-l)/2;
            if(nums[mid] > nums[mid + 1]) r=mid;
            else l=mid+1;
        }
        return l;
    }
}
