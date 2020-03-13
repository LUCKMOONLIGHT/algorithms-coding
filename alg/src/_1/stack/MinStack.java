package _1.stack;

import java.util.Stack;

//155.最小栈
//设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
public class MinStack {
    //思路：辅助栈
    //用数据栈正常记录数据的操作，辅助栈寻找当前最小的数，最小栈原理
        /** initialize your data structure here. */
        Stack<Integer> data;
        Stack<Integer> helper;
        public MinStack() {
            data = new Stack<>();
            helper = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if(helper.isEmpty() || helper.peek() >= x){
                helper.add(x); // 维护一个递减栈
            }else helper.add(helper.peek());
        }

        public void pop() {
            if(!data.isEmpty()){
                data.pop();
                helper.pop();
            }
        }

        public int top() {
            if(!data.isEmpty()) return data.peek();
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

        public int getMin() {
            if(!helper.isEmpty()) return helper.peek();
            throw new RuntimeException("栈中元素为空，此操作非法");
        }
    }
