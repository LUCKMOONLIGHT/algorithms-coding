package _2.linked;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
 * 反转首位结点之间的链表
 * 1.指针方向不变，数字变化
 * 方法：头插法
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 */

public class reverseBetween {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }
    public ListNode reverseBetweenII(ListNode head, int m, int n){//原地头插法，反转指定区间的链表
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for(int i = 1; i < m; i++){
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for(int i = m; i < n; i++){ //头插法，每次将cur的next节点插入到pre后面
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

}
