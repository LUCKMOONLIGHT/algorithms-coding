package _3.binaryTree._1;
//114. 二叉树展开为链表 - 中等
//给定一个二叉树，原地将它展开为链表。实际上还是TreeNode，全部转化为右子树上
// 1.将左子树插入到右子树的地方  2.将原来的右子树接到左子树的最右边节点  3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
public class flatten {
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){
                root = root.right;
            }else{ //迭代右子树，当前左子树不为null时
                TreeNode pre = root.left;
                while(pre.right != null) pre = pre.right;
                pre.right = root.right; //先把右子树连接到左子树的最右子树
                root.right = root.left;//把左子树丢到右子树上
                root.left = null;//左子树为空
                root = root.right;//继续迭代右子树
            }
        }

    }
}
