package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历的顺序为左-根-右
 * 1.从根节点开始，先将根节点压入栈
 * 2.然后再将其所有左子结点压入栈，取出栈顶节点，保存节点值
 * 3.再将当前指针移到其右子节点上，若存在右子节点,则在下次循环时又可将其所有左子结点压入栈中
 */
public class inorderTraversal {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root != null){

            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()){
                if (root != null){
                    stack.push(root);
                    root = root.left;
                }else {
                    root = stack.pop();
                    res.add(root.val);
                    root = root.right;
                }
            }
        }
        return res;
    }
}
