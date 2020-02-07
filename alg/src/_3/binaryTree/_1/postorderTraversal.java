package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历 Hard
 * 0.后序遍历为左右中，而先序遍历为中左右
 * 1.先用s1做出中右左的结构
 * 2.再用s2做出左右中
 */
public class postorderTraversal {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<TreeNode>(); //先将左子树入栈后将右子树入栈，先将右子树出栈插入到首部，后将左子树出栈插入到首部
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);//和传统先序遍历不一样，先将左结点入栈
            if (node.right != null) stack.push(node.right);//后将右结点入栈
            res.add(0, node.val);                        //逆序添加结点值，插入到首部位置
        }
        return res;
    }

    //递归写法   左右中
    public List<Integer> postorderTraversalIII(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
            if(root == null)
            return res;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        res.add(root.val);
        return res;
    }
}
