package _2.linked;

/**
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 1.从头开始遍历，新建一个链表，遍历过程中隔一个结点取出一个结点，把取出的结点编号为偶数的结点插到新链表中，原链表剩下的都是索引为奇数的元素
 * 2.再把两个链表拼起来
 */
public class oddEvenList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){x = val;}
    }

    public ListNode oddEvenList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode odd = head; //奇数位
        ListNode even = head.next; //偶数位
        ListNode evenHead = even;//偶数头
        while(even !=null && even.next != null){
            odd.next = even.next; //奇数连接
            odd = odd.next; //指针往后移
            even.next = odd.next; //偶数连接
            even = even.next;//指针往后移
        }
        odd.next = evenHead;//奇偶链表连接
        return head;
    }

    public ListNode oddEvenListI(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode o = head;
        ListNode e = head.next;
        ListNode eh = e;
        while(e != null && e.next != null){
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = eh;
        return head;
    }
}
