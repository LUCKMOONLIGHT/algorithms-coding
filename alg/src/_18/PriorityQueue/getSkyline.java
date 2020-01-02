package _18.PriorityQueue;

import java.util.*;

/**
 * 218. 天际线问题 - 大小堆扫描 difficult
 * 输出由这些建筑物形成的天际线
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
 * [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]
 *
 * 关键是：拐点的判断 https://blog.csdn.net/whdAlive/article/details/81152670
 */
public class getSkyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> resList = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return resList;
        }
        //定义比较器，先按pos从小到大排序，pos相等，按height从小到大排
        PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.pos != o2.pos) {
                return o1.pos - o2.pos;
            }
            if (o1.height != o2.height) {
                return o1.height - o2.height;
            }
            return 0;
        });
        //生成queue，<第一个位置，负高度>，<第二个位置，正高度>
        //将矩形拆分为，左上角坐标和右上角坐标，同时为了区分二者，将左上角的纵坐标设为负值
        for (int i = 0; i < buildings.length; i++) {
            queue.offer(new Point(buildings[i][0], -buildings[i][2]));
            queue.offer(new Point(buildings[i][1], buildings[i][2]));
        }
        //创建一个大顶堆
        PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Comparator.reverseOrder());
        //加入地平线
        maxHeightQueue.offer(0);
        //记录上一次的最大高度
        int prePeak = maxHeightQueue.peek();
        //遍历队列
        while (!queue.isEmpty()) {
            //当当前高度是负数，说明是上升的，加到maxHeightQueue，反之移除掉
            Point point = queue.poll();
            if (point.height < 0) { //说明是上升的，加入左上角坐标
                maxHeightQueue.offer(-point.height);
            } else {
                //遇到右上角坐标表示这个矩形已经处理完成，将左上角坐标移出即可
                maxHeightQueue.remove(point.height);
            }
            //当前的最大高度
            int curPeak = maxHeightQueue.peek();
            //两次最大高度不一致，说明前一个最大高度被移除，或者新加入了更高的building
            if (curPeak != prePeak) {
                //添加拐点
                resList.add(Arrays.asList(point.pos, curPeak));
                //更新最大高度
                prePeak = curPeak;
            }
        }
        return resList;
    }
    class Point {
        int pos, height; //起始或者结束位置，高度

        public Point(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }
}
