package _8.greed;

/**
 * 1031. 两个非重叠子数组的最大和 - 双指针
 * A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * 子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
 */
public class maxSumTwoNoOverlap {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int a = maxFunc(A, L, M);
        int b = maxFunc(A, M, L);
        return Math.max(a,b);
    }
    public int maxFunc(int[] A, int L, int M){
        if(L+M>A.length){  //子数组和大于原数组长度
            return -1;
        }
        int i,j;
        int max = 0;
        int ret1 = 0;//滑动窗口部分和
        for(i = 0;i<L-1;i++){ //L段滑动窗口子数组和
            ret1+=A[i];
        }
        for(;i<A.length-M;i++){
            ret1 += A[i];
            int ret2 = 0;
            for(j = i+1;j<i+M;j++){ //M段滑动窗口子数组初始和
                ret2+=A[j];
            }
            for(;j<A.length;j++) { //M段滑动窗口滑动范围i+M - len - 1
                ret2 += A[j];
                max = Math.max(max, ret1+ret2); //保存最大值
                ret2 -=  A[j-M+1];
            }
            ret1 -= A[i-L+1];
        }
        return max;
    }
}
