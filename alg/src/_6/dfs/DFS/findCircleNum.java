package _6.dfs.DFS;

import java.util.LinkedList;
import java.util.Queue;

/** 547.朋友圈总数 - DFS 类似于环岛总数
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。1.如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *2. 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 *
 * 给定的矩阵可以看成图的邻接矩阵。这样我们的问题可以变成无向图连通块的个数
 */
public class findCircleNum {
    /**
     使用一个visited数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点
     **/

    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) { //先遍历每一个连通块(人)，如果连通块没有被访问到，深度遍历连通块，然后++
            if (visited[i] == 0) {   //如果该连通块没有被访问到，dfs访问进行标记
                dfs(M, visited, i);  //dfs标记该连通块
                count++;  //记录连通块的个数
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) { //深度遍历每一个连通块
            if (M[i][j] == 1 && visited[j] == 0) {//本人i与其他人j的朋友圈关系
                visited[j] = 1;   //标记访问
                dfs(M, visited, j); //深度优先遍历
            }
        }
    }

    public static void main(String[] args){
        int[][] arr = new int[][]{{1,0,1,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
        findCircleNum findCircleNum = new findCircleNum();
        int res = findCircleNum.findCircleNum(arr);
        System.out.println(res);
    }

    //广度优先搜索
    //在广度优先搜索中，我们从一个特定点开始，访问所有邻接的节点。然后对于这些邻接节点，我们依然通过访问邻接节点的方式，知道访问所有可以到达的节点。因此，我们按照一层一层的方式访问节点
    //O(n2) O(n)
    public int findCircleNumII(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue< Integer > queue = new LinkedList< >();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {  //如果当前节点未访问过
                queue.add(i);       //加入到队列中
                while (!queue.isEmpty()) {  //队列如果不为空
                    int s = queue.remove(); //出队列
                    visited[s] = 1;         //标记访问
                    for (int j = 0; j < M.length; j++) {  //访问该节点下面关联的节点
                        if (M[s][j] == 1 && visited[j] == 0)  //如果节点之间存在关系，并且没有被访问过
                            queue.add(j);                    //节点加入队列，继续访问
                    }
                }
                count++;  //朋友圈范围1-n，判断每次未访问的个数
            }
        }
        return count;
    }
}



