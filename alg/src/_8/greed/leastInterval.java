package _8.greed;

import java.util.Arrays;

/**
 * 621. 任务调度器  - 贪心算法  贪心地将出现次数较多的任务安排在前面
 * 一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 *
 *  tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *
 */
public class leastInterval {
    /**
     * 0.使用数组int[] 按照出现次数排序，选择执行任务
     * 1.由于相同的任务之间必须有 n 的冷却时间，所以我们可以想到按照任务的数量来安排它们，即一种任务的出现次数越多，我们就越早地安排
     * 2.n + 1 个任务为一轮
     * 3.每一轮，将当前的任务按照它们剩余的次数降序排序
     * 4.选择剩余次数最多的n+1个任务执行
     * 5.如果任务的种类 t 少于 n + 1 个，就只选择全部的 t 种任务，其余的时间空闲
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalI(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);//从小到大排序
        int time = 0;
        while (map[25] > 0) {//次数最大的不为0
            int i = 0;
            while (i <= n) {//周期内
                if (map[25] == 0)//全部任务执行完毕
                    break;
                if (i < 26 && map[25 - i] > 0)//从次数高到低执行
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);//从小到大排序
        }
        return time;
    }

}
