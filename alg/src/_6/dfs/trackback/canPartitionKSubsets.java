package _6.dfs.trackback;

/**
 * 698. 划分为k个相等的子集 - 回溯dfs
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 * 思路：
 * 1.求得每个子集得总和
 * 2.判断是否实际总是否能被k整除，并且最大值是否大于每个子集得总和
 * 3.dfs：nums,k,target,cur,start,mark
 *  - k == 0 凑齐了k个集合 return true
 *  - cur == target 拿到target值，（加入到res中）进一步dfs(nums,k-1,target,0,0,mark)
 *  - for 0 len(nums):
 *      - 去重复 !mark[i] && cur+nums[i] <= target
 *          - 标记为true
 *          - if（dfs（cur+nums[i],i+1）） true
 *          - 还原标记
 */
public class canPartitionKSubsets {
    private boolean backtracking(int[] nums, int k, int target, int cur, int start, boolean[] used) {
        // 返回条件
        if (k == 0) return true;
        if (cur == target) {
            // 构建下一个集合
            return backtracking(nums, k-1, target, 0, 0, used);
        }
        for (int i = start; i < nums.length; i++) {
            if (!used[i] && cur+nums[i] <= target) {
                used[i] = true;
                if (backtracking(nums, k, target, cur+nums[i], i+1, used)) return true;
                used[i] = false;
            }
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 注意nums[i] > 0
        int sum = 0, maxNum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (maxNum < nums[i]) maxNum = nums[i];
        }
        if (sum % k != 0 || maxNum > sum/k) return false;
        boolean[] used = new boolean[nums.length];
        return backtracking(nums, k, sum/k, 0, 0, used);
    }

}
