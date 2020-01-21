package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;

//95. 不同的二叉搜索树 II

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 *
 */


public class generateTrees {
    /**
     * 思路：递归
     * 1.以1-n每个点作为根节点
     * 2.左右节点进行迭代，组合成当前节点返回
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<TreeNode>();
        return dfs(1, n);
    }

    public List<TreeNode> dfs(int s, int e){
        List<TreeNode> all_tree = new ArrayList<>();
        if(s > e){
            all_tree.add(null);
            return all_tree;
        }

        for(int i=s;i<=e;i++){
            List<TreeNode> left = dfs(s, i-1);
            List<TreeNode> right = dfs(i+1, e);
            for(TreeNode l:left){
                for(TreeNode r:right){
                    TreeNode cur = new TreeNode(i);
                    cur.left = l;
                    cur.right = r;
                    all_tree.add(cur);
                }
            }
        }
        return all_tree;
    }
}
