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

    private Map<Integer, Integer> hashmap = new HashMap<>();

    public TreeNode buildTree(int[] inOrder, int[] postOrder){
        if(inOrder == null || postOrder == null || inOrder.length != postOrder.length){
            return null;
        }
        for (int i=0;i<inOrder.length;i++){
            hashmap.put(inOrder[i], i);
        }
        return buildDFSIITree(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1);
    }

    private TreeNode buildDFSIITree(int[] inOrder, int inPre, int inEnd, int[] postOrder, int postPre, int postEnd){
        if (inPre > inEnd || postPre > postEnd) return null;
        TreeNode root = new TreeNode(postOrder[postEnd]);
        int mid = hashmap.get(postOrder[postEnd]);//找根节点的位置
        if (mid < 0 ) return null;
        root.left = buildDFSIITree(inOrder, inPre, mid -1, postOrder, postPre, postPre + (mid - inPre));
        root.right = buildDFSIITree(inOrder, mid + 1, inEnd, postOrder, postPre + (mid - inPre) + 1, postEnd - 1);
        return root;
    }
}


