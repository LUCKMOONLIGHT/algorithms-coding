package _1.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值 Medium https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *  遇到数字压入栈, 遇到操作符,弹出栈顶两个元素操作
 */
public class evalRPN {
    public static void main(String[] args) {

    }
    public int evalRPN(String[] tokens) {
        String[] symbols = new String[]{"+", "-", "*", "/"};
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (Arrays.asList(symbols).contains(token)) {
                int left = stack.pop();
                int right = stack.pop();
                if (token.equals("+")) stack.push(right + left);
                if (token.equals("-")) stack.push(right - left);
                if (token.equals("*")) stack.push(right * left);
                if (token.equals("/")) stack.push(right / left);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
}

