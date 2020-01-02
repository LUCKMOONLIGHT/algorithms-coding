package _18.PriorityQueue;

import java.util.PriorityQueue;

/**
 * 无序数组的中位数
 * 1.大小根堆的思想，保持大根堆大于等于小跟队 维持平衡
 *
 *
 * 滑动窗口的中位数
 * 1.迭代每个数，//移除窗口外的值
 *             if (minHeap.size() + maxHeap.size() > k){
 *                 if (minHeap.contains((double) nums[i-k])){
 *                     minHeap.remove((double)nums[i-k]);
 *                 }else {
 *                     maxHeap.remove((double)nums[i-k]);
 *                 }
 *             }
 * 2.分别加入大根堆和小根堆，维持平衡
 *
 */
public class ArrMidNum
{

    /**
     * 快排思想-直至mid为(size-1)/2
     *
     */
    public static int getMidNum(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }

        int size = arr.length;
        int targetMid = (size-1)/2;

        int low = 0;
        int high = size - 1;

        int mid = getMid(arr, low, high);
        while (mid != targetMid) {
            if (mid < targetMid) {
                mid = getMid(arr, mid + 1, high);
            } else {
                mid = getMid(arr, low, mid - 1);
            }
        }

        return arr[mid];
    }

    /**
     * 快排思想-一趟排序
     */
    private static int getMid(int[] arr, int low, int high) {
        int base = arr[low];
        while (low < high) {
            // 判断条件必须加=场景，为<= 不能为<，否则数组中有相同数据时，会一直循环
            while (low < high && base <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];

            // 判断条件必须加=场景，为>= 不能为>，否则数组中有相同数据时，会一直循环
            while (low < high && base >= arr[low]) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = base;
        return low;
    }

    /**
     * 大小根堆排序思想
     *
     * 1.创建两个堆，分别为大根堆和小根堆
     * 2.迭代数字，将数据加入大根堆中，然后将大根堆的top加入到小根堆中
     * 3.当大根堆的size小于小根堆，将小根堆的top加入到大根堆中
     * 4.中位数根据两个堆size继续判断。当两个堆大小一致时，取两个堆的top求平均，当大根堆多一个时，取大根堆top
     *
     */

    public int ArrMidNum3(int[] nums){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); //大根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); //小根堆

        for (int num:nums){
            maxHeap.offer(num);
            minHeap.offer(maxHeap.peek());
            maxHeap.poll();
            if (maxHeap.size() < minHeap.size()){
                maxHeap.offer(minHeap.poll());
            }
        }
        return minHeap.size() > maxHeap.size() ? minHeap.peek() : (minHeap.peek() + maxHeap.peek())/2;
    }

}
