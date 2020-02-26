package _3.binaryTree._1;

/**
 * 572. 另一个树的子树
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

    //[10,12,6,8,3,11]
    //[10,12,6,8]  true
    public boolean isSubStructure(TreeNode A, TreeNode B) {  //先序遍历判断字数结构，中左右
        if(A == null || B == null) return false;  //如果有一棵树为null，不成立false
        return isSame(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B); //一个条件成立即可
    }

    public boolean isSame(TreeNode A, TreeNode B){
        if(B == null) return true; //如果B子树迭代完成，true
        if(A == null) return false;//如果B子树未迭代完成，A迭代完成，false
        return A.val == B.val && isSame(A.left, B.left) && isSame(A.right, B.right);//子树相等同时成立
    }
}
