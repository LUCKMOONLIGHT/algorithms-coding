package _2.linked;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

//61. 旋转链表
//1->2->3->4->5->NULL, k = 2
//4->5->1->2->3->NULL
public class rotateRight {
    //思路：1.找到尾结点连接头结点 endNode.next = head
    //2.找到断开点n-k%n-1 resNode = node.next; node.next = null
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        ListNode endNode = head;
        int n = 1;
        for(;endNode.next != null;n++) endNode = endNode.next;
        endNode.next = head;
        ListNode newNode = head;
        for(int i=0;i<n-k % n-1;i++) newNode = newNode.next;
        ListNode resNode = newNode.next;
        newNode.next = null;
        return resNode;
    }
}
