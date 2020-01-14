package _15.series.K;

//440. 字典序的第K小数字
//给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
//n: 13   k: 2  10
//字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
public class findKthNumber {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;//除了1之外第一小的
        while (k > 0) {//计算前缀之间的step数
            long step = 0, first = cur, last = cur + 1;
            while (first <= n) { //计算前缀之间的step数
                step += Math.min(last, (long) (n + 1)) - first;//前缀间距太大，需要深入一层
                first *= 10;
                last *= 10;
            }
            //前缀间距太大，需要深入一层
            if (step > k) {
                //在树里，往子树里面去看 curr=10，k-1
                k--;
                cur *= 10;
            }
            //间距太小，需要扩大前缀范围
            if (step <= k) {
                //不在树里，curr=2 第二个分支，k减去steps差值
                k -= step;
                cur += 1;
            }
        }
        return cur;
    }

    public static void main(String[] args) {

    }
}
