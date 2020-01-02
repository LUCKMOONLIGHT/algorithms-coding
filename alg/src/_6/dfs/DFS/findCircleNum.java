package _6.dfs.DFS;

/** 朋友圈总数 - DFS 类似于环岛总数
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 *
 * 思路：因为如果m个人最多m个朋友圈，设置后visited后，相同的朋友圈会检测到visited[i]!=0就会不算数
 */
public class findCircleNum {
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) { //深度遍历每一个连通块
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) { //先遍历每一个连通块，如果连通块没有被访问到，深度遍历连通块，然后++
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

}

/** 并查集的实现
 * 1.初始化并查集
 *public class UnionFind {
 * 	Node[] node;   //父亲数组
 * 	//并查集中的结点
 * 	private static class Node{
 * 		int parent;
 * 		boolean root;
 *
 * 		private Node(){
 * 			parent = 1;
 * 			root = true;
 *                }* 	}
 * 	//将每个元素初始化为一颗单结点树
 * 	public UnionFind(int n){
 * 		node = new Node[n + 1];
 * 		for(int e= 0; e <= n; e++){
 * 			node[e] = new Node();
 *        }* 	}
 * }
 * ————————————————
 *2.find运算就是从元素e相应的结点走到树根处，找出所在集合的名字。
 *public int find(int e){
 * 		while(!node[e].root){
 * 			e = node[e].parent;
 *                }
 * 		return e;* 	}
 *
 * 3.union运算，合并两个集合，只要将表示其中一个集合的树的数根改为表示另一个集合的树的数根的儿子结点。
 * public void union(int a, int b){
 * 		node[a].parent += node[b].parent;
 * 		node[b].root = false;
 * 		node[b].parent = a;
 *        }
 *
 * 4.快速并查集的实现
 * 1.）小树合并到大树
 * 在union操作中，将表示小树的数根改为表示大树的数根的儿子结点。于是并查集中每个结点至多被移动O(logn)次，从而每个结点到数根的距离不会超过O(logn)。所有，每次find运算只需O(logn)时间。
 *
 * 	 合并两个集合(加速)
 * 	 将表示小树的数根改为表示大树的数根的儿子结点
*     public void union(inta, int b){
* 		if(node[a].parnt < node[b].parent){
* 			node[b].parnt+= node[a].parent;
* 			node[a].rot = false;
* 			node[a].parnt = b;
* 		}else{
* 			node[a].parnt+= node[b].parent;
* 			node[b].rot = false;
* 			node[b].parnt = a;
* 		}
 * 		}
 *
 * 	2.）路径压缩技术
 * 	在执行find时，实际上找到了从一个结点到数根的一条路径。路径压缩就是把这条路上所有的结点都提升1层，以加快找到根结点的时间。
 *  find运算(加速)
 *  从元素e相应的结点走到树根处，找出所在集合的名字
 *   public int find(int e){
 *   int current = e, p ,gp;
 *   排除当前结点或其父结点为根的情况后，加速find
 *   if(node[current].root){
 *           return current;
 *   }
 *   p = node[current].parent;
 *   if(node[current].root){
 *           return p;
 *   }
 *   gp = node[current].parent;
 *   while(true){
 *       node[current].parent = gp;
 *       if(node[gp].root){
 *           return gp;
 *       }
 *       current = p;
 *       p = gp;
 *       gp = node[p].parent;
 *      }
 *    }
 */
