package _3.binaryTree._1;

import java.util.Stack;

//98. 验证二叉搜索树
//使用中序遍历判断大小顺序
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class isValidBST {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {//栈不为null，出栈
            while (root != null) {//当前结点不为null，入栈
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    //面试题54. 二叉搜索树的第k大节点
    //思路：右中左遍历，为递减顺序，计数器每次减一
    public int kthLargest(TreeNode root, int k) {
        //中序遍历为从小到大顺序
        Stack<TreeNode> stack = new Stack();
        while (!stack.isEmpty() || root != null) {//栈不为null，出栈
            while (root != null) {//当前结点不为null，入栈
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            k--;
            if (k==0) return root.val;
            root = root.left;
        }
        return -1;
    }
}
