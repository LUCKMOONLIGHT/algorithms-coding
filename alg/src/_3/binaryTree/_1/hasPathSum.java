package _3.binaryTree._1;

//112. 路径总和
//给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
public class hasPathSum {
    //思路：1.当前节点为空 返回false 2.递归：目标值减去当前节点值，递归左右子树 3.临界点：当叶子节点是判断当前目标值是否满足
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        sum -= root.val; //状态变换
        if((root.left == null) && (root.right == null)) return (sum == 0);  //到了边界值后，不能继续迭代，判断是否满足条件
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);  //递归
    }
}
