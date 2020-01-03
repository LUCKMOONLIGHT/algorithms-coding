package _3.binaryTree._1;

//104. 二叉树的最大深度
//给定一个二叉树，找出其最大深度。
//思路：递归，左右子树的最大深度+1
public class maxDepth {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}
