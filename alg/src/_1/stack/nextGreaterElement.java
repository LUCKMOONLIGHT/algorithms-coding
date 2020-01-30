package _1.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 496.下一个更大元素 I ：给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 先遍历大数组nums2，首先将第一个元素入栈；
 * 继续遍历，当当前元素小于栈顶元素时，继续将它入栈；当当前元素大于栈顶元素时，栈顶元素出栈，此时应将该出栈的元素与当前元素形成key-value键值对，存入HashMap中；
 * 当遍历完nums2后，得到nums2中元素所对应的下一个更大元素的hash表；
 * 遍历nums1的元素在hashMap中去查找‘下一个更大元素’，当找不到时则为-1。
 */
public class nextGreaterElement {
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int [] res = nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resArr = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {//找下一个比当前元素大的数
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);//找到下一个大的元素，组成kv
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {//结果保存到数组中
            resArr[i] = map.getOrDefault(nums1[i], -1);
        }
        return resArr;
    }

}
