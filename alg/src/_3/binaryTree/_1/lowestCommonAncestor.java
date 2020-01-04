package _3.binaryTree._1;

/**
 *  二叉树的最近公共祖先 LeetCode 左神P153 [Medium]
 *  给定一棵二叉树, 找到该树中两个指定节点的最近公共祖先。
 *  后序遍历，先从左右子节点上找
 *  1,如果发现cur等于null,或者o1,o2。则返回cur.
*       2,如果left和right都为空，说明cur整棵子树都没有发现过o1 和o2,返回null.
*       3.如果left和right都不为空，说明左子树上发现过o1 和 o2，右子树上也发现过o1 和o2，说明o1 向上与o2向上的过程中，首次在cur相遇，返回cur.
*       4,如果left和right是有一个为空，另一个不为空，假设不为空的那个记为node，此时又两种可能，要么node是o1或o2中的一个，要么node已经是o1和o2的最近公共祖先节点，此时直接返回node即可。
 */
public class lowestCommonAncestor {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q){
        if (cur == null || cur == p || cur == q) return cur; //如果发现cur等于null,或者o1,o2。则返回cur.

        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);

        if(left != null && right != null){ //如果left和right都不为空，说明左子树上发现过o1 和 o2，右子树上也发现过o1 和o2，说明o1 向上与o2向上的过程中，首次在cur相遇，返回cur.
            return cur;
        }
        return left == null ? right : left; //如果left和right是有一个为空，另一个不为空，假设不为空的那个记为node，此时又两种，要么node是o1或o2中的一个，要么node已经是o1和o2的最近公共祖先节点，此时直接返回node即可。
    }

}
