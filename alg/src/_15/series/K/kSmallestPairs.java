package _15.series.K;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. 查找和最小的K对数字
 *
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 *
 *  nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 *  [1,2],[1,4],[1,6]
 */
public class kSmallestPairs {
    //思路1：列举出全部的list<Integer>组合，然后使用PriorityQueue排列保存k个最大堆
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> queue=new PriorityQueue<>(k,new Comparator<List<Integer>>() { //比较每个list的和，维护一个最小堆

            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                // TODO Auto-generated method stub
                int sum1=0,sum2=0;
                for(int i=0;i<o1.size();i++) {
                    sum1+=o1.get(i);
                }
                for(int i=0;i<o1.size();i++) {
                    sum2+=o2.get(i);
                }
                return sum2-sum1;
            }
        });
        for(int i=0;i<nums1.length;i++) {
            for(int j=0;j<nums2.length;j++) {
                List<Integer>list=new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                if(queue.size()<k) {

                    queue.add(list);
                }else {
                    if(queue.comparator().compare(list, queue.peek())>0) {//当前元素小于堆顶的时候，删除堆顶，加入队列
                        queue.remove();
                        queue.add(list);
                    }
                }
            }
        }
        List<List<Integer>>lists=new ArrayList<>();
        while (!queue.isEmpty()) {
            lists.add(0,queue.remove());

        }
        return lists;
    }
}
