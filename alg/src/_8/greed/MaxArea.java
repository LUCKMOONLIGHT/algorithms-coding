package _8.greed;

import java.util.Scanner;
import java.util.Stack;

/**
 * 数组最大矩形面积
 *
 * 给定数组arr，其中arr[i]表示1为底，高为arr[i]的矩形，则数组arr可以表示一个柱状图。
 * 这里求该柱状图所包含的矩形中，面积最大的矩形。
 * int arr[] = {2, 4, 7, 3, 5, 4, 6, 9, 4}; 3*8=24
 */
public class MaxArea {
    /**
     * 贪心迭代 i-j之间最小高度的面积
     * @param height
     * @return
     */
    public static int getMaxArea(int[] height) {
        int len = height.length;
        int max = 0;//存储最大面积的
        for(int i = 0;i<len;i++){//i表示依次遍历每一个高度
            int minHeight = height[i];//设i为最小高度
            for(int j=(i+1); j<len; j++){
                minHeight = Math.min(minHeight, height[j]);//找出i之后的最小高度
                max = Math.max(max, (j-i+1)*minHeight);//计算以最小高度为高度的最大面积值
            }
        }
        return max;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println(getMaxArea(arr));
    }

    /**
     * 单调栈
     * 维护一个单调递增栈，当遇到递减项的时候，求最小面积
     */

    public int getlargestRectangleArea(int[] height){
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i=0;i<height.length;i++){
            while (height[stack.peek()] > height[i]){
                int cur = stack.pop();
                int h = height[cur];
                res = Math.max(res, h*(i - cur - 1));
            }
            stack.push(i);
        }
        return res;
    }
}
