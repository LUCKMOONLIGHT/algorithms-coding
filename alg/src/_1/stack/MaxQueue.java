package _1.stack;


import java.util.ArrayDeque;
import java.util.Deque;

//面试题59 - II. 队列的最大值
//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
//解释：1.输入元素保存到队列尾部   2.输出元素为队列头部元素   3.最大值为当前队列里的最大值
//思路：求滑动窗口的最大值，使用双端队列ArrayDeque保存当前窗口中的最大值
public class MaxQueue {
    private Deque<Integer> data;
    private Deque<Integer> help;
    public MaxQueue() {
        data = new ArrayDeque<>();
        help = new ArrayDeque<>();
    }

    public int max_value() {
        if(help.isEmpty()) return -1;
        else return help.peek();
    }

    public void push_back(int value) {
        data.offer(value);
        //保留每个窗口的最大值
        while (!help.isEmpty() && value > help.peekLast()){
            help.pollLast();
        }
        help.offer(value);
    }

    public int pop_front() {
        if (data.isEmpty()) return -1;
        int value = data.poll();
        if(help.peek() == value) help.poll(); //当最大值元素出队列时，help里的最大值出队列
        return value;
    }
}
