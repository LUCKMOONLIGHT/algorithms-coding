## 题目描述

题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字

## 解题思路

**分治法**：

1. 把字符串分成两部分：一部分是字符串的第一个字符；另一部分是第一个字符串以后的所有字符。接下来求阴影部分的字符串的排列
2. 拿到第一个字符和它后面的字符逐个交换



扩展：

1.求字符的所有组合

 - 如果输入n个字符，则n个字符能构成1-n的组合。
 - 把n个字符分成两部分，第一部分和其余部分
 - 选取第一个字符和其余部分的m-1个字符
 - 在剩下的n-1个字符里选取m个字符
 - 用递归解决

## 代码

```java
public class Test35 {
    public static void permutation(char[] chars) {
        // 输入较验
        if (chars == null || chars.length < 1) {
            return;
        }
        // 进行排列操作
        permutation(chars, 0);
    }

    /**
     * 求字符数组的排列
     *
     * @param chars 待排列的字符串
     * @param begin 当前处理的位置
     */
    public static void permutation(char[] chars, int begin) {
        // 如果是最后一个元素了，就输出排列结果
        if (chars.length - 1 == begin) {
            System.out.print(new String(chars) + " ");
        } else {
            char tmp;
            // 对当前还未处理的字符串进行处理，每个字符都可以作为当前处理位置的元素
            for (int i = begin; i < chars.length; i++) {
                // 下面是交换元素的位置
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;

                // 处理下一个位置
                permutation(chars, begin + 1);
            }
        }
    }
}
```








