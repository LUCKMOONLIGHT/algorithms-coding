package _9.search;

/**
 * x的平方根
 * x / sqrt = mid
 */
public class mySqrt {
    public int mySqrt(int x){
        int l = 1, h = x;
        while (l <= h){
            int mid = l+(h-l)/2;
            int sqrt = x / mid;
            if (sqrt == mid) return mid;
            else if(sqrt > mid) l = mid + 1;
            else h = mid - 1;
        }
        return h;
    }

    public static void main(String[] args) {
        mySqrt mySqrt = new mySqrt();
        int res = mySqrt.mySqrt(16);
        System.out.println(res);
    }
}
