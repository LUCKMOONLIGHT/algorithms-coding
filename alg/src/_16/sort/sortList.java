package _16.sort;

/**
 * 148. 排序链表 - 链表中的归并排序
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 *  1.递归地将当前链表分为两段(fast-slow，用两个指针，一个每次走两步，一个走一步，直到快的走到了末尾，然后慢的所在的位置就是中间位置，这样就分成了两段)
 *  2.merge(把两段头部结点值比较，用一个p指向较小的结点，且记录第一个结点，然后两段的头一步一步向后走，p也一直向后走，总是指向较小的结点，直到其中一个头为Null，处理剩下的元素，最后返回记录头)
 *
 *  考点：1.归并排序的整体思想
 *      2.找到一个链表的中间节点的方法
 *      3.合并两个已经排好序的链表为一个新的有序链表
 */
public class sortList {
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }
    public ListNode mergeSort(ListNode head){
        if (head.next == null) return head;
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null){
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }
    public ListNode merge(ListNode l, ListNode r){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l != null && r != null){
            if (l.val > r.val){
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }else {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            }
        }
        if (l != null){
            cur.next = l;
        }
        if (r != null){
            cur.next = r;
        }
        return dummy.next;
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}
