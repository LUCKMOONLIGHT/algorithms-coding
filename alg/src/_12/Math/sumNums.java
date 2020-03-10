package _12.Math;

//面试题64. 求1+2+…+n
//求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
public class sumNums {
    //思路：公式转换   (1+n)*n/2  ->   pow(n,2)+n >> 1
    public int sumNums(int n) {
        return (int) (Math.pow(n, 2) + n ) >> 1;
    }
}
