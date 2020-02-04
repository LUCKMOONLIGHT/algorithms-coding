package _3.binaryTree._1;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 */
public class buildTreeDFSII {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public TreeNode buildTree(int[] inOrder, int[] postOrder){
        if(inOrder == null || postOrder == null || inOrder.length != postOrder.length){
            return null;
        }
        return dfs(inOrder, postOrder, 0, inOrder.length - 1, 0, postOrder.length - 1);
    }

    private TreeNode dfs(int[] inOrder, int[] postOrder, int inPre, int inEnd, int postPre, int postEnd){
        if (inPre > inEnd || postPre > postEnd) return null;
        TreeNode root = new TreeNode(postOrder[postEnd]);//根据后续遍历创建根节点
        int mid = inPre;//从中序遍历开始迭代搜索，查找根节点，确定左子树mid-1，(mid - inPre) - 1和右子树的长度
        while(inOrder[mid] != postOrder[postEnd]) mid++;
        root.left = dfs(inOrder, postOrder, inPre, mid -1, postPre, postPre + (mid - inPre) - 1);
        root.right = dfs(inOrder, postOrder, mid + 1, inEnd, postPre + (mid - inPre), postEnd - 1);
        return root;
    }

}


