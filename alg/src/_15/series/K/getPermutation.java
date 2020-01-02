package _15.series.K;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. 第k个排列
 * 给定n个顺序数，求第n个排列
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1, n!]
 *
 *
 * 比如n=4,这样排列出来的数据就有[1234,1243,1324,1342,1423,1432,2134,
 * 2143,2314,2341,2413,2431,3124...]
 *
 * 第一轮：
 * 可以看到以1开头的有3*2*1 = 6种，同理2.3.4的都是这样
 * 这样8/6 = 1..2（商为1，余数为2）， 结果数据就是索引为1的2（第0个是1）
 * 然后把2从数组中移除
 *
 * 第二轮
 * 这时候数据把2除外就有[134,143,314,341,413,431]
 * 可以看到以1开头的有2*1 = 2种，同理3.4的都是这样
 * 上一把余数是2，所以2/2 = 1..0，结果数据就是索引为1的3（第0个是1）
 *
 * 第三轮
 * 这时候数据把2除外就有[14,41]
 * 可以看到以1开头的有1 =1种，同理4的都是这样
 * 上一把余数是0，所以0/1 = 0..1，结果数据就是索引为0的1（第0个是1）
 * ————————————————
 */
public class getPermutation {
    public static void main(String[] args) {
        getPermutation getPermutation = new getPermutation();
        String res = getPermutation.getPermutation(4, 9);
        System.out.println(res);
    }
    public String getPermutation(int n, int k) {
        if (n <= 1) {
            return "" + n;
        }
        StringBuffer sb = new StringBuffer();
        int[] factorials = new int[n + 1];
        factorials[0] = 1;
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            candidates.add(i);
            factorials[i] = i * factorials[i - 1]; //全排列的值
        }
        k = k - 1;
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorials[i];//当前位的值
            sb.append(candidates.remove(index));
            k -= factorials[i] * index;//更新k
        }

        return sb.toString();
    }


}
