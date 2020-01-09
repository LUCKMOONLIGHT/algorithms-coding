package _12.Math;

//231. 2的幂

//思路1：若 n 为 2 的幂，恒有 n & (n - 1) == 0，这是因为
//n 二进制最高位为 1，其余所有位为 0
//n−1 二进制最高位为 0，其余所有位为 1
//一定满足 n > 0
public class isPowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n>0 && (n&(n-1)) == 0;
    }
}
