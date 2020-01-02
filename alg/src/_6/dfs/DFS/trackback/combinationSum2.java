package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和2 - dfs 回溯
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的数字可以无限制重复被选取。
 * 0.candidates 中的数字只能被取一次    level
 * 1.所有数字（包括 target）都是正整数
 * 2.解集不能包含重复的组合    排序+candidate[i]==candidate[i-1] continue
 *
 *
 *
 * 1.根据输入进行排列 或者 组合，不能有重复元素,不能有重复组合
 *  - 排列 取全部 去重复
 *  - Arrays.sort()
 *  - boolean mark[]
 *  - if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) continue
 *  - for 0-n
 *  - mark[true]
 *
 *  - 组合 取部分 去重复 控制start变量
 *  或者控制for index
 *  -  for (int i=level;i<=n;i++)
 *  - dfs(res, list, n, k, i+1)
 *
 * 1.5 可以取重复值
 *    - dfs(res, new ArrayList<>(),0,nums,target)
 *    - for (int i=index;i<nums.length;i++)
 *    - dfs(res, list, i, nums, target - nums[i]
 *
 *
 *  2.获取输入中的数
 *   - levelList.add(nums[i]);
 *
 *  3.排列 或者 组合 达到指定长度
 *    - if(levelList.size() == nums.length) result.add(new ArrayList<>(levelList))
 *    - levelList.add(nums[i])
 *
 *  4.组合和  动态改变target
 *    - dfs(res, new ArrayList<>(),0,nums,target);
 *    - dfs(res, list, i, nums, target - nums[i]
 *
 */
public class combinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        if(candidates == null || candidates.length == 0 || target == 0) return res;
        dfs(res, candidates, new ArrayList<>(), target, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, int[] candidates, List<Integer> list, int target, int level){
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=level;i<candidates.length;i++){
            if(i != level && candidates[i] == candidates[i - 1]) continue;
            if(target < candidates[i]) continue;
            list.add(candidates[i]);
            dfs(res, candidates, list, target - candidates[i], i+1);
            list.remove(list.size() - 1);
        }
    }
}
