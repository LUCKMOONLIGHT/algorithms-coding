package _7.strhash;

import java.util.Stack;

public class reverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        for(String str:strs){
            sb.append(new StringBuilder(str).reverse().toString()+' ');
        }
        return sb.toString().trim();
    }

    public String reverseWordsII(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String[] strs = s.split(" ");
        for(String str:strs) {
            if(str.length() == 0) continue;
            stack.push(str);
        }
        while(!stack.isEmpty()) sb.append(stack.pop()).append(' ');
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        reverseWords reverseWords = new reverseWords();
        String res = reverseWords.reverseWordsII("a good   example");
        System.out.println(res);
    }
}
