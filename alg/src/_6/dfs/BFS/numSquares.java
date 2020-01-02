package _6.dfs.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 279. 完全平方数 Medium BFS与DP均可
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 1.dp:
 * 1.初始状态
 * 2.从2开始迭代到n，每次去最优dp
 *
 * 2.BFS
 * 生成一个generateSquares 1-n 的平方数序列
 * 记下level，其实代表是进入的层数
 * 记下每一个remain的值，放进queue中，弹出queue的值进行，判断
 * 如果之前出现的remain，不需要再次加入到queue中重复计算，使用marked的布尔数组
 */
public class numSquares {
    public int getNumSquares1(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i=2;i<=n;i++){
            int tmp = Integer.MAX_VALUE;
            for (int j=1;j<i;j++){
                tmp = Math.min(tmp, dp[i - j*j]);
            }
            dp[i] = tmp + 1;
        }
        return dp[n];

    }

    public int getNumSquares2(int n){
        List<Integer> sequence = getSequence(n);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        Boolean[] mark = new Boolean[n];
        int level = 0;
        int size = queue.size();
        while(size -- > 0){
            int num = queue.poll();
            level ++;
            for (int seq:sequence){
                int res = num - seq;
                if(res < 0) break;
                else if(res == 0) return level;
                else {
                    if (mark[res]) continue;
                    else {
                        mark[res] = true;
                        queue.add(res);
                    }
                }
            }
        }
        return -1;
    }
    public List<Integer> getSequence(int n){
        List<Integer> seq = new ArrayList<>();
        int last = (int) Math.sqrt(n);
        for (int i=1; i<=last;i++){
            seq.add((int) Math.pow(i, 2));
        }
        return seq;
    }
}
