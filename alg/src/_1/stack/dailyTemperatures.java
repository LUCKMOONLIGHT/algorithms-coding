package _1.stack;

import java.util.Arrays;
import java.util.Stack;

/**  739. 每日温度 - 单调栈 与比当前元素大的下一个元素的天数差
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替
 * 使用indexStack作为元素的索引栈，resArr存放返回结果
 * 比对当前元素与栈顶元素的大小
 * 若当前元素 < 栈顶元素：入栈
 * 若当前元素 > 栈顶元素：依次弹出栈顶元素，记录两者下标差值即为所求天数
 */
public class dailyTemperatures {
    public static void main(String[] args) {
        int[] a = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(a)));
    }

    public static int[] dailyTemperatures(int[] T){
        int n = T.length;
        Stack<Integer> resPush = new Stack<>();
        int[] resArr = new int[n];
        for (int i=0;i<n;i++){
            while(!resPush.isEmpty() && T[resPush.peek()] < T[i]){
                int curInx = resPush.pop();
                resArr[curInx] = i - curInx;
            }
            resPush.push(i);
        }
        return resArr;
    }
}
