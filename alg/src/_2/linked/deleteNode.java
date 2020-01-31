package _2.linked;

/**
 * 删除节点有两种方法
 * 1.在删除节点之前的节点 node.next = node.next.next
 * 2.在删除节点之上  node.val = node.next.val  node.next = node.next.next
 * 1->2->3->4->5
 * 1->2->4->4->5
 * 1->2->4->5
 */
public class deleteNode {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){x = val;}
    }
    //1.在删除结点之上，将当前的值修改为下一结点的值，删除后继节点
    public void deleteNode(ListNode node){
        if(node == null) return ;
        node.val = node.next.val;
        node.next = node.next.next;
    }
    //2.直接删除后继结点
}
