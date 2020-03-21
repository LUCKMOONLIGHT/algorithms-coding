package _3.binaryTree._1;

//543. 二叉树的直径 - 简单 最长路径
//类似于最大路径和
//这里是求边的个数
//思路：求左右子树的最大深度，最大值为左右子树最大深度+1，返回
//一条路径的长度为该路径经过的节点数减一，
//所以求直径（即求路径长度的最大值）等效于求路径经过节点数的最大值减一
//该节点为根的子树的深度max(L,R)+1
//该节点的dnode值为L+R+1
public class diameterOfBinaryTree {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;//默认结点数为1
        depth(root); // 迭代树
        return ans - 1; //最长路径长度 = 最长路径的点数 - 1
    }
    //求最长路径的点数
    public int depth(TreeNode node) {
        if (node == null) return 0; // 访问到空节点了，返回0
        int L = depth(node.left);  //左子树的深度
        int R = depth(node.right);  //右子树的深度
        ans = Math.max(ans, L + R + 1);  //计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1;  //返回该节点为根的子树的深度
    }

    //687. 最长同值路径  左右中
    //给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
    //注意：两个节点之间的路径长度由它们之间的边数表示。
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }
    public int dfs(TreeNode node){
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int arrowLeft = 0, arrowRight = 0; //left和right有可能会改变，使用新的地址保存值
        if (node.left != null && node.left.val == node.val) {  //要求同值
            arrowLeft = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {//要求同值
            arrowRight = right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);  //全局最长
        return Math.max(arrowLeft, arrowRight); //当前节点最长
    }

    //124.二叉树的最大路径和   左右中
    int ret = 0;
    private int getMax(TreeNode r) {
        if(r == null) return 0;
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        ret = Math.max(ret, r.val + left + right); // 全局的最大路径和
        return Math.max(left, right) + r.val; //当前节点的最大路径和
    }

}
