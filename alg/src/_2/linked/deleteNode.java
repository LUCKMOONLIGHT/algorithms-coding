package _2.linked;

/**
 * 删除节点有两种方法
 * 1.在删除节点之前的节点 node.next = node.next.next
 * 2.在删除节点之上  node.val = node.next.val  node.next = node.next.next
 * 1->2->3->4->5
 * 1->2->4->4->5
 * 1->2->4->5
 */
public class deleteNode {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){x = val;}
    }
    //1.在删除结点之上，将当前的值修改为下一结点的值，删除后继节点
    public void deleteNode(ListNode node){
        if(node == null) return ;
        node.val = node.next.val;
        node.next = node.next.next;
    }
    //2.直接删除后继结点

    //面试题18. 删除链表的节点
    //给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
    //节点位置  1.第一个  2.中间  3.末尾

    //head = [4,5,1,9], val = 1      [4,5,9]

    public ListNode deleteNode(ListNode head, int val) {
        if(head == null) return head;
        ListNode cur = head;
        ListNode pre = null;
        if(cur.val == val) return head.next;  //第一个位置
        while(cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = pre.next.next;//中间位置和最后一个位置
        return head;
    }
}
