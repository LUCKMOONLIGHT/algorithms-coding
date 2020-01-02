package _12.Math;

/**
 * 丑数
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数
 *
 * 思路：1.边界条件判断
 * 2.如果能被2，3，5整除的话，整除后判断是否为1
 */
public class isUgly {
    public boolean isUgly(int num) {
        if (num == 0) return false;
        while (num != 1){
            if (num % 2 == 0){
                num /= 2;
                continue;
            }
            if (num % 3 == 0){
                num /= 3;
                continue;
            }
            if (num %5 == 0){
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }
}
