package _12.Math;

//面试题66. 构建乘积数组
//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
// 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
//输入: [1,2,3,4,5]
//输出: [120,60,40,30,24]
public class constructArr {
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] B = new int[a.length];
        for(int i=0,pos = 1;i<n;pos *= a[i],i++){  //左迭代，当前元素的值等于之前元素值的乘集
            B[i] = pos;
        }
        for(int i = n-1,pos = 1;i>=0;pos *= a[i],i--){ //从右向左迭代，当前元素的值乘以右边的值
            B[i] *= pos;
        }
        return B;
    }
}
