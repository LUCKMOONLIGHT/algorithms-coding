package _6.dfs.trackback;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集 - 回溯
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *思路： 回溯
 * 1.解集合不包括重复的元素 dfs(0) dfs(i+1)
 * 2.子集 for res.add(new ArrayList<>(list))
 */
public class subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        if(nums == null || nums.length == 0) return res;
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int index){
        for(int i=index;i<nums.length;i++){
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            dfs(res,list,nums, i+1);
            list.remove(list.size() - 1);
        }
    }
}
