package _2.linked;

/**
 * 160. 相交链表 简单
 * 找到两个单链表相交的起始节点。
 * 1.当访问A链表的指针访问到链表尾部时，令它从链表B的头部开始访问链表B；
 * 2.当访问B链表的指针访问到链表尾部时，令它从链表A的头部开始访问链表A
 * 这样就能够控制访问A和访问B两个链表指针能够同时访问到交点
 */
public class getIntersectionNode {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }
    public ListNode getIntersectionNode(ListNode l1, ListNode l2){
        ListNode a = l1;
        ListNode b = l2;
        while(a != b){
            a = (a == null)? b:a.next;
            b = (b == null)? a:b.next;
        }
        return a;
    }
}
