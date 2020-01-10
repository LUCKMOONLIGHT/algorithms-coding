package _3.binaryTree._1;


//109. 有序链表转换二叉搜索树
//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class findMiddleElement {
    private ListNode findMiddleElement(ListNode head) {

        // 使用快慢指针找到中间节点，slow为中间节点
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // 将链表拆分成两个链表
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    public TreeNode sortedListToBST(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // 找到链表的中间节点
        ListNode mid = this.findMiddleElement(head);

        // 将中间节点构建为根节点
        TreeNode node = new TreeNode(mid.val);

        // 当链表只有一个节点时，中间节点等于头结点，直接返回
        if (head == mid) {
            return node;
        }

        // 递归：使用左右链表来构建左右子树
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
    }
}
