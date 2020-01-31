package _2.linked;

import java.util.List;
import java.util.Stack;

/**
 * 445. 两数相加 II
 * 1.计算时要保证最右边的数对齐 -> 使用栈存放链表中的每个值，进行右对齐
 */
public class addTwoNumbersII {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    //1.先将两个链表保存到栈中
    //2.将链表和保存到栈中
    //3.从栈中取出链表
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null){
            return l1 == null ? l2:l1;
        }
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        Stack<ListNode> resStack = new Stack<>();
        while(l1 != null){
            s1.push(l1);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.push(l2);
            l2 = l2.next;
        }
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty()) {
            int sum = carry;
            if(!s1.isEmpty()){
                sum += s1.pop().val;
            }
            if (!s2.isEmpty()){
                sum += s2.pop().val;
            }
            carry = sum % 10;
            resStack.push(new ListNode(sum / 10));
        }
        if(carry !=0 ) resStack.push(new ListNode(carry));
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while(!resStack.isEmpty()){
            dummy.next = resStack.pop();
            dummy = dummy.next;
        }
        return head.next;
    }
}
