package _12.Math;

/**
 * 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转
 * 输入: 123
 * 输出: 321
 *
 * 输入: -123
 * 输出: -321
 */
public class reverse {
    public int reverse(int x) {
        int ans = 0;
        while(x !=0){
            int pop = x%10;
            if((ans > Integer.MAX_VALUE / 10) || (ans == Integer.MAX_VALUE / 10 && pop > 7 ))
                return 0;
            if ((ans < Integer.MIN_VALUE / 10) || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        reverse reverse = new reverse();
        reverse.reverse(Integer.MIN_VALUE);
    }
}
