package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 二叉树的后序遍历 Hard
 * 0.后续遍历为左右中，而先序遍历为中左右
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

    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root != null){
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(root);
            while(!s1.isEmpty()){
                root = s1.pop();
                s2.push(root);

                if(root.right != null){
                    s1.push(root.right);
                }
                if (root.left != null){
                    s1.push(root.left);
                }

            }
            while(!s2.isEmpty()){
                res.add(s2.pop().val);
            }
        }
        return res;
    }
}
