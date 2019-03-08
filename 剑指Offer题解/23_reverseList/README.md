## 题目描述

定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。

## 解题思路

1. 使用尾插法翻转链表|使用栈翻转链表见Test6



## 代码

```java
public class Test23 {
        /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList(ListNode head) {
        // 创建一个临时结点，当作尾插法的逻辑头结点
        ListNode root = new ListNode();
        // 逻辑头结点点的下一个结点为空
        root.next = null;

        // 用于记录要处理的下一个结点
        ListNode next;
        // 当前处理的结点不为空
        while (head != null) {
            // 记录要处理的下一个结点
            next = head.next;
            // 当前结点的下一个结点指向逻辑头结点的下一个结点
            head.next = root.next;
            // 逻辑头结点的下一个结点指向当前处理的结点
            root.next = head;
            // 上面操作完成了一个结点的头插

            // 当前结点指向下一个要处理的结点
            head = next;
        }

        // 逻辑头结点的下一个结点就是返回后的头结点
        return root.next;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        printList(head);
        head = reverseList(head);
        printList(head);
        head = reverseList2(head);
        printList(head);

    }
}
```








