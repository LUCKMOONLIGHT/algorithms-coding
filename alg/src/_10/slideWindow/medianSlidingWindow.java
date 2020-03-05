package _10.slideWindow;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 480. 滑动窗口中位数 - 堆 HARD
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 给出一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 */
public class medianSlidingWindow {
    //用两个优先队列来模仿大顶堆和小顶堆，然后每次只要找小的一半平衡树的最大值和大的一半最小值即可得到中位数，复杂度为O(n(log(n)+log(k)))
    //维护两个平衡树,一个平衡树存k个数中小的一半,另一个存k个数中大的一半
    //数据首先从大根堆进入，然后大根堆将最大值给小根堆，大根堆最多比小根堆多一个元素
    //中位数：当两个堆的元素和==k时，大根堆元素多于小根堆，从大根堆中获得中位数；大根堆元素小于小根堆时，从小根堆中获得中位数，否则从两个堆中的堆顶获得元素和/2
    //平衡：若两个堆中的元素数量相差大于1，则从多数堆中poll给少数堆
    //大根堆堆顶保存的是最大值，offer是插入元素后需要调整堆结构，poll是删除堆顶元素，然后用最后一个元素替代堆顶元素

    /**
     * 1.创建大根堆小根堆
     * 2.每回合添加一个数进小根堆
     * 3.删除窗口外的值
     * 3.保持平衡3  大根堆堆顶要小于小根堆堆顶
     * 4.取中位数
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k +1];
        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o2 - o1 > 0.0d){
                    return 1; //交换
                }else if (o2 - o1 < 0.0d){
                    return -1;//不交换
                }else {
                    return 0;//不交换
                }
            }
        });
        int index = 0;
        for (int i =0; i < nums.length; i++){
            minHeap.offer((double) nums[i]);//每回合将元素放入小根堆

            //移除窗口外的值，判断maxHeap与minHeap哪个包含contains了num，remove
            if (minHeap.size() + maxHeap.size() > k){
                if (minHeap.contains((double) nums[i-k])){
                    minHeap.remove((double)nums[i-k]);
                }else {
                    maxHeap.remove((double)nums[i-k]);
                }
            }
            //维持平衡，保证1.maxHeap[1....size-1] <= maxHeap[0] <= minHeap[0] <= minHeap[1....size-1] 2.Math.abs(maxHeap.size()- minHeap.size()) <= 1
            //1.大小根堆的数量相差不为1
            //2.小根堆的堆顶的值大于大根堆堆顶的值
            //3.大小根堆的数量相差不为1
            if (minHeap.size() - maxHeap.size() > 1){
                maxHeap.offer(minHeap.poll());
            }
            if (maxHeap.size() > 0 && minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()){ //保证大根堆的堆顶小于小根堆的堆顶
                maxHeap.offer(minHeap.poll());//将小根堆的堆顶元素输出给大根堆
            }
            if (maxHeap.size() - minHeap.size() > 1){
                minHeap.offer(maxHeap.poll());
            }

            //求窗口中间值
            if (minHeap.size() + maxHeap.size() == k){

                if (minHeap.size() > maxHeap.size()){
                    res[index++] = minHeap.peek();
                }else if (minHeap.size() < maxHeap.size()){
                    res[index++] = maxHeap.peek();
                }else {
                    res[index++] = minHeap.peek()/2.0d + maxHeap.peek()/2.0d;
                }
            }
        }
        return res;
    }
}
