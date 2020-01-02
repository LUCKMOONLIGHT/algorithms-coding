package _2.linked;

/**
 * 删除链表的倒数第N个节点 Medium
 * 1.找到第N个节点
 * 2.删除节点
 *
 * 1.设置虚拟节点dummyHead指向head
 * 2.设定双指针p和q，初始都指向虚拟节点dummyHead
 * 3.移动q，直到p与q之间相隔的元素个数为n
 * 4.同时移动p与q，直到q指向的为NULL
 * 5.将p的下一个节点指向下下个节点
 */
public class removeNthFromEnd {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        ListNode q = dummy;

        for (int i=1; i<=n+1;i++){
            p = p.next;
        }
        while(p != null){
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;
        return dummy.next;
    }
}
