package _8.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间 - 贪心算法 start排序
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 */
public class merge {
    /** 0.如果有两个输入的话，先合并
     *  1.边界值空值判断
     *  2.sort排序:按照start进行排序,然后按照end排序
     *  3.记录start end 当 i<n-1 && end >= intervals[i+1][0] 记录最大的end i++
     *  4.不相交时，将区间添加到List<int[]>中
     *  5.结果导出 list.toArrays(new int[res.size()][2])
     * O(nlogn) O(1)(or O(n))
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if(o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int i=0;
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        while (i < n){
            int start = intervals[i][0];
            int end = intervals[i][1];
            while (i<n-1 && end >= intervals[i+1][0]){
                end = Math.max(end, intervals[i+1][1]);
                i++;
            }
            res.add(new int[]{start,end});
            i++;
        }
        return res.toArray(new int[res.size()][2]);
    }


    /**
     * 986. 区间列表的交集
     * 给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。返回这两个区间列表的交集。
     *
     * A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     *
     * 思路：1.两个指针遍历AB数组
     * 2.先计算A[0] B[0] 的交集，然后根据end的位置选择i++或者j++
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<int[]>();
        int i=0,j=0;
        while(i<A.length && j<B.length){
            int max_l = Math.max(A[i][0], B[j][0]);
            int min_r = Math.min(A[i][1], B[j][1]);
            if(max_l <= min_r){
                res.add(new int[]{max_l, min_r});
            }

            if(A[i][1] < B[j][1]){
                i++;
            }else{
                j++;
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
