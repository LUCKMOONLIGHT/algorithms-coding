package _1.stack;

import java.util.Stack;

//71. 简化路径
//简化绝对路径
public class simplifyPath {
    //1.split . .. "" "str" push pop stringbuilder.append
    public String simplifyPath(String path) {
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length; i++) {
            if (!stack.isEmpty() && s[i].equals(".."))//返回上一个
                stack.pop();
            else if (!s[i].equals("") && !s[i].equals(".") && !s[i].equals(".."))//入栈
                stack.push(s[i]);
        }
        if (stack.isEmpty())
            return "/";

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < stack.size(); i++) {
            res.append("/" + stack.get(i));//从头开始拼接
        }
        return res.toString();
    }
}
