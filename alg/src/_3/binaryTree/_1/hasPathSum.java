package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;

//112. 路径总和
//给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
public class hasPathSum {
    //思路：1.当前节点为空 返回false 2.递归：目标值减去当前节点值，递归左右子树 3.临界点：当叶子节点是判断当前目标值是否满足
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        sum -= root.val; //状态变换
        if((root.left == null) && (root.right == null)) return (sum == 0);  //根节点到叶子节点的路径，判断是否满足条件
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);  //递归先序遍历，判断是否存在即可
    }



    //面试题34. 二叉树中和为某一值的路径
    //从树的根节点开始往下一直到叶节点所经过的节点形成一条路径，路径和为sum
    //得出路径和为sum的所有路径，需要回溯
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        dfs(root, sum, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(TreeNode root, int sum, int tmp, List<Integer> list, List<List<Integer>> res){
        tmp += root.val;
        list.add(root.val);
        if(tmp == sum && root.left == null && root.right == null){ //从根节点一直到叶子节点形成的一条路径
            res.add(new ArrayList<>(list));
        }
        if(root.left != null){
            dfs(root.left, sum, tmp, list, res);
            list.remove(list.size() - 1);  //删除root.left.val
        }
        if(root.right != null){
            dfs(root.right, sum, tmp ,list, res);
            list.remove(list.size() - 1);  //删除root.left.val
        }
    }
}
