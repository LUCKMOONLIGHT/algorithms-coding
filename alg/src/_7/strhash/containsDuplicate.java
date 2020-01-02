package _7.strhash;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 217. 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 *
 */
public class containsDuplicate {
    /**
     * 1.排序  O(nlogn)  O(1) if (nums[i] == nums[i + 1])
     * 2.哈希表:利用支持快速搜索和插入操作的动态数据结构。 O(n) O(n)
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hashset = new HashSet<>(nums.length);
        for (int num:nums){
            if (hashset.contains(num)) return true;
            hashset.add(num);
        }
        return false;
    }

    /**
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
     * 思路：用散列表来维护这个k大小的滑动窗口。
     * O（n）O（min(n,k)）
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateII(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;

    }

    /**
     * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
     * 思路：将每个元素与它之前的 k 个元素比较，查看它们的数值之差是不是在 t 以内。
     * 2.使用 TreeSet（结合 Map 和 BST）加滑动窗口实现
     * @param nums
     * @return
     */
    public boolean containsDuplicateIII(int[] nums, int k, int t) {
        // 滑动窗口结合查找表，此时滑动窗口即为查找表本身（控制查找表的大小即可控制窗口大小）
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 边添加边查找
            // 查找表中是否有大于等于 nums[i] - t 且小于等于 nums[i] + t 的值
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= ((long) nums[i] + (long) t)) {
                return true;
            }
            // 添加后，控制查找表（窗口）大小，移除窗口最左边元素
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;

    }
}
