package _5.pointer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Leetcode 209:长度最小的子数组
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的子数组。如果不存在符合条件的子数组，返回 0。
 *
 * 方法：使用滑动窗口，双指针的办法
 * 1.当sum小于target的时候，右指针右移
 * 2.当sum大于target的时候，左指针左移
 * 3.当满足条件时，选择最小的长度
 * O（n）
 */
public class minSubArrayLen {
    public int getminSubArrayLen(int[] nums, int s){
        int n = nums.length;
        if(nums == null || n == 0) return 0;
        int sum = 0;
        int l = 0;
        int res = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            sum += nums[i];
            while(sum >= s){
                res = Math.min(res, i-l+1);
                sum -= nums[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    //862. 和至少为 K 的最短子数组   57ms
    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N+1]; //数组各个长度的和
        for (int i = 0; i < N; i++)
            P[i+1] = P[i] + (long) A[i];

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N+1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; y++) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()]) //让队列保持递增
                monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)//满足条件时，移除队首，求最短长度
                ans = Math.min(ans, y - monoq.removeFirst());

            monoq.addLast(y);
        }

        return ans < N+1 ? ans : -1;
    }

    //思路：1.迭代每个起点i，从起点开始迭代直到终点j，求和至少为 K 的最短子数组  1855ms

    public int shortestSubarrayI(int[] A, int K) {
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < A.length; i++){
            if(A[i] <= 0) continue;
            int sum = 0;
            int j = i;
            while (j < A.length){
                sum += A[j];
                if(sum >= K){
                    ret = Integer.min(j-i+1,ret);
                    break;
                }
                j++;
            }
        }
        return ret==Integer.MAX_VALUE?-1:ret;
    }

    public static void main(String[] args) {
        minSubArrayLen minSubArrayLen = new minSubArrayLen();
        int res = minSubArrayLen.shortestSubarray(new int[]{-28,81,-20,28,-29},89);
        System.out.println(res);
    }
}
