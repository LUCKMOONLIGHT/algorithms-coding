package _9.search;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 */
public class findDuplicate {
    /**
     * 1.排序 + for nums[i] == nums[i-1]
     *
     * 快慢指针问题
     * 把nums看成是顺序存储的链表，nums中每个元素的值是下一个链表节点的地址
     * 那么如果nums有重复值，说明链表存在环，本问题就转化为了找链表中环的入口节点，因此可以用快慢指针解决
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 初始时，都指向链表第一个节点nums[0]
        int slow = nums[0];
        int fast = nums[0];
        do { //慢指针走一步，快指针走两步
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast); //循环退出时，slow与fast相遇，相遇节点必在环中

        // 让before，after分别指向链表开始节点，相遇节点
        int before = nums[0];
        int after = slow;
        while (before != after) { //before与after相遇时，相遇点就是环的入口节点
            before = nums[before];
            after = nums[after];
        }

        return before;
    }

}
