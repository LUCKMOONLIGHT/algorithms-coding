package _3.binaryTree._1;

/**
 * 101.对称二叉树
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
        if (node1 == null && node2 == null) {//如果左右都为空，true
            return true;
        }
        if (node1 == null || node2 == null) {//如果左右只有一个为null，false
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);//对比当前结点，左节点，右节点
    }
}
