package _16.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. 最大数
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * 输入: [10,2]
 * 输出: 210
 *
 * 思路：
 * 1.将数字变成字符串，使用compare进行排序后拼接
 * 2.使用基数排序，排序后进行拼接
 */
public class largestNumber {
    private class LargerNumberComparatorI implements Comparator<String> { //一个比较器
        @Override
        public int compare(String a, String b) { //从大到小进行排序
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length]; //将数字变成字符串
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }
        //将字符串按照从大到小排序
        Arrays.sort(asStrs, new LargerNumberComparatorI());

        if (asStrs[0].equals("0")) {
            return "0";
        }
        //将字符串进行拼接
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }

}
