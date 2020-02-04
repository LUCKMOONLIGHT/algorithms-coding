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
 *
 * 先序和后序是不能构建唯一的一颗二叉树的。 例如： 先序为：[1, 2] 后序为：[2, 1]
 */
public class buildTreeDFSIII {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return dfs(pre,post,0,pre.length-1,0,post.length-1);
    }
    public TreeNode dfs(int[] pre, int[] post, int prestart, int preend, int poststart, int postend){
        if(prestart>preend||poststart>postend) return null;//只要有一个不满足条件返回null
        TreeNode root=new TreeNode(pre[prestart]);//根据前序遍历确定根节点
        if (prestart == preend) return root;
        int index=poststart;
        //从后序遍历开始迭代搜索，查找左节点，确定左子树mid，(mid - inPre)和右子树的长度
        while(post[index]!=pre[prestart + 1]){
            index++;
        }
        root.left = dfs(pre,post,prestart+1,prestart+(index-poststart)+1, poststart, index);
        root.right = dfs(pre,post,prestart+(index-poststart)+2, preend, index+1,preend-1);
        return root;
    }
}
