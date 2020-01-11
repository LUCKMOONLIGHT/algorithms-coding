package _12.Math;

import java.util.*;

//645. 错误的集合
//找到1-n 无序集合中重复的数和丢失的整数
public class findErrorNums {
    //重复的数 =  sum(num) - set(sum(nums))
    //丢失的数 = sum(range(1,n)) - set(sum(nums))
    public int[] findErrorNums(int[] nums) {
        int n=nums.length;
        int totalSum = (1+n)*n/2; //原总和
        int numsSum = 0; //现总和
        List<Integer> list = new ArrayList<>();
        for(int num:nums) {
            numsSum += num;
            if(!list.contains(num)) list.add(num);
        }
        int setSum = 0;
        for(int num:list) setSum += num;
        int dup = numsSum - setSum; // 找到重复出现的整数
        int res = totalSum - setSum; // 找到丢失的整数
        return new int[]{dup, res};
    }
}
