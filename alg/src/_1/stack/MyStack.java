package _1.stack;

import java.util.LinkedList;
import java.util.Queue;
//https://leetcode-cn.com/problems/implement-queue-using-stacks/

/**
 * 1.pop  data不为null时，data输出给help，留最后一个用于输出pop，然后交换data与help
 * 2.peek data不为null时，data输出给help，留最后一个用于输出peek，然后peek也加入data，swap
 * 3.push 直接add到data
 * 4.empty data的size不为0
 */
class MyStack {

    private Queue<Integer> data;
    private Queue<Integer> help;

    public MyStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }


    public void push(int pushInt) {
        data.add(pushInt);
    }

    public int top() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (data.size() != 1) {
            help.add(data.poll());
        }
        int res = data.poll();
        help.add(res);
        swap();
        return res;
    }

    public int pop() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (data.size() > 1) {
            help.add(data.poll());
        }
        int res = data.poll();
        swap();
        return res;
    }

    private void swap() {
        Queue<Integer> temp = help;
        help = data;
        data = temp;
    }

    public boolean empty() {
        return data.size() == 0;
    }
}
