package _5.pointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和，三数之和，四数之和（java）
 * 1.可以借助HashMap，将nums加入到HashMap表中，其中nums中的数作为key，索引作为value, 判断Hashmap中存在target-x且这个数的索引值不等于x自身即可。
 *
 * 2.根据已排序这个特性，
 * （1）如果numbers[left]与numbers[right]的和tmp小于target，说明应该增加tmp,因此left右移指向一个较大的值。
 * （2）如果tmp大于target，说明应该减小tmp,因此right左移指向一个较小的值。
 * （3）tmp等于target，则找到，返回left+1和right+1。（注意以1为起始下标）
 *
 * 2.双指针
 */
public class twoSum {
   public int[] twoSum(int[] nums, int target){
       Map<Integer, Integer> hashmap = new HashMap<>();
       for (int i=0;i<nums.length;i++){
           hashmap.put(nums[i], i);
       }
       for (int i=0;i<nums.length;i++){
           int v = target - nums[i];
           if(hashmap.containsKey(v) && hashmap.get(v) != i){
               return new int[] {i, hashmap.get(v)};
           }
       }
       throw new IllegalArgumentException("No two sum solution");
   }

    int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++; // 让 sum 大一点
            } else if (sum > target) {
                right--; // 让 sum 小一点
            }
        }
        // 不存在这样两个数
        return new int[]{-1, -1};
    }

    /**
     * 进行迭代并将元素插入到表中的同时
     * 我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        twoSum twoSum = new twoSum();
        twoSum.twoSum2(new int[]{3,2,4}, 6);
    }
}


//    public int[] twoSumII(int[] numbers, int target) {
//        if (numbers == null || numbers.length <= 1) {
//            return null;
//        }
//        int[] res = new int[2];
//        int start = 0, end = numbers.length - 1;
//        while (start != end) {
//            //两个数的和可能会超过Integer.MAX_VLAUE
//            long sum = (long) numbers[start] + numbers[end];
//            if (sum == target) {
//                res[0] = start + 1;
//                res[1] = end + 1;
//                //找到一组即退出
//                break;
//            } else if (sum > target) {
//                end--;
//            } else {
//                start++;
//            }
//        }
//        return res;
//    }