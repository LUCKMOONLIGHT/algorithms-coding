package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和 - dfs 回溯
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的数字可以无限制重复被选取。
 * 0.candidates 中的数字可以无限制重复被选取    level
 * 1.所有数字（包括 target）都是正整数
 * 2.解集不能包含重复的组合
 * candidates = [2,3,6,7], target = 7
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 *
 *
 *
 *
 * 1.根据输入进行排列 或者 组合，不能有重复元素
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
 *
 *  解题思路：
 *  1.创建List<List<Integer>> res 全局变量作为返回
 *  2.List<Integer> list 辅助函数作为临时存储
 *  3.边界值判断：if (candidates == null || candidates.length == 0 || target < 0)
 *  4.递归 process(0, candidates, target, list);
 *  5.边界值判断 target <0 return target == 0 添加到res中 res.add(new ArrayList<>(list))
 *  6.一个循环来进行遍历,start变量控制不重复选择
 *  7.选择一个数加入到list中，递归调用process，控制start变量 = i，target变量 = target - i
 *
 *
 *
 */
public class combinationSum {
    public List<List<Integer>> getcombinationSum(int[] nums, int target){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        dfs(res, new ArrayList<>(),0,nums,target);
        return res;
    }
    /**
     * @param res 收集结果的result
     * @param list   每一轮for loop的list
     * @param index   下一个数的位置，可以取重复值
     * @param nums      候选数
     * @param target  目标大小
     */
    public void dfs(List<List<Integer>> res, List<Integer> list, int index, int[] nums, int target){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i=index;i<nums.length;i++){
            list.add(nums[i]);
            dfs(res, list, i, nums, target - nums[i]);//要求元素可重复，但是结果不可重复，故重复元素为当前元素，将当前元素index，传入dfs即可
            list.remove(list.size() - 1);
        }
    }
}
