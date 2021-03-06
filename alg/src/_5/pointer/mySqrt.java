package _5.pointer;

//69. x 的平方根
//实现 int sqrt(int x) 函数
//计算并返回 x 的平方根，其中 x 是非负整数。
//由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
public class mySqrt {
    public int mySqrt(int x) {
        int min = 0, max = x;
        if(x <= 1) return x;
        while(max - min > 1){
            int m = (max + min) / 2;
            if(x / m < m) max = m; //除m大了，缩小m的范围
            else min = m;//增大m的范围
        }
        return min;
    }
}
