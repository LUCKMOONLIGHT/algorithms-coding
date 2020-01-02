package _11.MonotonousStack;

import java.util.Arrays;
import java.util.Stack;

/**
 *  单调栈问题
 *  主要解决：
 * 解决一下问题：
 *  比当前元素更大的下一个元素
 *  比当前元素更大的前一个元素
 *  比当前元素更小的下一个元素
 *  比当前元素更小的前一个元素
 */
public class MonotonousStack {
    public static void main(String[] args) {
        MonotonousStack monotonousStack = new MonotonousStack();
        int[] res = monotonousStack.monotonousStack1(new int[]{3,7,4,6,2});
        for (int num:res)
            System.out.println(num);

    }

    /**
     * 比当前元素大的下一个元素
     * 42.接雨水
     * 496.下一个更大元素
     * 503.下一个更大元素I
     * 739.每日温度
     */
    public int[] monotonousStack1(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> modec = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!modec.isEmpty() && nums[modec.peek()] < nums[i]){  //找到比当前元素大的下一个元素
                res[modec.pop()] = nums[i];
            }
            modec.push(i);//维护一个单调递减的栈
        }
        return res;
    }

    /**
     * 比当前元素大的前一个元素
     * 901.股票价格跨度
     * 239.滑动窗口最大值
     */
    public int[] monotonousStack2(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> modec = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!modec.isEmpty() && nums[modec.peek()] < nums[i]){
                modec.pop();
            }
            if (!modec.isEmpty()) res[i] = nums[modec.pop()]; //比当前元素大的前一个元素
            modec.push(i);//维护一个单调递减的栈
        }
        return res;
    }

    /**
     * 比当前元素更小的下一个元素
     * 84.柱状图中最大的矩形
     */
    public int[] monotonousStack3(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> moincrease = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!moincrease.isEmpty() && nums[moincrease.peek()] > nums[i]){
                res[moincrease.pop()] = nums[i];
            }
            moincrease.push(i);//维护一个单调递增栈
        }
        return res;
    }

    /**
     * 比当前元素小的前一个元素
     */
    public int[] monotonousStack4(int[] nums){
        int[] res = new int[nums.length];
        Stack<Integer> moincrease = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!moincrease.isEmpty() && nums[moincrease.peek()] > nums[i]){
                moincrease.pop();
            }
            if (!moincrease.isEmpty()) res[i] = nums[moincrease.pop()];
            moincrease.push(i); //维护一个单调递增栈
        }
        return res;
    }
}
