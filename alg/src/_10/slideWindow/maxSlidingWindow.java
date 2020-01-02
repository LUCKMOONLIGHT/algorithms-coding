package _10.slideWindow;

import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 */
public class maxSlidingWindow {
    /**
     * 1.初始化大根堆 2.维护窗口大小 3.满足窗口大小，保存最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i=0;i<nums.length;i++){
            maxHeap.offer(nums[i]);
            if (maxHeap.size() > k){
                maxHeap.remove(nums[i - k]);
            }
            if (maxHeap.size() == k)
                res[index++] = maxHeap.peek();
        }
        return res;
    }
}
