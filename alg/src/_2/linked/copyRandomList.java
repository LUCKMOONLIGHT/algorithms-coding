package _2.linked;

//138. 复制带随机指针的链表
//给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

import java.util.HashMap;
import java.util.Map;

//思路：O(n) O(1)
//1.在原始节点后面克隆新节点及其随机节点
//2.将克隆节点与新节点分开
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
public class copyRandomList {
    public Node copyRandomList(Node head) {

        if (head == null) { //如果链表为空，直接返回null
            return null;
        }

        Node ptr = head;
        while (ptr != null) { //滑动指针不为空时

            Node newNode = new Node(ptr.val); //克隆一个新节点

            newNode.next = ptr.next; //将新节点插入到当前节点的后面
            ptr.next = newNode;
            ptr = newNode.next; //移动指针
        }

        ptr = head; //滑动指针还原

        while (ptr != null) {//克隆random
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;//移动两个指针
        }

        //拆分两个链表
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;  //复制结点的头结点
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    //面试题35. 复杂链表的复制
    public Node copyRandomListII(Node head) {
        if(head == null) return head;
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node p = head;
        while(p != null){//复制节点
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while(p != null){//连接结点
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);//返回复制的头结点
    }
}
