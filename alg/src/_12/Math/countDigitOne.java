package _12.Math;

//面试题43. 1～n整数中1出现的次数

//总体思想就是分类，先求所有数中个位是 1 的个数，再求十位是 1 的个数，再求百位是 1 的个数...
//假设 n = xyzdabc，此时我们求千位是 1 的个数，也就是 d 所在的位置。
//那么此时有三种情况，
//d == 0，那么千位上 1 的个数就是 xyz * 1000
//d == 1，那么千位上 1 的个数就是 xyz * 1000 + abc + 1
//d > 1，那么千位上 1 的个数就是 xyz * 1000 + 1000
//
//链接：https://leetcode-cn.com/problems/number-of-digit-one/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-50/
public class countDigitOne {
    public int countDigitOne(int n) {
        int count = 0;
        for (long k = 1; k <= n; k *= 10) {
            // xyzdabc
            long abc = n % k;
            long xyzd = n / k;
            long d = xyzd % 10;
            long xyz = (xyzd + 8) / 10;
            count += xyz * k;
            if (d == 1) {
                count += abc + 1;
            }
        }
        return count;
    }
    //4561234  多给r + 8，表示当k位大于1的时候，0 - n / k / 10 为 n / k / 10 + 1位
    public int countDigitOneII(int n) {
        int count = 0;

        for (long k = 1; k <= n; k *= 10) {
            long r = n / k, m = n % k;
            // sum up the count of ones on every place k
            count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }

        return count;
    }

}
