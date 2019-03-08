## 题目描述
输入一个链表的头结点，从尾结点反过来打印出每个节点的值。

## 解题思路
使用栈实现。

1. 每次迭代一个节点的时候，把该节点放到一个栈中。
2. 当遍历完整个链表后，再从栈顶开始逐个输出节点的值

## 代码

```java
public Node reverserLinkedList2(Node node){
        Stack<Node> nodeStack = new Stack<>();
        Node head = null;
        //存入栈中，模拟递归开始的栈状态
        while (node != null){
            nodeStack.push(node);
            node = node.getNode();
        }
        //特殊处理第一个栈顶元素（也就是反转前的最后一个元素，因为它位于最后，不需要反转，如果它参与下面的while， 因为它的下一个节点为空，如果getNode()， 那么为空指针异常）
        if ((!nodeStack.isEmpty())){
            head = nodeStack.pop();
        }
        //排除以后就可以快乐的循环
        while (!nodeStack.isEmpty()){
            Node tempNode = nodeStack.pop();
            tempNode.getNode().setNode(tempNode);
            tempNode.setNode(null);
        }
        return head;

 }
```

