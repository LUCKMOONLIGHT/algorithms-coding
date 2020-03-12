package _11.MonotonousStack;

import java.util.Stack;

/**
 * 907. 子数组的最小值之和 - 单调栈
 *  给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
 *  由于答案可能很大，因此返回答案模 10^9 + 7。
 *  输入：[3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 *
 * 单调栈：
 * 1.单调递增栈就是一个保持栈内元素为单调递增的栈。
 * 典型范式
 * for(int i = 0; i < A.size(); i++){
 *   while(! in_stk.empty() && in_stk.top() > A[i]){
 *       in_stk.pop();
 *   }
 *   in_stk.push(A[i]);
 * }
 *
 * 作用
 * 以O(n)的计算复杂度找到vector中每一个元素的前下界(previous less element)
 * for(int i = 0; i < A.size(); ++i){
 *     while(!in_stk.empty() && A[in_stk.top()] > A[i]){
 *         in_stk.pop();
 *     }
 *     PLE[i] = in_stk.empty()? -1 : in_stk.top();
 *     in_stk.push(i);
 * }
 *
 * 以O(n)的计算复杂度找到vector中每一个元素的后下界(next less element)
 * 一个元素的后下界是一个元素右边的值中找到一个infimum
 * for(int i = 0; i < A.size(); ++i){
 *     while(!in_stk.empty() && A[in_stk.top()] > A[i]){
 *         auto x = in_stk.top();
 *         in_stk.pop();
 *         NLE[x] = i;
 *     }
 *     in_stk.push(i);
 *
 *
 *    https://leetcode-cn.com/problems/sum-of-subarray-minimums/solution/zi-shu-zu-de-zui-xiao-zhi-zhi-he-by-leetcode/  没看懂
 */
public class sumSubarrayMins {
    /**维护最小值单调栈
     * 时间复杂度：O(N)O(N)，其中 NN 是 A 的长度。
     * 空间复杂度：O(N)O(N)。
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;

        Stack<RepInteger> stack = new Stack();
        int ans = 0, dot = 0;
        for (int j = 0; j < A.length; ++j) {
            // Add all answers for subarrays [i, j], i <= j
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= A[j]) {
                RepInteger node = stack.pop(); //遇到了一个比当前元素小的下一个元素
                count += node.count;//子序列的长度
                dot -= node.val * node.count;//子序列的值
            }
            stack.push(new RepInteger(A[j], count)); //维护一个单调递增栈
            dot += A[j] * count;//子数组的最小值
            ans += dot;
            ans %= MOD;
        }

        return ans;
    }
}

class RepInteger {
    int val, count;
    RepInteger(int v, int c) {
        val = v;
        count = c;
    }

    public static void main(String[] args) {
        sumSubarrayMins sumSubarrayMins = new sumSubarrayMins();
        int res = sumSubarrayMins.sumSubarrayMins(new int[]{3,1,2,4});
        System.out.println(res);
    }
}