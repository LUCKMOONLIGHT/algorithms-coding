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
     * 141. 环形链表
     * 判断链表中是否有环
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
    /**142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
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
    /**
     * 142. 环形链表 II
     * 找环入口
     * 3.快慢指针（Floyd 算法）
     * 1.fast slow指针先环中相遇
     * 2.head slow指针同时出发，相遇点为环的入口点
     *
     * 1.当慢指针走到距离为n的环节点时，快指针已经在环中距离环节点n的位置，距离环节点还剩b
     * 2.慢指针走距离b后，快结点走了2b路径，两节点相遇，且距离环节点距离n
     */

    public ListNode hasCycleIII(ListNode head){
        //龟兔赛跑，同一起点出发
        if(head == null || head.next == null || head.next.next == null) return null;
        ListNode fast = head.next.next, slow = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    //查找环的的长度
    //第二次相遇的时候快指针比慢指针正好又多走了一圈
    //第一次相遇后距离环节点为n，第二次相遇时，慢指针绕环一圈，快指针绕2圈
    public int hasCycleIIII(ListNode head){
        if(head == null && head.next == null) return 0;
        ListNode slow = head;
        ListNode fast = head.next;
        //第一次相遇，查找环
        while(slow.next != null && fast.next.next != null){
            if(fast == slow) break;
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;
        fast = fast.next.next;
        int cnt = 1;
        //第二次相遇，计算环的长度
        while(slow != fast){
            slow = slow.next;
            fast = fast.next.next;
            cnt ++;
        }
        return cnt;
    }

}
