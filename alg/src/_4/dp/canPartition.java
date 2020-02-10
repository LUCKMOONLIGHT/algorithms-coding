package _4.dp;

/**
 * 416. 分割等和子集 - 动态规划
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *  [1, 5, 11, 5]  true
 *  [1, 5, 5] 和 [11]
 *
 *  动态规划的一般思考路径
 *  1、状态定义；
 *
 * 2、状态转移方程；
 *
 * 3、初始化；
 *
 * 4、输出；
 *
 * 5、思考状态压缩。
 *
 * 如果总和为sum，当在区间中找到一部分元素为target时，剩余的部分元素和必定也为sum
 * 等价转换：是否可以从这个数组中挑选出一些正整数，使得这些数的和等于整个数组元素的和的一半
 * 前提条件：数组的和一定得是偶数
 *
 */
public class canPartition {
    /**
     * @param {number[]} nums
     * @return {boolean}
     * 背包问题：看数组中是否存在加起来为sum/2的数.  类似于0/1背包
     * 背包容量（V） = sum/2
     * 每一个物品只能装一次,如果出现背包中重量等于sum/2则为true else false
     * dp[i]表示能否填满容量为i的背包
     * 状态转移方程为 dp[i] = dp[i-1] || nums[i]+dp[i-nums[j]]
     * 举例:v = sum/2 = 11   nums = [1,5,11,5]  1是true 0 是false
     *          0  1  2  3  4  5  6  7  8  9  10  11
     *  nums[0] 0  1  0  0  0  0  0  0  0  0   0   0
     *  nums[1] 0  1  0  0  0  1  1  0  0  0   0   0
     *  nums[2] 0  1  0  0  0  1  1  0  0  0   0   1
     *  nums[3] 0  1  0  0  0  1  1  0  0  0   0   1
     *
     * 所以返回true，因为背包中nums[i]的状态都是由上一行决定的因此可以将二维转化为1维数组从尾部开始逆序迭代
     */
    public boolean canPartition(int[] nums) {
        int N = nums.length; //元素个数
        int sum = 0;
        for(int num:nums) sum += num; //元素的总和
        if((sum & 1) == 1) return false; //如果是奇数的话，不满足条件直接返回
        int target = sum / 2; //如果是偶数的话，对目标值进行dp

        boolean[] dp = new boolean[target+1];
        dp[0] = true;//初始条件
        for(int i=1;i<N;i++){ //迭代可能的取值
            for(int j = target;j>=0 && j >= nums[i];j--){  //逆序操作0/1选择
                dp[j] = dp[j] || dp[j - nums[i]];
                //dp[j]不取nums[i]：已经有一部分元素，使得和为j
                //dp[j - nums[i]]取nums[i]，这个子区间内就得找到一部分元素，使得它们的和为 j - nums[i]
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
