package _6.dfs.trackback;

import java.util.ArrayList;
import java.util.List;

/**
 * 77.组合 - 回溯
 *  给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *  n = 4, k = 2
 *  [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 思路：经典回溯模板
 * 1.判断条件：当len == k时们加入
 * 2.for 控制len的值数量；dfs回溯时的传参 res, list,n,k,i+1
 */
public class combine {
    public List<List<Integer>> combine(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        if(n <=0 || k <= 0) return res;
        List<Integer> list = new ArrayList<>();
        dfs(res, list, n, k, 1);
        return res;
    }
    /**
     * @param res 收集结果的result
     * @param list   每一轮for loop的list
     * @param n      最大值n
     * @param k      k个数
     * @param level  下一个数 ，比如当前从2 开始，当做完2 后，加上做3
     */
    public void dfs(List<List<Integer>> res, List<Integer> list, int n, int k, int level){
        if(list.size() == k) {//控制要求的个数
            res.add(new ArrayList<>(list));
            return;
        }
        //i-1 +(k-list.size()) <= n  当从中间开始遍历，剩下的数，不足以组成k个数时，进行剪枝
        for (int i=level;i<=n;i++){  //控制候选数
            list.add(i);
            dfs(res, list, n, k, i+1);
            list.remove(list.size() - 1);
        }
    }
}
