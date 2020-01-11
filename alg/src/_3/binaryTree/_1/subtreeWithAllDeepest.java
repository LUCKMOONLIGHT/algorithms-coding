package _3.binaryTree._1;

//865. 具有所有最深结点的最小子树
//输入：[3,5,1,6,2,0,8,null,null,7,4]
//输出：[2,7,4]
public class subtreeWithAllDeepest {
    //0.在最深节点中找最小子树
    //1.判断左右子节点哪个更深
    //2.从更深的节点开始递归，当左右节点相等时，返回最深节点的最小子树
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;
        int lnode = maxDepth(root.left);
        int rnode = maxDepth(root.right);

        if(lnode == rnode) return root;
        else if(lnode > rnode) return subtreeWithAllDeepest(root.left);
        return subtreeWithAllDeepest(root.right);
    }
    //求二叉树的最大深度
    int maxDepth(TreeNode root){
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
