package _3.binaryTree._1;

/**
 * 101.对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class isSymmetric {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public boolean isSymmetric(TreeNode root) {

        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {  //如果两棵树都为null
            return true;
        }
        if (node1 == null || node2 == null) { //如果只有其中一棵树为null
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left); //dfs递归对比两棵树
    }
}
