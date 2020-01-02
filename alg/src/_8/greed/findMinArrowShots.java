package _8.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. 用最少数量的箭引爆气球 - 贪心算法
 */
public class findMinArrowShots {
    /**
     * 1.因为末端结点很重要，因此当前结点的start>末端节点end的时候，就需要要一根新的箭，记录最新的末端节点值end
     * O（nlogn） O（1）
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        int len = points.length;
        if (len < 2) {
            return len;
        }

        // 按照区间终点进行排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                if (point1[1] != point2[1]) {
                    return point1[1] - point2[1];
                }
                return point1[0] - point2[0];
            }
        });

        int count = 1;
        int end = points[0][1];
        for (int i = 1; i < len; i++) {
            if (points[i][0] > end) {
                // 就得多用一支箭，保存新结尾
                end = points[i][1];
                count++;
            }
        }
        return count;
    }

}
