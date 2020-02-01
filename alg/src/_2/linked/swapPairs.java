package _2.linked;

/**
 * 24. 两两交换链表中的节点 Medium
 * 给定 1->2->3->4, 你应该返回 2->1->4->3
 */
public class swapPairs {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }
    //非递归操作
    public ListNode swapPairs1(ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(pre.next !=null && pre.next.next != null){
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            node1.next = next;
            node2.next = node1;
            pre.next = node2;
            pre = node1; //此时node1与node2已经交换了，也就是后面要交换得结点的前一个结点
        }
        return dummy.next;
    }

    //递归操作

    /**
     * 返回值：交换完成的子链表
     * 调用单元：设需要交换的两个点为 head 和 next，head 连接后面交换完成的子链表，next 连接 head，完成交换
     * 终止条件：head 为空指针或者 next 为空指针，也就是当前无节点或者只有一个节点，无法进行交换
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }
}
