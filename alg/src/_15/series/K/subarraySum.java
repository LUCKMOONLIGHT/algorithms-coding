package _15.series.K;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组 - map
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * nums = [1,1,1], k = 2
 * 2 , [1,1] 与 [1,1] 为两种不同的情况。
 */
public class subarraySum {
    public int subarraySum(int[] nums, int k) {
        /**
         扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
         **/
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ret = 0;

        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i]; //求和sum
            if(map.containsKey(sum-k)) //包含和为k的
                ret += map.get(sum-k); //加上和为k的个数
            map.put(sum, map.getOrDefault(sum, 0)+1); //增加和为sum的数量
        }

        return ret;
    }

    public static void main(String[] args) {
        subarraySum subarraySum = new subarraySum();
        int res = subarraySum.subarraySum(new int[]{1,1,1}, 2);
        System.out.println(res);
    }
}
