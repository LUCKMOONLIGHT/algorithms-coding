package _10.slideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * K个不同整数的子数组  - 滑动窗口 没理解
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 *
 * A = [1,2,1,2,3], K = 2   7
 * 恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 *
 * 1，双指针，l指向左端，i作为右端不断往右滑动
 * 2，一旦可以凑出正好K个整数，则尝试滑动l找到当前情况下的所有可能，并记得复原状态
 */
public class subarraysWithKDistinct {
    public int subarraysWithKDistinct(int[] A, int K) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        boolean stopj = false;
        for (int i = 0, j = 0; i < A.length && j < A.length; ) {
            if (!stopj) {
                map.compute(A[j], (key, v) -> v == null ? 1 : v + 1);
            }
            int num = map.size();
            if (num == K) {
                count++;
                int k = j + 1;
                while (k < A.length && map.containsKey(A[k])) {
                    count++;
                    k++;
                }

                map.compute(A[i], (key, v) -> v == null ? 0 : v - 1);
                if (map.get(A[i]) == 0) {
                    map.remove(A[i]);
                }
                i++;
                stopj = true;
            } else {
                stopj = false;
                j++;
            }
        }
        return count;
    }
}
