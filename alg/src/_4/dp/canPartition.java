package _4.dp;

/**
 * 416. 分割等和子集 - 动态规划
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *  [1, 5, 11, 5]  true
 *  [1, 5, 5] 和 [11]
 */
public class canPartition {
    public boolean canPartition(int[] nums) {
        int size = nums.length;

        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if ((s & 1) == 1) {//如果总和为奇数则，不满足条件直接返回false
            return false;
        }

        int target = s / 2;

        boolean[] dp = new boolean[target + 1];//背包容量大小，即目标
        // 先写第 1 行，看看第 1 个数是不是能够刚好填满容量为 target
        for (int j = 1; j < target + 1; j++) {//第一个物品能不能满足各种背包容量大小
            if (nums[0] == j) {
                dp[j] = true;
                // 如果等于，后面就不用做判断了，因为 j 会越来越大，肯定不等于 nums[0]
                break;
            }
        }
        // size为每一个物品
        for (int i = 1; i < size; i++) {
            // target为每一个背包容量大小，每一个目标，容量大小要大于物品大小才放得下
            for (int j = target; j >= 0 && j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];//取或者不取，当有一种情况满足时
            }
        }
        return dp[target];
    }
    //参考答案
    public boolean canPartitionII(int[] nums) {
        int N = nums.length;
        int sum = 0;
        for(int num:nums) sum += num;
        if((sum & 1) == 1) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int i=1;i<N;i++){
            for(int j = target;j>=0 && j >= nums[i];j--){
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        canPartition canPartition = new canPartition();
        boolean res = canPartition.canPartition(new int[]{1,5,11,5});
        System.out.println(res);
    }
}
