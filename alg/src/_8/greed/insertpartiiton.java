package _8.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间  -difficult 贪心
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 */
public class insertpartiiton {
    /**
     * 贪心算法：从最简单的子结构开始拆分
     * 1.当interval整体在newInterval整体之前时：List<int[]> res直接添加interval整体，并将插入位置insertPos+1；
     * 2.当interval整体在newInterval整体之后时：说明此时newInterval已经添加☞List<int[]> res中，直接添加interval整体；
     * 3.interval与newInterval存在相交的情况，(不用分析有几个interval与newInterval相交了)，只要相交了，将interval与newInterval融合：
     * 1.newInterval[0] = Math.min(interval[0], newInterval[0]);
     * 2.newInterval[1] = Math.max(interval[1], newInterval[1]);
     * 4.最后遍历完interval，将newInterval插入到res指定的位置insertPos；
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insertI(int[][] intervals, int[] newInterval){
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        List<int[]> results = new ArrayList<int[]>();
        int insertPos = 0;//插入位置

        for (int[] interval : intervals) {//遍历intervals
            if (interval[1] < newInterval[0]) {//如果当前区间的end小于新区间的start，说明无重叠，直接添加
                results.add(new int[]{interval[0], interval[1]});
                insertPos++;//插入位置+1
            } else if (interval[0] > newInterval[1]) {//如果当前区间的start小于新区间的end，说明无重叠，直接添加
                results.add(new int[]{interval[0], interval[1]});
            } else {//否则一定有重叠，取两个区间的最小start，和最大end, 作为新区间
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }

        results.add(insertPos, new int[]{newInterval[0], newInterval[1]});

        return results.toArray(new int[results.size()][2]);
    }

    /**
     * 输入: intervals = [[1,3],[6,9]], newInterval = [[2,5], [8, 10]]
     * 输出: [[1,5],[6,10]]
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insertII(int[][] intervals, int[][] newInterval){
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        List<int[]> results = new ArrayList<int[]>();
        int cnt = 0;
        for (int[] nInterval:newInterval){
            int insertPos = 0;//插入位置
            if (cnt++ >= 1){
                intervals = results.toArray(new int[results.size()][2]);//从第二次开始使用第一次的结果进行合并比较
            }
            results = new ArrayList<int[]>();
            for (int[] interval : intervals) {//遍历intervals
                if (interval[1] < nInterval[0]) {//如果当前区间的end小于新区间的start，说明无重叠，直接添加
                    results.add(new int[]{interval[0], interval[1]});
                    insertPos++;//插入位置+1
                } else if (interval[0] > nInterval[1]) {//如果当前区间的start小于新区间的end，说明无重叠，直接添加
                    results.add(new int[]{interval[0], interval[1]});
                } else {//否则一定有重叠，取两个区间的最小start，和最大end, 作为新区间
                    nInterval[0] = Math.min(interval[0], nInterval[0]);
                    nInterval[1] = Math.max(interval[1], nInterval[1]);
                }
            }

            results.add(insertPos, new int[]{nInterval[0], nInterval[1]});
        }


        return results.toArray(new int[results.size()][2]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval1 = {2,5};
        int[][] newInterval2 = {{2,5},{8,10}};
        insertpartiiton insertpartiiton = new insertpartiiton();
//        int[][] res = insertpartiiton.insertI(intervals, newInterval1);
        int[][] res = insertpartiiton.insertII(intervals, newInterval2);
        for (int[] r:res){
            System.out.println(r[0]+" "+r[1]);
        }
    }
}
