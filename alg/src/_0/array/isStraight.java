package _0.array;

import java.util.Arrays;

//面试题61. 扑克牌中的顺子
//从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
// 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
//输入: [0,0,1,2,5]
//输出: True
public class isStraight {
    public boolean isStraight(int[] nums) {
        int zero = 0, diff = 0;  //记录0的个数，顺序差异的个数
        Arrays.sort(nums); //排序后后面的等于或者大于前面的
        for(int i=0;i<nums.length-1;i++){//迭代
            if(nums[i] == 0){//记录为0的个数
                zero++;
            }else{
                if(nums[i] == nums[i+1]) return false;//如果不为0而且重复的话，false
                if(nums[i+1] - nums[i] != 1){//如果相差大于1的话，记录差异
                    diff += nums[i+1] - nums[i] - 1; //记录大于1的差异
                }
            }
        }
        return zero >= diff;//判断是否能组合成顺子
    }
}
