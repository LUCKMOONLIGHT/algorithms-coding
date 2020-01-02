package _3.binaryTree._1;

/**
 * 判断B树是不是A树的子树
 *
 * 1.递归判断B是不是A相同 || B是不是跟A的子树相同
 * 2.递归判断A,B的值 && A,B左右子树的值
 */
public class isSubtree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public boolean isSubtree(TreeNode s, TreeNode t){
        if(s == null || t == null) return s == null && t == null;
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    private boolean isSameTree(TreeNode s, TreeNode t){
        if(s == null || t == null) return s == null && t == null;
        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
