package _7.strhash;

import java.util.HashMap;
import java.util.Map;

/**
 * 在未排序数组中累加和为给定值的最长子数组
 * 思路:hashMap<SUM,INDEX></>
 */
public class maxLength {
    public int maxLength(int[] arr, int k){
        if (arr == null || arr.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        int res = 0;
        for (int i=0;i<arr.length;i++){
            sum += arr[i];
            if(map.containsKey(k - sum)){
                res = Math.max(res, i-map.get(k - sum));
            }
            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return res;
    }
}
