package _3.binaryTree._1;


import _2.linked.isPalindromeIII;

//109. 有序链表转换二叉搜索树
//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
//[-10, -3, 0, 5, 9]
//一个可能得答案是  [0, -3, 9, -10, null, 5]
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class findMiddleElement {
    //快慢指针找当前结点
    private ListNode findMiddleElement(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // 偶数的链表中,slow取后面那个
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // 断开中间节点，分为左右结点
        if (prevPtr != null) {
            prevPtr.next = null;
        }
        return slowPtr;
    }

    public TreeNode sortedListToBST(ListNode head) {

        // 如果当前结点不存在，返回null
        if (head == null) {
            return null;
        }

        // 找到中间节点：奇数时找到中间结点，偶数时找到后一个中间节点
        ListNode mid = this.findMiddleElement(head);

        // 链表结点转树结点
        TreeNode node = new TreeNode(mid.val);

        // 当链表中只有一个元素时，不继续迭代生成左右null；要求偶数时中间结点取后面那个
        if (head == mid) {
            return node;
        }

        // 递归左右链表生成左右子树
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
    }
}
