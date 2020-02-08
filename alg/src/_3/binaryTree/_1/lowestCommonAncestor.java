package _3.binaryTree._1;

/**
 *  235.二叉搜索树的最近公共祖先   思路：找到一个结点在两个值之间即可
 *  236.二叉树的最近公共祖先 LeetCode 左神P153 [Medium]
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
        if (cur == null || cur == p || cur == q) return cur; //当遍历到叶结点后就会返回null，当找到p或者q的是时候就会返回pq

        TreeNode left = lowestCommonAncestor(cur.left, p, q); //返回的结点，可能是null、pq和公共祖先结点
        TreeNode right = lowestCommonAncestor(cur.right, p, q);

        if(left != null && right != null){ //如果左右都存在，就说明pq都出现了，这就是，公共祖先
            return cur;
        }
        return left == null ? right : left; //否则我们返回已经找到的那个值（存储在left，与right中），p或者q
    }

    //1123. 最深叶节点的最近公共祖先
    //左右子树深度相同，表示获取到了最深叶子节点的最近公共祖先
    //最深叶子节点肯定在深度较大的子树当中
    //采用深度优先遍历，每次往深度更大的子树递归
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) return root; //
        int l = depth(root.left);
        int r = depth(root.right);

        if(l == r) return root;
        if(l > r) return lcaDeepestLeaves(root.left);
        else return lcaDeepestLeaves(root.right);
    }

    //求结点的深度
    public int depth(TreeNode node){
        if(node == null) return 0;
        int l = depth(node.left);
        int r = depth(node.right);

        return 1 + Math.max(l,r);
    }
}
