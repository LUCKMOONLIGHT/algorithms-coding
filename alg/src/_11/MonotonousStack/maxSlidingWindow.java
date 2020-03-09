package _11.MonotonousStack;

import java.util.LinkedList;

//面试题59 - I. 滑动窗口的最大值
//滑动窗口最大值  1.优先队列   2.单调栈
// 1 2 3 4
public class maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < k || k < 1) return new int[0];
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            while (!list.isEmpty() && nums[list.peekLast()] < nums[i]){  //求窗口内的最大值
                list.pollLast();
            }
            list.addLast(i); //记录坐标
            if(list.peekFirst() == i-k) list.pollFirst(); //将窗口外的元素移出双向队列
            if(i >= k-1) res[index++] = nums[list.peekFirst()]; //每回合将双向队列头元素添加到数组中
        }
        return res;
    }
}
