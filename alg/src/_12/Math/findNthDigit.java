package _12.Math;

//面试题44. 数字序列中某一位的数字
//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
//
//请写一个函数，求任意第n位对应的数字。
public class findNthDigit {
    //123456789 9个数字  9*1位数字
    //1011...9899  90个数字  90*2位数字
    //100...999    900个数字  900*3位数字
    //首先找到这个数字对应的数是几位数，用 digits 表示；
    //然后确定这个对应的数的数值 target；
    //最后确定返回值是 target 中的哪个数字。
    public int findNthDigit(int n) {
        long sum = n;
        long size = 1;
        long max = 9;
        while(sum - size*max > 0){  //先减去个位十位百位....的数字个数 9 90*2 900*3
            sum -= size * max;
            size += 1;
            max *= 10;
        }
        long count = sum / size; //属于哪个数字
        long left = sum % size; //多出来的位数，从左向右偏移
        if(left == 0){
            return (int) (((long)Math.pow(10, size-1)+count-1)%10);
        }else{
            //(long)Math.pow(10, size-1)+count 对应的数值   / ((long)Math.pow(10, size-left))%10)  具体的位置
            return (int)(((long)Math.pow(10, size-1)+count)/((long)Math.pow(10, size-left))%10);
        }
    }

}
