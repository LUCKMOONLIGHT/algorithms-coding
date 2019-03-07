## 题目描述
用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

## 解题思路
利用两个栈，实现两次进栈出栈（先入后出x2）即可实现队列（先入先出）。

如1，2，3，4，5依次入栈A（此时top为5），出栈并入栈B为：5，4，3，2，1（此时top为1）。此时的栈B即相当于一个队列。

入队：将元素进栈A

出队：判断栈B是否为空，如果为空，则将栈A中所有元素pop，并push进栈B，栈B出栈；

如果不为空，栈B直接出栈。

## 代码

```java
public class Test07 {
    /**
     * 用两个栈模拟的队列
     * 用两个核实现一个队列。队列的声明如下，诸实现它的两个函数appendTail和deleteHead，
     * 分别完成在队列尾部插入结点和在队列头部删除结点的功能。
     */
    public static class MList<T> {
        // 插入栈，只用于插入的数据
        private Stack<T> stack1 = new Stack<>();
        // 弹出栈，只用于弹出数据
        private Stack<T> stack2 = new Stack<>();

        public MList() {
        }
        
        // 添加操作，成在队列尾部插入结点
        public void appendTail(T t) {
            stack1.add(t);
        }

        // 删除操作，在队列头部删除结点
        public T deleteHead() {

            // 先判断弹出栈是否为空，如果为空就将插入栈的所有数据弹出栈，
            // 并且将弹出的数据压入弹出栈中
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.add(stack1.pop());
                }
            }

            // 如果弹出栈中还没有数据就抛出异常
            if (stack2.isEmpty()) {
                throw new RuntimeException("No more element.");
            }

            // 返回弹出栈的栈顶元素，对应的就是队首元素。
            return stack2.pop();
        }
    }
}
```



```python
class Solution:
    def __init__(self):
        self.stack1 = []    # 栈A
        self.stack2 = []    # 栈B
    def push(self, node):
        # write code here
        self.stack1.append(node)    # 向栈顶添加元素
    def pop(self):
        # return xx
        if len(self.stack2) > 0:
            return self.stack2.pop()
        # 将栈A的所有元素pop并push至栈B中，
        while len(self.stack1)>0: # 或者 while self.stack1
            self.stack2.append(self.stack1.pop())
        if len(self.stack2) > 0:
            return self.stack2.pop()
```




