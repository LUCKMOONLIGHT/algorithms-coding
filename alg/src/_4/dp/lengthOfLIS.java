package _4.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 300. 最长上升子序列 - 序列不要求连续   nums[i] > nums[j]  dp[i] = Math.max(dp[i],dp[j]+1)
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 思路：从顶至下 动态规划
 *
 * dp[i]表示以i索引结尾的最长上升子序列的长度
 * 1.初始状态Arrays.fill(dp,1)
 * 2.转移方程 dp[i] = Math.max(dp[i],dp[j]+1)
 */
public class lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len < 2) return len;
        int[] dp = new int[len];
        Arrays.fill(dp,1);
        int maxans = dp[0];
        for(int i=1;i<len;i++){ //总长度
            for(int j=0;j<i;j++){//子串长度
                if(nums[j] < nums[i]){//在每个可能的最长上升子序列中附加当前元素nums[i]
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxans = Math.max(maxans,dp[i]);
        }
        return maxans;
    }

    //674. 最长连续递增序列 子串
    //给定一个未经排序的整数数组，找到最长且连续的的递增序列。
    //[1,3,5,4,7]  3
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[2];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            dp[i % 2] = 1;
            if (nums[i] > nums[i - 1]) {
                dp[i % 2] += dp[(i - 1) % 2];
            }
            maxLen =Math.max(maxLen,dp[i%2]);
        }
        return maxLen;
    }

    /**
     * 最长连续子序列长度：一个连续的子序列,并且这个子序列还必须得满足:
     * 最多只改变一个数,就可以使得这个连续的子序列是一个严格上升的子序列
     *  5 （2，3，1，5，6）
     * 最多只改变一个数，即不考虑该数，求以每个数开头和以每个数结尾的子序列长度和
     *
     * 思路：
     * 1.正序遍历一遍以end[i]结束的子数组
     * 2.反序遍历一遍以start[i]开头的子数组
     * 3.正序遍历，当元素前后为递增时，将两个子序列相加再+1
     *
     *  分别求以下标i元素为起始点和结束点的递增子序列长度。
     *  比较下标i-1和i+1位置的元素差值是否大于2，如果大于2则拼接长度
     */
    public int lengthOfLISII(int[] nums) {
        int n = nums.length;
        int[] start = new int[n];
        int[] end = new int[n];
        end[0] = 1;
        for (int i = 1; i < n; i++) {//以end[i]结束的子序列，原数组后面加一个数
            end[i] = nums[i] > nums[i - 1] ? end[i - 1] + 1 : 1;
        }
        start[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {//以start[i]开始的子序列，原数组前面加一个数
            start[i] = nums[i] < nums[i + 1] ? start[i + 1] + 1 : 1;
        }
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            if ( nums[i + 1] - nums[i - 1] >= 2) {  //当前后元素大小相差大于1时，求组合两个子序列的最大值
                int sum = start[i + 1] + end[i - 1] + 1;  //以end[i - 1]结尾的子串长度加上start[i + 1]开头的子串长度 + 1 当前改变后的数字长度
                result = Math.max(result, sum);
            }
        }
        return result;
    }


    //任意删除一个正整数，剩余的最长上升子串构造长度
    //1.求以每个位置开始和结束能够组成的最长上升子串构造长度
    //2.判断每个位置的前后元素是否满足条件，满足将前后长度相加，否则为前后最大长度

    public int lengthOfLISIII(int[] nums) {
        int n = nums.length;
        int[] start = new int[n];
        int[] end = new int[n];
        end[0] = 1;
        for (int i = 1; i < n; i++) {//以end[i]结束的子序列，原数组后面加一个数
            end[i] = nums[i] > nums[i - 1] ? end[i - 1] + 1 : 1;
        }
        start[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {//以start[i]开始的子序列，原数组前面加一个数
            start[i] = nums[i] < nums[i + 1] ? start[i + 1] + 1 : 1;
        }
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            int sum = 0;
            if ( nums[i + 1] - nums[i - 1] >= 1) {  //当后面的元素大于前面的元素时，求组合两个子序列的最大值
                sum = start[i + 1] + end[i - 1] ;  //以end[i - 1]结尾的子串长度加上start[i + 1]开头的子串长度 (当前字符串删除)
            }else sum = Math.max(start[i + 1], end[i - 1]); //不满足条件，求前后最大的子串
            result = Math.max(result, sum);//保存当前最大的子串
        }
        return result;
    }

    /**
     * 673. 最长递增子序列的个数
     * @param nums
     * @return
     *
     * [1,3,5,4,7]   [1, 3, 4, 7] 和[1, 3, 5, 7]   2
     * [2,2,2,2,2]   5
     *
     *
     * 假设对于以 nums[j] 结尾的序列，我们知道最长序列的长度 length[j]，以及具有该长度的序列的 count[j]。
     * 对于每一个新状态i>j,如果 nums[i] > nums[j]，我们可以将一个 nums[i] 附加到以 nums[j] 结尾的最长子序列上，否则没必要更新了。
     * 如果新状态最长子序列的长度没有更长，那么我们更新他为之前状态最长的，新状态最长子序列的个数就是之前长度最长的状态的子序列的个数。
     * 如果新状态最长子序列的长度比旧状态长度大1（只能是大1，否则就是无关状态了，因为只多了一个数），
     * 此时记录的长度已经是最长，就不用更新，只需要更新新状态的count，即所欲能转移到当前状态的count的和，才是总次数，
     * 累计是因为结尾数可能不同，看上面例子e.g.
     */
    public int findNumberOfLIS(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n]; //最长子序列的长度
        int[] combination = new int[n];//最长子序列的个数
        Arrays.fill(dp ,1);
        Arrays.fill(combination, 1);
        int max = 1, res = 0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i]){//状态转移
                    if(dp[j] + 1 > dp[i]){//说明第一次找到以nums[i]为结尾的最长递增子序列
                        dp[i] = dp[j] + 1;
                        combination[i] = combination[j];
                    }else if(dp[i] == dp[j] + 1){//说明这个长度已经找到过一次了
                        combination[i] += combination[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        for(int i=0;i<n;i++){
            if(max == dp[i]) res += combination[i];
        }
        return res;
    }

    /**
     * 128. 最长连续子序列 - difficult
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     *  [100, 4, 200, 1, 3, 2] 4
     *  [1, 2, 3, 4]
     *  1.先排序  2.再计算最长连续子序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int n=nums.length;
        if(nums == null || n == 0) return 0;
        Arrays.sort(nums);
        int curlen = 1;
        int maxlen = 1;
        for(int i=1;i<n;i++){
            if(nums[i] != nums[i-1]){
                if(nums[i] == nums[i-1] + 1){
                    curlen++;
                }else{
                    maxlen = Math.max(maxlen, curlen);
                    curlen = 1;
                }
            }
        }
        return Math.max(maxlen, curlen);
    }

    //哈希表和线性空间的构造  O(n) O(n)

    /**
     * 最大连续递增数列  无序-递增子序列
     * 1.将数字放入set集合中
     * 2.遍历数组，从不连续的数组开始，while计算连续的
     * @param nums
     * @return
     */
    public int longestConsecutiveI(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);//把每个元素添加到set中
        int longestCnt = 0;
        for (int num : nums) {//迭代数组
            if (!set.contains(num - 1)) { //判断是否包含前一个，包含前一个说明以及计算进去了，不重复计算，每次从最新的开始计算
                int curNum = num; //如果不包含前一个，从当前计数
                int curCnt = 1;
                while (set.contains(curNum + 1)) { //如果包含下一个数组
                    curNum++;//当前计数+1
                    curCnt++;
                }
                longestCnt = Math.max(longestCnt,curCnt);//保存最大值
            }
        }
        return longestCnt;
    }



    public static void main(String[] args) {
        lengthOfLIS lengthOfLIS = new lengthOfLIS();
        int res = lengthOfLIS.findNumberOfLIS(new int[]{1,3,5,4,7});
        System.out.println(res);
    }
}
