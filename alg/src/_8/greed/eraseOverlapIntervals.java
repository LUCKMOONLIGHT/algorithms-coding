package _8.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间 - 贪心
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * [ [1,2], [2,3], [3,4], [1,3] ]  1 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 先计算最多能组成的不重叠区间个数，然后用区间总个数减去不重叠区间的个数。
 * 在每次选择中，区间的结尾最为重要，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能
 * 够选择的区间个数也就越大。
 * 按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
 *
 * 时间复杂度O(nlogn) 空间复杂度O(1)
 */
public class eraseOverlapIntervals {
    //从终点的贪心算法:按照区间结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // -1：不交换位置，1：交换位置，从小到大排序
            }
        });
        int end = intervals[0][1];  //第一个end
        int count = 1;
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0] >= end){  //肯定：intervals[0][0] < intervals[0][1]
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    public static void main(String[] args) {
        eraseOverlapIntervals eraseOverlapIntervals = new eraseOverlapIntervals();
        int res = eraseOverlapIntervals.eraseOverlapIntervals(new int[][]{{1,11},{1,100},{2,12},{11,22}});
        System.out.println(res);
    }
}
