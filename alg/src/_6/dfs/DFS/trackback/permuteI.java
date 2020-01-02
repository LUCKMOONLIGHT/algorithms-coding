package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列I - 回溯（深度优先遍历 + 状态重置）   给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 1.先从大到小进行排序
 * 2.从头开始一次标记mark，然后加入到list中，进行dfs
 * 3.当list长度等于要求长度时，添加到res中result.add(new ArrayList<>(levelList))，返回return
 *
 * 1.从叶子结点到根节点形成的一条路径，就是题目要求的一个排列
 * 2.在更深层可选的数一定不能包含在之前的层选过的数，因此需要使用一个数组mark来记录哪些在之前的层选过
 * 3.在"回溯"的时候，状态要“重置”
 * 4.数组mark还可以使用位掩码替代
 */
public class permuteI {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        boolean[] visited = new boolean[len];
        helper(result, nums, new ArrayList<Integer>(), visited);
        return result;

    }

    private void helper(List<List<Integer>> result, int[] nums, ArrayList<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            list.add(nums[i]);
            helper(result, nums, list, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }


    }
}
