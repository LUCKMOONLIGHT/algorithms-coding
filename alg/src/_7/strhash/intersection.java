package _7.strhash;

import java.util.*;

/**
 * 349. 两个数组的交集
 *
 * nums1 = [4,9,5], nums2 = [9,4,9,8,4] [9,4]
 *
 * 思路：1.hashset去重
 *      2.contains 判断交集
 */
public class intersection {
    public int[] intersectionI(int[] nums1, int[] nums2){
        if (nums1.length == 0 || nums2.length == 0) return new int[]{};
        Set<Integer> h1 = new HashSet<>();
        Set<Integer> h2 = new HashSet<>();
        for (int num:nums1){
            h1.add(num);
        }
        for (int num:nums2){
            if (h1.contains(num)){
                h2.add(num);
            }
        }
        int[] res = new int[h2.size()];
        int index = 0;
        for (int value:h2){
            res[index++] = value;
        }
        return res;
    }

    //输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
    //nums1 = [1,2,2,1], nums2 = [2,2] [2,2]
    //1.hashmap<int,count> containsKey
    //2.排序+双指针：时间复杂度为O(nlogn)，额外空间复杂度为O(1)
    public int[] intersectionII(int[] nums1, int[] nums2){
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l1 = 0;
        int l2 = 0;
        List<Integer> nums = new ArrayList<>();
        while(l1 < nums1.length && l2 < nums2.length){
            if(nums1[l1] < nums2[l2]) l1++;
            else if(nums1[l1] > nums2[l2]) l2++;
            else{
                nums.add(nums1[l1]);
                l1++;
                l2++;
            }
        }
        int[] res = new int[nums.size()];
        int i=0;
        for(int num:nums){
            res[i++] = num;
        }
        return res;
    }
}
