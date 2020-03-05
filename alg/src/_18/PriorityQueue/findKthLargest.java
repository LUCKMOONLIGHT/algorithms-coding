package _18.PriorityQueue;

import java.util.PriorityQueue;

/**
 * 找数组中第K大的元素
 *
 * 使用小根堆
 * 1.每次插入一个数，并保证堆内的数不超过k个，迭代完后，根节点为第K大的数
 * 时间复杂度 : {O}(N \log k)O(Nlogk)。
 * 空间复杂度 : {O}(k)O(k)，用于存储堆元素。
 *
 * 使用快排
 *
 */
public class findKthLargest {
    public int findKthLargest(int[] nums, int k){
        if(nums.length < k) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((o1, o2) -> o1 - o2); //维护一个小根堆
        for (int num:nums){
            heap.offer(num);
            if (heap.size() > k){//当数量超过k时，输出堆顶
                heap.poll();  //每次poll的都是当前数组中的最小数，最后堆中保留的是k-n大的数，输出堆顶即可
            }
        }
        return heap.poll();
    }


    //面试题40. 最小的k个数
    //arr = [0,1,2,1], k = 1 [0]
    //arr = [3,2,1], k = 2  [1,2] 或者 [2,1]
    //思路：大根堆存放数字，每次输出最多的元素，留在队列里面的都是小数
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int num:arr){
            maxHeap.offer(num);
            if(maxHeap.size() > k) maxHeap.poll();
        }
        int[] res = new int[maxHeap.size()];
        int i = 0;
        while(maxHeap.size() > 0){
            res[i++] = maxHeap.poll();
        }
        return res;
    }
    /*final PriorityQueue<Integer> q ;
    final int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        q = new PriorityQueue<Integer>(k);
        for(int i: nums) {
            add(i);
        }
    }

    public int add(int val) {
        if(q.size() < k) {
            q.offer(val);

        }
        else if(q.peek() < val) {
            q.poll();
            q.offer(val);
        }
        return q.peek();
    }*/

    /**
     * 使用快排
     *
     */

    public int findKthLargest1(int[] nums, int k){
        // k是要求的第几大(从1开始计数),k-1即是数组中的序号(0开始计数)
        return findKthLargest2(nums,0,nums.length-1,k-1);
    }
    public int findKthLargest2(int[] nums,int low,int high,int k){
        int index = partition(nums,low,high,k);
        if (index == k)
            return nums[index];
        else if (index>k)
            return findKthLargest2(nums,low,index-1,k);
        else
            return findKthLargest2(nums,index+1,high,k);
    }

    // 同快排的partation,每次确定一个枢轴的位置,并返回其index
    // 找第k 大 的数就把数组按大->小排列
    private int partition(int[] nums,int low,int high,int k){
        int pivot = nums[low];

        while (low<high){
            while (low<high && nums[high]<=pivot) // nums[high]<=pivot 体现出把数组按大->小排列
                high--;
            nums[low] = nums[high];

            while (low< high && nums[low]>=pivot)
                low++;
            nums[high] = nums[low];
        }

        nums[low] = pivot;
        return low;
    }
}
