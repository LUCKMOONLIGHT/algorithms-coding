package _1.stack;

import java.util.Arrays;
import java.util.Stack;

/** 503. 下一个更大元素 II
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 *给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 */
public class nextGreaterElements2 {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        int[] resArr = nextGreaterElements(nums);
        System.out.println(Arrays.toString(resArr));
    }

    public static int[] nextGreaterElements(int[] nums){
        int n = nums.length;
        int[] resArr = new int[n];
        Arrays.fill(resArr, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i< 2*n; i++){
            int num = nums[i % n]; //循环队列
            while(!stack.isEmpty() && nums[stack.peek()] < num){
                resArr[stack.pop()] = num;
            }
            if(i < n) stack.push(i);//维护一个单调递增栈的下标位置
        }
        return resArr;
    }
}
