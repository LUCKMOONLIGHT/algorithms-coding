package _3.binaryTree._1;

/**
 * 二叉树的最大路径和 - 递归 HARD
 *
 * 1.递归获取每个子节点的最大路径和
 * 2，保存其中的最大路径和
 *
 *  最大路径和：根据当前节点的角色，路径和可分为两种情况：
 *     一：以当前节点为根节点
 *     1.只有当前节点
 *     2.当前节点+左子树
 *     3.当前节点+右子书
 *     4.当前节点+左右子树
 *     这四种情况的最大值即为以当前节点为根的最大路径和
 *     此最大值要和已经保存的最大值比较，得到整个树的最大路径值
 *
 *     二：当前节点作为父节点的一个子节点
 *     和父节点连接的话则需取【单端的最大值】
 *     1.只有当前节点
 *     2.当前节点+左子树
 *     3.当前节点+右子书
 *     这三种情况的最大值
 */
public class max_gainTreeNode {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }
    private int ret = Integer.MIN_VALUE;

    private int getMax(TreeNode r) {
        if(r == null) return 0;
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        ret = Math.max(ret, r.val + left + right); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和；该结点作为根结点组成最大路径和结点进行比较
        return Math.max(left, right) + r.val; //该节点作为子节点
    }
}
