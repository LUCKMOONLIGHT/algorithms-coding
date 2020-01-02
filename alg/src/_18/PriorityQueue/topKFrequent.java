package _18.PriorityQueue;

import java.util.*;

/**
 * 347 - 前k个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 思路：
 * 1.频率使用hashmap进行计数<num, fraq>
 * 2.前k个元素，使用优先队列进行排序，排序方法根据map.get(k)
 * 3.保持前k个元素。当超过k个时poll，否则offer
 */
public class topKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (Integer key : map.keySet()) {
            pq.offer(key);
            if (pq.size() > k)
                pq.poll();
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<Integer>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }
}
