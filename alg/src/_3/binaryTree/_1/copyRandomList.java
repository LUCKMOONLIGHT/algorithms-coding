package _3.binaryTree._1;

import java.util.HashMap;
import java.util.Map;

//面试题35. 复杂链表的复制
//复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null
public class copyRandomList {
    public Node copyRandomList(Node head) {
        if(head == null) return head;
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node p = head;
        while(p != null){//复制节点
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while(p != null){//连接
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
