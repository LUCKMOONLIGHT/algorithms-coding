package _0.array;

import java.util.Arrays;

//135. 分发糖果 - Hard
//每个孩子至少分配到 1 个糖果。
//相邻的孩子中，评分高的孩子必须获得更多的糖果。
//[1,0,2]
//输出: 5
//[1,2,2]
//输出: 4
public class candy {

    //O(n)  O(n)
    //左规则 ratings[i] > ratings[i - 1]  left[i] = left[i - 1] + 1
    //右规则 ratings[i] > ratings[i + 1]  right[i] = right[i + 1] + 1
    //最终最少糖果 = 同时满足左规则和右规则
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < ratings.length; i++)   //以ratings[i]结束的子序列获得的糖果数
            if(ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {//以ratings[i]开始的子序列获得的糖果数
            if(ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            count += Math.max(left[i], right[i]);//当前孩纸获得最大糖果数之和
        }
        return count;
    }
}
