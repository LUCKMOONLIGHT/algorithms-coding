## 题目描述

题目：输入一个字符串，打印出该字符事中字符的所有排列。例如输入字符串abc。则打印出由字符a、b、c 所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。

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
public class Test36 {
        /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     *
     * @param numbers 输入数组
     * @return 找到的数字
     */
    public static int moreThanHalfNum(int[] numbers) {

        // 输入校验
        if (numbers == null || numbers.length < 1) {
            throw new IllegalArgumentException("array length must large than 0");
        }

        // 用于记录出现次数大于数组一半的数
        int result = numbers[0];
        // 于当前记录的数不同的数的个数
        int count = 1;
        // 从第二个数开始向后找
        for (int i = 1; i < numbers.length; i++) {
            // 如果记数为0
            if (count == 0) {
                // 重新记录一个数，假设它是出现次数大于数组一半的
                result = numbers[i];
                // 记录统计值
                count = 1;
            }
            // 如果记录的值与统计值相等，记数值增加
            else if (result == numbers[i]) {
                count++;
            }
            // 如果不相同就减少，相互抵消
            else {
                count--;
            }
        }

        // 最后的result可能是出现次数大于数组一半长度的值
        // 统计result的出现次数
        count = 0;
        for (int number : numbers) {
            if (result == number) {
                count++;
            }
        }

        // 如果出现次数大于数组的一半就返回对应的值
        if (count > numbers.length / 2) {
            return result;
        }
        // 否则输入异常
        else {
            throw new IllegalArgumentException("invalid input");
        }
    }
}
```








