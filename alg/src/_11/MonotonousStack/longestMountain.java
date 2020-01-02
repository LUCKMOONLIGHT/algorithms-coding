package _11.MonotonousStack;

/**
 * 845. 数组中的最长山脉
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * [2,1,4,7,3,2,5]
 * 5
 */
public class longestMountain {
    /**
     * 1.先上升后下降，记录最长res
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int res = 0, index = 0;
        int n = A.length;
        while (index < n){
            int end = index;
            if (end + 1 < n && A[end + 1 ] > A[end]){
                while (end + 1  < n && A[end + 1] > A[end]) end++;
                if (end + 1  < n && A[end + 1 ] < A[end]){
                    while (end + 1  < n && A[end + 1 ] < A[end]) end++;
                    res = Math.max(res, end - index + 1);
                }
            }
            index = Math.max(end, index + 1);
        }
        return res;
    }
}
