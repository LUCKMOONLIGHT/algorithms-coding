package _2.linked;

//86. 分隔链表
//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
//输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
public class partition {
    //思路：双head
    //1.当前节点小于x时，添加到一个head里，否则添加到另一个head里
    //2.然后连接两个链表
    public ListNode partition(ListNode head, int x) {
        ListNode firstNode = new ListNode(0);
        ListNode first = firstNode;
        ListNode secondNode = new ListNode(0);
        ListNode second = secondNode;
        while(head != null){
            if(head.val >= x){
                second.next = head;
                second = second.next;
            }else{
                first.next = head;
                first = first.next;
            }
            head = head.next;
        }
        second.next = null;
        first.next = secondNode.next;
        return firstNode.next;
    }
}
