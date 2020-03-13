package _4.dp;

import java.util.List;

//120. 三角形最小路径和
//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
//
//  [2],
//  [3,4],
//  [6,5,7],
//  [4,1,8,3]
//  状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
//  dp[0][0]=triangle[0][0]
//
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
public class minimumTotal {
    /**
     * 解法1 二维数组进行求解
     * @param triangle
     * @return
     */
    public int minimumTotalI(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // 加1可以不用初始化最后一层
        int[][] dp = new int[triangle.size()+1][triangle.size()+1];
        //从底至上
        //状态转移：最小路径和为 下一层相邻节点的最小路径 + 当前路径
        for (int i = triangle.size()-1; i>=0; i--){  //行数
            List<Integer> curTr = triangle.get(i); //获取每一行的值
            for(int j = 0 ; j< curTr.size(); j++){  //列数  //类似于求最小路径和，从左下结点开始二层循环
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + curTr.get(j); //当前结点最大路径为当前路径值+连接两个结点的路径值
            }
        }
        return dp[0][0];
    }
    /**
     * 解法2 一维数组进行求解
     * @param triangle
     * @return
     */
    public int minimumTotalII(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // 只需要记录每一层的最小值即可
        //dp[j] 长度为j的最小路径和
        int[] dp = new int[triangle.size()+1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> curTr = triangle.get(i);
            for (int j = 0; j < curTr.size(); j++) {
                //这里的dp[j] 使用的时候默认是上一层的，赋值之后变成当前层
                //dp[j] 长度为j的最小路径和 = 下一层相邻的最小路径 + 当前路径
                dp[j] = Math.min(dp[j],dp[j+1]) + curTr.get(j);
            }
        }
        return dp[0];
    }
}
