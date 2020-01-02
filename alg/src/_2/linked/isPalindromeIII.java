package _2.linked;

import java.util.List;

//回文链表 Easy 采用空间复杂度O（1）的解法
//1.如果链表head || head.next 为空，返回true
//2.slow fast 分别到mid和链表末端
//3.反转右链表 reverseList mid  cur, next, pre
//4.while 同时遍历两个结点
public class isPalindromeIII {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }
    public boolean isPalindromeIII(ListNode head){
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        ListNode right = reverseList(mid);
        ListNode left = head;

        while(left != null && right != null){
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head){
        ListNode cur = head, next, pre = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
