package _3.binaryTree._1;

//543. 二叉树的直径 - 简单 最长路径
//类似于最大路径和
//这里是求边的个数
public class diameterOfBinaryTree {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L+R+1);  //全局节点的直径
        return Math.max(L, R) + 1;  //当前节点的直径
    }

    //687. 最长同值路径
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
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {  //要求同值
            arrowLeft = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {//要求同值
            arrowRight = right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);  //全局最长
        return Math.max(arrowLeft, arrowRight); //当前节点最长
    }
}
