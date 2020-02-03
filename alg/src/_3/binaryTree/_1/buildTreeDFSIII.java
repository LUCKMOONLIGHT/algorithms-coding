package _3.binaryTree._1;

import java.util.HashMap;
import java.util.Map;

/**
 * 889. 根据前序和后序遍历构造二叉树
 *
 * 思路：
 * 1.判断start与end，不满足条件返回null
 * 2.创建根节点，如果start == end 返回root，寻找后序遍历数组的左子树长度
 * 3.dfs寻找cur的left和right结点
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
        if(prestart>preend||poststart>postend) return null;//只要有一个不满足条件返回null
        TreeNode root=new TreeNode(pre[prestart]);//确定根节点
        if (prestart == preend)
            return root;
        int index=0;
        //在后序遍历数组中寻找左子树的长度，左子树的长度应该等于index-poststart，位置为[poststart,index]
        while(post[index]!=pre[prestart+1]){
            index++;
        }
        root.left=helper(pre,post,prestart+1,prestart+1+index-poststart,poststart,index);//前后结点中左子树的长度
        root.right=helper(pre,post,prestart+2+index-poststart,preend,index+1,preend-1);//前后结点中右子树的长度
        return root;
    }
}
