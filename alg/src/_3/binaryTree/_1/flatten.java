package _3.binaryTree._1;
//114. 二叉树展开为链表
//给定一个二叉树，原地将它展开为链表。
// 1.将左子树插入到右子树的地方  2.将原来的右子树接到左子树的最右边节点  3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
public class flatten {
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){
                root = root.right;
            }else{
                TreeNode pre = root.left;
                while(pre.right != null) pre = pre.right;
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }

    }
}
