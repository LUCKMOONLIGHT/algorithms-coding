package _2.linked;

/**
 * 21.合并两个有序链表
 * 1.创建一个head及其node
 * 2.遍历，对比l1和l2，将小的连接到node上，最后返回head.next
 */
public class mergeTwoLists {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(0);
        ListNode node = head;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
            }
            else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        if(l1 != null){
            node.next = l1;
        }
        if(l2 != null){
            node.next = l2;
        }
        return head.next;
    }
}
