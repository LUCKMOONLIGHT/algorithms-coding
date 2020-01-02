package _1.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 */
public class isValid {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Map<Character, Character> map = new HashMap();
        //开闭括号的映射关系
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            //当遇到开括号时，入栈
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                //当遇到比括号时，出栈
                if (!stack.empty() && stack.peek() != null && map.get(s.charAt(i)).equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        //结果判断栈是否为空
        return stack.empty();
    }
}
