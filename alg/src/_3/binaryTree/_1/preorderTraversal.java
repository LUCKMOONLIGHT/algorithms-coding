package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class preorderTraversal {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    /**
     * 非递归方式前序遍历
     * 1.把根节点push到栈中
     * 2.循环检测栈是否为空，若不空，则取出栈顶元素，保存其值
     * 3.看其右子节点是否存在，若存在则push到栈中
     * 4.看其左子节点，若存在，则push到栈中。
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
            while(!stack.isEmpty()){
                TreeNode tmp = stack.pop();
                list.add(tmp.val);
                if (tmp.right != null){ //先右节点,再左节点，出栈的时候先左再右
                    stack.push(tmp.right);
                }
                if(tmp.left != null){
                    stack.push(tmp.left);
                }

            }
        }
        return list;
    }
}
