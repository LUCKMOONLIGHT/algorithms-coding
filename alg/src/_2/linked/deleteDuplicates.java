package _2.linked;

/**
 * 82. 删除排序链表中的重复元素 II  中等
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 要求没有重复出现的数字
 */
public class deleteDuplicates {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }
    public ListNode deleteDuplicatesII(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        while(head != null && head.next != null){
            if(head.val != head.next.val){//没有重复元素，指针直接向后移动
                pre = head;
                head = head.next;
            }else{
                while(head != null && head.next != null && head.val == head.next.val){//存在重复元素，head向前移动
                    head = head.next;
                }
                if(head.next == null) pre.next = null;//直到链表尾也为重复元素
                else{//更新pre head指针
                    pre.next = head.next;
                    head = head.next;
                }
            }
        }
        return dummy.next;
    }

    //83. 删除排序链表中的重复元素 简单
    //给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
    //1->1->2   1->2
    //要求重复出现的数字只保存一个
    public ListNode deleteDuplicates(ListNode head){
        ListNode cur = head;
        while(cur != null && cur.next != null){//遍历链表
            if(cur.val == cur.next.val) cur.next = cur.next.next;//当前结点的值等于下一结点的值时，删除下一结点
            else cur = cur.next;//指针指向下一结点
        }
        return head;
    }
}
