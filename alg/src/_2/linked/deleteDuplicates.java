package _2.linked;

/**
 * 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 */
public class deleteDuplicates {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        while(head != null && head.next != null){
            if(head.val != head.next.val){
                pre = head;
                head = head.next;
            }else{
                while(head != null && head.next != null && head.val == head.next.val){
                    head = head.next;
                }
                if(head.next == null) pre.next = null;
                else{
                    pre.next = head.next;
                    head = head.next;
                }
            }
        }
        return dummy.next;
    }
}
