package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和 III - 回溯
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 思路：回溯
 * 1.指定个数k  判断条件k==0 dfs(k-1)
 * 2.相加之和为 n 判断条件 n==0 dfs(n-i)
 * 3.组合中只允许含有 1 - 9 的正整数 for 1-9
 * 4.每种组合中不存在重复的数字 dfs(i+1)
 * 5.边界条件 k == 0 && target == 0   target - i < 0
 *
 *
 * 1.限定侯选数字为1-9
 * 2.限定数字不重复
 * 3.限定组合数为k
 * 4.限定和为n
 *
 */
public class combinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 1, k, n);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int index, int k, int target){
        if(k == 0 && target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=index;i<10;i++){
            if(target - i < 0) return;
            list.add(i);
            dfs(res,list,i + 1,k - 1,target - i);
            list.remove(list.size() - 1);
        }
    }
}
