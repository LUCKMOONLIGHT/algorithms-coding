package _5.pointer;

import java.util.Stack;

/**
 * 接雨水 - 双指针 O(n) O(1)
 *
 * 装水的多少，当然根据木桶效应，我们只需要看左边最高的墙和右边最高的墙中较矮的一个就够了。
 */
public class trap {
    /**
     * 1.right_max[i]>left_max[i] 时，积水高度由小的left_max[i]决定
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height == null || height.length == 0) return 0;
        int n = height.length;
        int l = 0,r=n - 1;
        int res = 0;
        int l_m = height[l],r_m = height[r];
        while (l <= r){
            l_m = Math.max(l_m, height[l]);
            r_m = Math.max(r_m, height[r]);
            if (l_m < r_m) {
                res += l_m - height[l];
                l++;
            }else {
                res += r_m - height[r];
                r++;
            }
        }
        return res;
    }

    /**
     * 单调栈 - O(n) O(n)
     * 建立一个单调递减的栈，如果出现一个元素大的话，必然可以形成凹槽，此时只需要计算凹槽长度和边界的高度差即可。
     * 使用栈来存储条形块的索引下标。
     * 遍历数组：
     *      当栈非空且 height[current]>height[st.top()]
     *              意味着栈中元素可以被弹出。弹出栈顶元素 top。
     *              计算当前元素和栈顶元素的距离，准备进行填充操作
     *              distance=current−st.top()−1
     *              找出界定高度
     *              bounded_height=min(height[current],height[st.top()])−height[top]
     *              往答案中累加积水量ans+=distance×bounded_height
     * 将当前索引下标入栈
     * 将current 移动到下个位置
     *
     */

    public int trap2(int[] height) {
        int n = height.length;
        if (n < 3) return 0;

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i=0;i<n;i++){
            while (!stack.isEmpty() && height[stack.peek()] < height[i]){
                int tmp = stack.pop();
                if(!stack.isEmpty()){
                    int distance = i - stack.peek() - 1;
                    int bounded_height = Math.min(height[i], height[stack.peek()]) - height[tmp];
                    res += distance * bounded_height;
                }
            }
            stack.push(i); //如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈
        }
        return res;
    }

}
