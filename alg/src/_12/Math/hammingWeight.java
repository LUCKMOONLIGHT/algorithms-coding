package _12.Math;

public class hammingWeight {

    //191. 位1的个数
    //不超时 最低位的1为0，计算1的个数
    public int hammingWeight(int n) {
        int cnt = 0;
        while(n != 0){
            cnt++;
            n &= (n - 1);//最低位的1为0，计算1的个数
        }
        return cnt;
    }

    //超出时间限制
    public int hammingWeightI(int n) {
        int cnt = 0;
        while(n!=0){
            cnt += (n & 1); //相加最低为的值
            n = n >> 1; //右移，除以2
        }
        return cnt;
    }
}
