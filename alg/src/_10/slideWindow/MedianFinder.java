package _10.slideWindow;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 */
public class MedianFinder {
    PriorityQueue<Double> minHeap;
    PriorityQueue<Double> maxHeap;
    /** 初始化大小根堆*/
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o2 - o1 > 0.0d){
                    return 1;
                }else if (o2 - o1 < 0.0d){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
    }

    /**
     * 1.添加元素到小根堆
     * 2.保持平衡
     * @param num
     */
    public void addNum(int num) {
        minHeap.offer((double) num);
        if (minHeap.size() - maxHeap.size() > 1){
            maxHeap.offer(minHeap.poll());
        }
        if (maxHeap.size() > 0 && minHeap.size() > 0 && maxHeap.peek() > minHeap.peek()){
            maxHeap.offer(minHeap.poll());
        }
        if (maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
        }
    }
    //寻找中位数
    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) return minHeap.peek();
        else if(minHeap.size() < maxHeap.size()) return maxHeap.peek();
        else return minHeap.peek()/2.0d + maxHeap.peek()/2.0d;
    }
}
