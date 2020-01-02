package _11.MonotonousStack;

/**
 * 738. 单调递增的数字 - 找递减点
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *  N=10 9
 *  N = 332 299
 */
public class monotoneIncreasingDigits {
    /**
     * 1.while 找递减点，然后将递减点之前的点-1
     * 2.将递减点后面的值置为9
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N){
        char[] s = String.valueOf(N).toCharArray();
        int i=1;
        while (i>0 && i<s.length && s[i-1] <= s[i]) i++;
        while (i>0 && i<s.length && s[i-1] > s[i]) s[--i]--;
        for (int j=i+1;j<s.length;j++) s[j] = '9';
        return Integer.parseInt(String.valueOf(s));
        }
}
