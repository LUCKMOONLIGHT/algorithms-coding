package _8.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 会议室系列
 */
public class meetroom {
    /**
     * 给定一系列的会议时间间隔，包括起始和结束时间[[s1,e1]，[s2,e2]，…(si < ei)，确定一个人是否可以参加所有会议。
     * 思路：
     * 1.使用priorityqueue优先队列进行排序
     * 2.判断当前的结束时间与下一个起始时间是否有重叠
     * @param time
     * @return
     */
    public boolean meetroomI(int[][] time){
        if(time == null || time.length == 0) return true;
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        int len = time.length;
        int i=0;
        while (i < len - 1){
            if (time[i][1] > time[i+1][0]) return false;
            i++;
        }
        return true;
    }

    /**
     * 输入一个二维数组，数组的每个元素表示会议的开始时间和结束时间，问总共需要多少个会议室？
     * 思路：
     * 1.当前会议结束时间小于下一个会议开始时间则不需要会议室
     * 2.当前会议结束时间大于下一个会议开始时间则会议室+1
     * @param time
     * @return
     */
    public int meetroomII(int[][] time){
        if(time == null || time.length == 0) return 0;
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        int len = time.length;
        int i=0;
        int cnt = 1;
        while (i++ < len - 1){
            if (time[i][1] > time[i+1][0])  cnt++;
            else continue;
        }
        return cnt;
    }
}
