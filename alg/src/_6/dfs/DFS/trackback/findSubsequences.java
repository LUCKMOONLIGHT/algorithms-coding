package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//491. 递增子序列
//给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
//输入: [4, 6, 7, 7]
//输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
public class findSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 2) return res;
        dfs(nums, Integer.MIN_VALUE, 0, res, new ArrayList<>());
        return res;
    }
    public void dfs(int[] nums, int preNum, int start, List<List<Integer>> res, List<Integer> list){
        if(list.size() >= 2){
            res.add(new ArrayList<>(list));
        }
        Set<Integer> hashset = new HashSet<>();
        for(int i=start;i<nums.length;i++){
            if(hashset.contains(nums[i]) || nums[i] < preNum) continue;
            list.add(nums[i]);
            hashset.add(nums[i]);
            dfs(nums, nums[i], i+1, res, list);
            list.remove(list.size() -1);
        }
    }
}
