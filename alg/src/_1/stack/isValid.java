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
        if (s == null || s.length() == 0) { //如果输入为空，有效匹配
            return true;
        }
        if (s.length() % 2 == 1) {//如果长度不为偶数，无效匹配
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
                //栈顶为上一个入栈元素，判断当前元素与栈顶元素是否相同，相同则有效匹配
                if (!stack.empty() && stack.peek() != null && map.get(s.charAt(i)).equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        //结果判断栈是否为空，为空则完全匹配
        return stack.empty();
    }
}
