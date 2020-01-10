package _12.Math;

//29. 两数相除 - 求商
//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符
//返回被除数 dividend 除以除数 divisor 得到的商
public class divide {
    //不许用除法，那就用减法；除法的本质就是计算被除数中包含几个除数。如果被除数中包含N个除数，则商就是N。
    public int divide(int dividend, int divisor) { //被除数 除数
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) { //如果被除数是最小值，除数是-1 商为最大值
            return Integer.MAX_VALUE;
        }
        int flag = -1;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) { //判断最终的符号
            flag = 1;
        }
        long a = Math.abs((long) dividend);//被除数
        long b = Math.abs((long) divisor);//除数
        return flag * getRes(a, b);
    }
    //递归增加减法倍数
    private int getRes(long a, long b) {
        if (a < b) {
            return 0;
        }
        int count = 1;
        long tmp = b;
        while (a > b << 1) {
            b = b << 1; //除数x2
            count = count << 1; //倍数x2
        }
        return count + getRes(a - b, tmp);
    }
}
