package _2.linked;

import java.util.List;

//234. 回文链表 easy 采用空间复杂度O（1）的解法
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


    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        ListNode first = half(head); //快慢指针找中间节点
        ListNode second = reverse(first.next); //反转结点
        //迭代判断是否为回文
        ListNode p = head;
        ListNode q = second;
        boolean res = true;
        while(res && q != null){
            if(p.val != q.val) res = false;
            p = p.next;
            q = q.next;
        }

        first.next = reverse(second); //还原
        return res;
    }
    //反转链表，返回的是右链表的尾结点
    public ListNode reverse(ListNode head){
        ListNode pre = null, cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    //快慢指针找中间结点
    public ListNode half(ListNode head){
        ListNode slow = head; //初始节点
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){//fast到达末尾时，slow为中点
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
