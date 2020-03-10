package _3.binaryTree._1;

/**
 *  235.二叉搜索树的最近公共祖先   思路：找到一个结点在两个值之间即可
 *  236.二叉树的最近公共祖先 LeetCode 左神P153 [Medium]
 *  给定一棵二叉树, 找到该树中两个指定节点的最近公共祖先。
 *  后序遍历，先从左右子节点上找
 *  若left为NULL，因为题目保证有解，所以答案必在右边
 * 若left不为NULL，则看right是否为NULL，若right为NULL， 则答案一定是左边这个left。
 * 若左右都不为NULL， 说明root在中间，p和q在两边。该根结点一定是最近公共祖先
 *
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
//        if (!left) return right;
//        if (!right) return left;
//
//        return root;
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
