package _3.binaryTree._1;


//108. 将有序数组转换为二叉搜索树 - dfs递归
public class sortedArrayToBST {
    //找到中间节点，一分为二，进入递归
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length);
    }

    public TreeNode dfs(int[] nums, int i, int j){
        if(i == j) return null;
        int mid = (i+j) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);  //找到中间节点，将中间节点作为根节点
        root.left = dfs(nums, i, mid);//dfs迭代左子树
        root.right = dfs(nums, mid+1,j);//dfs迭代右子树
        return root;//返回根节点
    }
}
