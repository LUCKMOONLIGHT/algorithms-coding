package _3.binaryTree._1;

/**
 * 在二叉搜索树中查找第K小的数
 * 二叉搜索树-左小右大，按照左中右遍历的话，为从小到大的顺序
 */
public class kthSmallest {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }
    private int count = 0;
    private int value = 0;
    public int kthSmallest(TreeNode root, int k){
        kthSmallestInOrder(root, k);
        return value;
    }
    private void kthSmallestInOrder(TreeNode root, int k){
        if(root == null) return;
        kthSmallestInOrder(root.left, k);
        count ++;
        if(k == count){
            value = root.val;
            return ;
        }
        kthSmallestInOrder(root.right, k);
    }
}
