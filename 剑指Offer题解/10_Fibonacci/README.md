## 题目描述
斐波那契数列（Fibonacci sequence），现在要求输入一个整数n，请你输出斐波那契数列的第n项。

## 解题思路
1. Fibonacci序列中的前两个数字是1和1（默认），或0和1，具体取决于所选序列的起始点，并且每个后续数字都是前两个数字的和。

2. 使用两个地址存放prePre和pre的值，节省空间复杂度。不用递归

   

## 代码

```java
public class Test10 {

    /**
     * 写一个函数，输入n，求斐波那契（Fibonacci) 数列的第n项
     * @param n Fibonacci数的项数
     * @return 第n项的结果
     */
    public static long fibonacci(int n) {

        // 当输入非正整数的时候返回0
        if (n <= 0) {
            return 0;
        }

        // 输入1或者2的时候返回1
        if (n == 1 || n == 2) {
            return 1;
        }

        // 记录前两个（第n-2个）的Fibonacci数的值
        long prePre = 1;
        // 记录前两个（第n-1个）的Fibonacci数的值
        long pre = 1;
        // 记录前两个（第n个）的Fibonacci数的值
        long current = 2;

        // 求解第n个的Fibonacci数的值
        for (int i = 3; i <= n ; i++) {
            // 求第i个的Fibonacci数的值
            current = prePre + pre;
            // 更新记录的结果，prePre原先记录第i-2个Fibonacci数的值
            // 现在记录第i-1个Fibonacci数的值
            prePre = pre;
            // 更新记录的结果，pre原先记录第i-1个Fibonacci数的值
            // 现在记录第i个Fibonacci数的值
            pre = current;
        }

        // 返回所求的结果
        return current;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
    }
}
```








