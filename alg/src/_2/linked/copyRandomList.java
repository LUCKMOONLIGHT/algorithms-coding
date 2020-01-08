package _2.linked;

//138. 复制带随机指针的链表
//给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

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
            ptr = newNode.next;
        }

        ptr = head; //滑动指针还原

        while (ptr != null) {//克隆random
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        //拆分两个链表
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }
}
