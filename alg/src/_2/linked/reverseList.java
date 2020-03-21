package _2.linked;

/**
 * 206.反转链表 - 简单
 * 反转一个单链表
 * 整体反转指针指向
 * 原地反转，是时刻更新pre
 *  头插法
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 */
public class reverseList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }
    public ListNode reverseList(ListNode head){ //原地反转前后链表的做法
        ListNode cur = head, next, pre = null;
        while(cur != null){
            next = cur.next; // 记录后继节点
            cur.next = pre; // 后继指针逆向
            pre = cur; //记录当前结点
            cur = next; //下一个结点成为当前结点
        }
        return pre;
    }
}
