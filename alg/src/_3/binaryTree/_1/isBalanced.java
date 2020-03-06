package _3.binaryTree._1;

//110. 平衡二叉树
//面试题55 - II. 平衡二叉树
public class isBalanced {
    //思路：1.dfs(左子树)&&dfs(右子树)&&Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2;
    }
    public int maxDepth(TreeNode node){
        if(node == null) return 0;
        return Math.max(maxDepth(node.left), maxDepth(node.right))+1;
    }
}
