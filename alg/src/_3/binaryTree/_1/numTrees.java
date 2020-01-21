package _3.binaryTree._1;

//96. 不同的二叉搜索树
//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

public class numTrees {
    /**
     * 1.每个节点都可以作为根节点，其左右节点都可以左右子树，即新的二叉树，很多多个子问题，可以用动态规划 G(n) = ∑G(n-i) * G(i-1) n-i  i-1 表示长度
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];

        dp[0]=1;
        dp[1]=1;

        for(int i=2;i<n+1;i++){ //输入整数n
            for(int j=1;j<i+1;j++){//以j作为二叉搜索树的根节点
                dp[i] += dp[j-1]*dp[i-j];//左右子树个数的笛卡尔积累加
            }
        }
        return dp[n];
    }
}
