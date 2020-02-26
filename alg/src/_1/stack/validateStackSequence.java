package _1.stack;

import java.util.Stack;

//946. 验证栈序列
//给定 pushed 和 popped 两个序列,判断两数组之间是否可以通过pop push完成
//思路：1.根据pop，控制栈的进出  2.对比是否能遍历整个push数组
public class validateStackSequence {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num:pushed){
            stack.push(num);
            while(i < N && !stack.isEmpty() && stack.peek() == popped[i]){
                stack.pop();
                i++;
            }
        }
        return i == N;
    }

}
