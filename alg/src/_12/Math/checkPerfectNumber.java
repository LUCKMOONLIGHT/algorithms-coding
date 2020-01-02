package _12.Math;

/**
 * 507. 完美数
 *
 * 思路：枚举
 * 1. num=28 从1开始枚举到14
 *
 * 完美数很少，只有6, 28, 496, 8128, 33550336
 */
public class checkPerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }
}
