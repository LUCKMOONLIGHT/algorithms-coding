package _1.stack;

import java.util.Stack;
/*https://leetcode-cn.com/problems/implement-stack-using-queues/*/

/**
 * 225.用栈模拟队列
 * 1.pop 当out为空，in不为空时，将in输出至out，然后out的pop
 * 2.push 直接push到in
 * 3.empty in和out都不为空时
 * 4.peek 当out为空，in不为空时，将in输出至out，然后out的peek
 */
class MyQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public MyQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void push(int pushInt) {
        stackPush.push(pushInt);
    }

    public int pop() {
        if (stackPush.empty() && stackPop.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackPop.isEmpty() && stackPush.isEmpty();
    }

}