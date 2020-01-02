package _3.binaryTree._1;

import java.util.HashMap;
import java.util.Map;

/**
 * 中序后序遍历构造二叉树
 * class Solution(object):
 *     def buildTree(self, inorder, postorder):
 *         """
 *         :type inorder: List[int]
 *         :type postorder: List[int]
 *         :rtype: TreeNode
 *         """
 *         if not postorder:
 *             return None
 *         root = TreeNode(postorder[-1])#创建树
 *         n = inorder.index(root.val)
 *         root.left = self.buildTree(inorder[:n],postorder[:n])
 *         root.right = self.buildTree(inorder[n+1:],postorder[n:-1])
 *         return root
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
        int mid = hashmap.get(postOrder[postEnd]);
        if (mid < 0 ) return null;
        root.left = buildDFSIITree(inOrder, inPre, mid -1, postOrder, postPre, postPre + (mid - inPre));
        root.right = buildDFSIITree(inOrder, mid + 1, inEnd, postOrder, postPre + (mid - inPre) + 1, postEnd - 1);
        return root;
    }
}


