package _15.series.K;

/**
 * 668. 乘法表中第k小的数 - 二分法
 *
 * m = 3, n = 3, k = 5  3   3*3的乘法表中找第5小的数
 * 1	2	3
 * 2	4	6
 * 3	6	9
 */
public class findKthNumberMultiTable {
    /**
     * 求个每一行不大于mid的个数
     */
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while(r > l){
            int mid = (l + r) / 2;
            int cnt = 0;
            for(int i=1;i<=m;i++){
                cnt += Math.min(n,mid /i);
            }
            if(cnt >= k){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }
}
