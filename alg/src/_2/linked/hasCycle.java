package _2.linked;

import java.util.HashSet;
import java.util.Set;

public class hasCycle {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }

    /**
     * 1.判断是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head){
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    /**找环入口
     * 2.Hashset保存ListNode，检查相同结点
     */
    public ListNode hasCycleII(ListNode head){
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)) return head;
            set.add(head);
            head = head.next;
        }
        return null;
    }
    /**找环入口
     * 3.快慢指针（Floyd 算法）
     *
     * 1.fast slow指针先环中相遇
     * 2.head slow指针同时出发，相遇点为环的入口点
     */

    public ListNode hasCycleIII(ListNode head){
        if(head == null || head.next == null ) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow.next != null && fast.next.next != null){
            if(fast == slow) break;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != slow) return null;
        while(head != slow){
            head = head.next;
            slow = slow.next;
        }
        return head;
    }


}
