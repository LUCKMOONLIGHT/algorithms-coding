package _8.greed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 605. 种花问题 - 贪心算法
 * 1.最优子问题 - 左右间隔大于1的数量count
 * 2.全局最优 - count >= n
 */
public class canPlaceFlowers {
    /**
     * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n){
        int i=0,count=0;
        while(i<flowerbed.length){
            if ((flowerbed[i] == 0) && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)) {
                flowerbed[i++] = 1;
                count ++;
            }
            if (count >=n ) return true;
            i++;
        }
        return false;
    }

    /**
     * 1042. 不邻接植花
     * 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
     * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
     * 另外，没有花园有 3 条以上的路径可以进入或者离开。
     * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
     * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。
     * 思路：先用邻接表 表示，然后广度优先遍历处理
     *
     * 先把图构造出来，然后遍历每个节点的邻节点，当前节点+邻节点最多四个，这样就用邻节点没有用过的一个颜色将当前节点染色即可。
     * @param N
     * @param paths
     * @return
     */
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < N; i++)
            graph.put(i, new HashSet<>());
        for (int[] path: paths) {
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] used = new boolean[5];
            for (int adj: graph.get(i))
                used[res[adj]] = true;
            for (int j = 1; j <= 4; j++)
                if (!used[j])
                    res[i] = j;
        }
        return res;
    }
}
