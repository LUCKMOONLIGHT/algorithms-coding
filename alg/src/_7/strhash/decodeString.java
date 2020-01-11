package _7.strhash;

import java.util.Stack;

//394. 字符串解码
//给定一个经过编码的字符串，返回它解码后的字符串。
//s = "3[a]2[bc]", 返回 "aaabcbc".
//s = "3[a2[c]]", 返回 "accaccacc".
//s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
//
public class decodeString {
    //思路 O(n) O(n)  辅助栈
    //1.mul res 保存数字和字符
    //2.当[时,将mul和res保存到栈中
    //3.遇见]时,将res*栈中的mul,拼接栈中的res,然后保存为res
    public String decodeString(String s) {
        int multi = 0;
        StringBuilder res = new StringBuilder();
        Stack<Integer> mStack = new Stack<>();
        Stack<String> rStack = new Stack<>();

        for(Character c:s.toCharArray()){
            if('[' == c){
                mStack.push(multi);
                rStack.push(res.toString());
                res = new StringBuilder();
                multi = 0;
            }else if(']' == c){
                StringBuilder tmp = new StringBuilder();
                int m = mStack.pop();
                for(int i=0;i<m;i++) tmp.append(res);
                res = new StringBuilder(rStack.pop() + tmp);
            }else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }
}
