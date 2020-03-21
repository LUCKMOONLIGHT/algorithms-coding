package _3.binaryTree._1;


import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 1.前序中左起第一位1肯定是根结点，我们可以据此找到中序中根结点的位置rootin；
 * 2.中序中根结点左边就是左子树结点，右边就是右子树结点，即[左子树结点，根结点，右子树结点]，我们就可以得出左子树结点个数为int left = rootin - leftin;；
 * 3.前序中结点分布应该是：[根结点，左子树结点，右子树结点]；
 * 4.根据前一步确定的左子树个数，可以确定前序中左子树结点和右子树结点的范围；
 * 5.如果我们要前序遍历生成二叉树的话，下一层递归应该是：
 * 6.左子树：root->left = pre_order(前序左子树范围，中序左子树范围，前序序列，中序序列);；
 * 7.右子树：root->right = pre_order(前序右子树范围，中序右子树范围，前序序列，中序序列);。
 * 8.每一层递归都要返回当前根结点root；
 *
 *
 * 找中序遍历的根节点的长度
 */
public class buildTreeDFS {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val = x;}
    }

    private Map<Integer, Integer> hashmap = new HashMap<>();

//    public TreeNode buildTree(int[] preOrder, int[] inOrder){
//        if(preOrder == null || inOrder == null || preOrder.length != inOrder.length) return null;
//        for (int i=0;i<inOrder.length;i++){
//            hashmap.put(inOrder[i], i);//中序的值及其每个值对应的位置
//        }
//        return buildDFSTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
//    }
//
//    private TreeNode buildDFSTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inPre, int inEnd){
//        if(preStart > preEnd || inPre > inEnd) return null;
//        int mid = hashmap.get(preOrder[preStart]);//通过hashmap来查找根结点对用的位置，省去了查找的时间
//        if (mid < 0) return null;
//        TreeNode root = new TreeNode(preOrder[preStart]);
//        root.left = buildDFSTree(preOrder, preStart + 1, preStart + (mid - inPre), inOrder, inPre, mid - 1);
//        root.right = buildDFSTree(preOrder, preStart + (mid - inPre) + 1, preEnd, inOrder, mid + 1, inEnd);
//        return root;
//    }


    public TreeNode buildTreeI(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) return null;
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, int[] inorder, int preStart, int preEnd, int inPre, int inEnd){
        if(preStart > preEnd || inPre > inEnd) return null;
        TreeNode cur = new TreeNode(preorder[preStart]);//根据前序遍历创建根节点
        if(preStart == preEnd) return cur;
        int index = inPre; //从中序遍历开始迭代搜索，查找根节点，确定左子树mid-1，(mid - inPre) - 1和右子树的长度
        while(inorder[index] != preorder[preStart]) index++; //在中序遍历中查找根节点，以确定左子树和右子树的长度
        cur.left = dfs(preorder, inorder, preStart+1, preStart + (index - inPre), inPre ,index-1);
        cur.right = dfs(preorder, inorder, preStart + (index - inPre)+1, preEnd, index+1, inEnd);
        return cur;
    }
}
