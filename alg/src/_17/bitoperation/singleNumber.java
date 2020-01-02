package _17.bitoperation;

import java.util.HashSet;
import java.util.Set;

/**
 * 136. 只出现一次的数字 - 位运算
 *
 */
public class singleNumber {
    //对所有数字进行异或运算，得到唯一的那个元素
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int num:nums){
            res ^= num;
        }
        return res;
    }

    //137. 只出现一次的数字 II
    //只有一个数组出现一次，其余数字皆出现三次
    /**
     * 设计一个状态转换电路，使得一个数出现3次时能自动抵消为0，最后剩下的就是只出现1次的数。
     * 假设有一个数为x,那么则有如下规律：
     * 0 ^ x = x,
     * x ^ x = 0；
     * x & ~x = 0,
     * x & ~0 =x;
     * a = b = 0
     * b = (b ^ x) & ~a;    x 0 0
     * a = (a ^ x) & ~b;    0 x 0
     */
    public int singleNumberII(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            b = (b ^ x) & ~a;
            a = (a ^ x) & ~b;
        }
        return b;
    }
    //260. 只出现一次的数字 III
    //找出只出现一次的两个元素：给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
    //集合存储，遇到两次就删除
    public int[] singleNumberIII(int[] nums) {
        Set<Integer> hashset = new HashSet<Integer>();
        for (int x:nums){
            if (hashset.contains(x)){
                hashset.remove(x);
            }else {
                hashset.add(x);
            }
        }
        int[] res = new int[hashset.size()];
        int i=0;
        for (int num:hashset){
            res[i++] = num;
        }
        return res;
    }
}
