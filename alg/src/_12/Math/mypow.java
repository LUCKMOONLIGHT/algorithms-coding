package _12.Math;

/**
 * 50. Pow(x, n) - 快速幂算法（循环）
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 *  快速幂算法能够快速计算出指数非常大的幂
 *  思想:每一步都把指数分成两半，而相应的底数做平方运算
 *  1.把非常大的指数不断变小  2.执行的循环次数也变小
 *  3^10=3*3*3*3*3*3*3*3*3*3
 *  3^10=(3*3)*(3*3)*(3*3)*(3*3)*(3*3)
 *  3^10=(3*3)^5
 *  3^10=9^5
 *
 * 思路：
 * 使用n的二进制表示，如果n的二进制从最低位到最高位表示，某一位=1，则需要将结果累成2i
 */
public class mypow {
    public double myPow(double x, int n) {
        long N = n;
        if(N < 0){
            x = 1/x;
            N = -N;
        }
        double res = 1;
        double base = x;
        for(long i=N;i>0;i/=2){//遍历N，每次把指数缩小一半
            if(i % 2 == 1){ //如果指数为奇数
                res *= base;
            }
            base *= base;//底数变大为原来的平方
        }
        return res;
    }

    /**
     * 暴力法
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }

    /**
     * 折半计算
     * @param x
     * @param n
     * @return
     */
    public double myPow4(double x, int n) {
        double res = 1.0;
        for(int i = n; i != 0; i /= 2){
            if(i % 2 != 0){
                res *= x;
            }
            x *= x;
        }
        return  n < 0 ? 1 / res : res;
    }

}
