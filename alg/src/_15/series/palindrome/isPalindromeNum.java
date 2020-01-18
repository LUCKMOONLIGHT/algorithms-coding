package _15.series.palindrome;

/**
 * 9. 回文数  - 方法：反转一半数字
 *
 * 1.当 x < 0 时，x 不是回文数;除了0，最后一位为0，不是回文数
 * 2.每次将x的后一位数翻转为   revertedNumber，x减小一位 当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123
 * 3.当两数相等 或者差一位时 为回文
 */
public class isPalindromeNum {
        public boolean isPalindrome(int x) {
            if (x < 0 || (x % 10 == 0 && x != 0)) return false; //当 x < 0 时，x 不是回文数;除了0，最后一位为0，不是回文数
            int revertedNumber = 0;
            while (x > revertedNumber) {  //数字翻转一半 x > revertedNumber
                revertedNumber = revertedNumber * 10 + x % 10;
                x /= 10;
            }
            return x == revertedNumber || x == revertedNumber / 10; //单回文或者双数回文
        }
    }
