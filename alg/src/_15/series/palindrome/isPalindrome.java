package _15.series.palindrome;

//125. 验证回文串
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//输入: "A man, a plan, a canal: Panama"
//输出: true
//思路：1.从字符串中挑选出字母和数字字符  2.使用双指针判断是否为回文
public class isPalindrome {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        for(char c:s.toCharArray()){
            if(c >= 'a' && c <= 'z' || c>= '0' && c <= '9') sb.append(c);
        }
        return checkPalindrome(sb.toString());
    }

    public boolean checkPalindrome(String s){
        int l = 0, r = s.length() - 1;
        while(l < r && s.charAt(l) == s.charAt(r)){
            l++;
            r--;
        }
        return l>=r;
    }


    //验证回文链表
    /**
     * O(n) O(1)
     * 1.找链表中间，反转后面链表
     * 2.对比两个链表判断是否为回文
     * 3.恢复链表
     */

    class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }}

    public boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore the list and return the result.
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
