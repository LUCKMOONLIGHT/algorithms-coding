package _5.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和 = 0
 * 双指针法：for i in len 迭代； left right 指针左右移动控制大小
 * 返回三个指针的位置
 *
 * 思路：
 * 1.定义一个返回值
 * 2.当数组为null或者长度小于3时，直接返回
 * 3.对数组进行从小到大排序
 * 4.从0开始遍历
 * 5.当i的值与之前的值相等时，跳过此遍历
 * 6.当前最小值大于0时，直接break
 * 7.定义两个指针l，r
 * 8.获取当前最小值，判断是否满足要求
 * 9.满足要求则更新lr位置，继续遍历，去重
 * 10.不满足要求，更新l或者r的位置
 */
public class threeSum {
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }
}
