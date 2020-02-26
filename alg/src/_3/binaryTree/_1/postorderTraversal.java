package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历 Hard
 * 0.stack栈中为中左右 出栈为中右左
 * 1.每次插入到list.add(0)首部，顺序为左右中
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

    //面试题33. 二叉搜索树的后序遍历序列
    //参考以下这颗二叉搜索树：
    //     5
    //    / \
    //   2   6
    //  / \
    // 1   3
    //输入: [1,6,3,2,5]  [1,3,2,6,5]
    //输出: false        true
    public boolean verifyPostorder(int[] postorder) {  //左<右>中
        // 单调栈使用，单调递增的单调栈
        Stack<Integer> stack = new Stack<>();
        int pervElem = Integer.MAX_VALUE;
        // 逆向遍历，就是翻转的先序遍历
        for (int i = postorder.length - 1;i>=0;i--){
            // 左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
            if (postorder[i] > pervElem){
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()){
                // 数组元素小于单调栈的元素了，表示往左子树走了，记录下上个根节点
                // 找到这个左子树对应的根节点，之前右子树全部弹出，不再记录，因为不可能在往根节点的右子树走了
                pervElem = stack.pop();
            }
            // 这个新元素入栈
            stack.push(postorder[i]);
        }
        return true;
    }



    //面试题33. 二叉搜索树的后序遍历序列
    //1.如果长度为2，true
    //2.找到右子树的第一个节点的位子
    //3.验证右子树节点都大于根节点
    //4.dfs递归判断左右子树
    public boolean verifyPostorderII(int [] postorder) {
        if (postorder.length <= 2) return true;  //只有2个节点，必定为true
        return verifySeq(postorder, 0, postorder.length-1);
    }
    private boolean verifySeq(int[] postorder, int start, int end) {
        if (start >= end) return true;//如果迭代完成无误，返回true
        int i;
        for (i = start; i < end; i++) { //找到右子树的起始节点
            if (postorder[i] > postorder[end]) break;
        }
        // 验证后面的是否都大于sequence[end]
        for (int j = i; j < end; j++) { //右子树的节点都要大于根节点
            if (postorder[j] < postorder[end]) return false;
        }
        return verifySeq(postorder, start, i-1) && verifySeq(postorder, i, end-1); //继续校验左右子树
    }
}
