package _3.binaryTree._1;

import java.util.HashMap;
import java.util.Map;

/**
 * 前序后序遍历构造二叉树
 * class Solution(object):
 *     def constructFromPrePost(self, pre, post):
 *         """
 *         :type pre: List[int]
 *         :type post: List[int]
 *         :rtype: TreeNode
 *         """
 *         if not pre:
 *             return None
 *         root = TreeNode(pre[0])
 *         if len(pre) == 1:
 *             return root
 *         n = post.index(pre[1])
 *         root.left = self.constructFromPrePost(pre[1:n+2], post[:n+1])
 *         root.right = self.constructFromPrePost(pre[n+2:], post[n+1:-1])
 *         return root
 */
public class buildTreeDFSIII {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return helper(pre,post,0,pre.length-1,0,post.length-1);
    }
    public TreeNode helper(int[] pre,int[] post,int prestart,int preend,int poststart,int postend){
        if(prestart>preend||poststart>postend)return null;
        TreeNode root=new TreeNode(pre[prestart]);
        if (prestart == preend)
            return root;
        int index=0;
        while(post[index]!=pre[prestart+1]){
            index++;
        }
        root.left=helper(pre,post,prestart+1,prestart+1+index-poststart,poststart,index);
        root.right=helper(pre,post,prestart+2+index-poststart,preend,index+1,preend-1);
        return root;

    }
}
