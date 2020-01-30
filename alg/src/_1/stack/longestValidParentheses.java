package _1.stack;

import java.util.Stack;

//32. 最长有效括号
//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度
//")()())"  4
public class longestValidParentheses {
    //栈
    public int longestValidParentheses(String s) {
        int max = 0;
        if(s == null || s.length() == 0) return max;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        int n = s.length();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(c == '(') stack.push(i);//保存当前左括号的位置
            else{
                stack.pop();//如果是右括号，出栈
                if(stack.isEmpty()) stack.push(i);//如果栈为空，保存当前位置作为起始地址
                else max = Math.max(max, i-stack.peek());//如果栈不为空，求最长有效括号
            }
        }
        return max;
    }

    //左右迭代
    public int longestValidParenthesesII(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) { //完美匹配
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) { //边界条件
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) { //完美匹配
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) { //边界条件
                left = right = 0;
            }
        }
        return maxlength;
    }
}
