package _2.linked;

/**
 * 2. 两数相加
 * 设立一个表示进位的变量carried，建立一个新链表，把输入的两个链表从头往后同时处理，
 * 每两个相加，将结果加上carried后的值作为一个新节点到新链表后面。
 * 两个链表数相加
 */
public class addTwoNumbers {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null){
            return l1 == null ? l2:l1;
        }
        int carry = 0;
        ListNode resNode = new ListNode(-1);
        ListNode head = resNode;
        while(l1 != null && l2 != null){
            int sum = carry;
            if(l1 !=null){
                sum += l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            resNode.next = new ListNode(sum % 10);
            resNode = resNode.next;
        }
        if(carry != 0) resNode.next = new ListNode(carry);
        return head.next;
    }

}
