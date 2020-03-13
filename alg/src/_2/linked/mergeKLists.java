package _2.linked;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个排序链表
 */
public class mergeKLists {
    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 1.小根堆
     * 1.将所有的链表头一次插入小根堆
     * 2.取出小根堆的值，添加到链表中，然后添加堆顶后的元素进小根堆，一次迭代，直到小根堆为空
     * @param head
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        //从小到大排列
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        //将每个链表的头指针加入优先队列中
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            //从优先队列中输出链表头节点，拼接到curr后面，然后将节点的后继节点加入到优先队列
            ListNode nextNode = pq.poll();  //当前最小值的结点
            curr.next = nextNode; //添加到新结点后面
            curr = curr.next;
            if (nextNode.next != null) {//添加最小值结点的后继结点
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }
}
