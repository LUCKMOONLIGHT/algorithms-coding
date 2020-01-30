package _1.stack;

import java.util.LinkedList;
import java.util.Queue;
//https://leetcode-cn.com/problems/implement-queue-using-stacks/

/**
 * 1.a为输入队列  b为输出队列
 * 2.push 首先输入a队列中，然后拼接上b队列，交换a，b队列
 */
class MyStack {
    private Queue<Integer> a;//输入队列
    private Queue<Integer> b;//输出队列

    public MyStack() {
        a = new LinkedList<>();
        b = new LinkedList<>();
    }

    public void push(int x) {
        a.offer(x); //此时a为空
        // 将b队列中元素全部转给a队列
        while(!b.isEmpty())
            a.offer(b.poll());
        //此时b为空
        // 交换a和b,使得a队列没有在push()的时候始终为空队列
        Queue temp = a;
        a = b;
        b = temp;
    }

    public int pop() {
        return b.poll();
    }

    public int top() {
        return b.peek();
    }

    public boolean empty() {
        return b.isEmpty();
    }
}