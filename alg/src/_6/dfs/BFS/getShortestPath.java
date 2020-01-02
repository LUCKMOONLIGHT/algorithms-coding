package _6.dfs.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 127. 单词接龙
 *  给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 解析：
 * 1。拥有一个startnode 和 endnode ，希望利用一些中间节点（单词）从 start node 到 end node，中间节点是 wordList 给定的单词。我们对这个单词接龙每个步骤的唯一条件是相邻单词只可以改变一个字母
 * 2.将问题抽象在一个无向无权图中，每个单词作为节点，差距只有一个字母的两个单词之间连一条边。问题变成找到从起点到终点的最短路径，如果存在的话。因此可以使用广度优先搜索方法
 *
 * 算法
 *
 * 1.对给定的 wordList 做预处理，找出所有的通用状态。将通用状态记录在字典中，键是通用状态，值是所有具有通用状态的单词。
 * 2.将包含 beginWord 和 1 的元组放入队列中，1 代表节点的层次。我们需要返回 endWord 的层次也就是从 beginWord 出发的最短距离。
 * 3.为了防止出现环，使用访问数组记录。
 * 4.当队列中有元素的时候，取出第一个元素，记为 current_word。
 * 5.找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
 * 6.从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
 * 7.对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
 * 8.最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
 *
 */
public class getShortestPath {
        //1.判断words链路中是否存在wndwords
        //2.根据连接条件构造连接图
        //3.使用BFS+队列进行查找
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            wordList.add(beginWord);
            int N = wordList.size();
            int start = N - 1;
            int end = 0;
            while (end < N && !wordList.get(end).equals(endWord)) {
                end++;
            }
            if (end == N) {
                return 0;
            }
            List<Integer>[] graphic = buildGraphic(wordList);
            return getShortestPath(graphic, start, end);
        }
        //构建有向图
        private List<Integer>[] buildGraphic(List<String> wordList) {
            int N = wordList.size();
            List<Integer>[] graphic = new List[N];
            for (int i = 0; i < N; i++) {
                graphic[i] = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (isConnect(wordList.get(i), wordList.get(j))) {
                        graphic[i].add(j);
                    }
                }
            }
            return graphic;
        }
        //判断是否连接
        private boolean isConnect(String s1, String s2) {
            int diffCnt = 0;
            for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diffCnt++;
                }
            }
            return diffCnt == 1;
        }
        //求最短连接
        private int getShortestPath(List<Integer>[] graphic, int start, int end) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] marked = new boolean[graphic.length];
            queue.add(start);
            marked[start] = true;
            int path = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                path++;
                while (size-- > 0) {
                    int cur = queue.poll();
                    for (int next : graphic[cur]) {
                        if (next == end) { //找到终点
                            return path;
                        }
                        if (marked[next]) { //防止回环
                            continue;
                        }
                        marked[next] = true; //标记为已访问并加入到队列中
                        queue.add(next);
                    }
                }
            }
            return 0;
        }
}
