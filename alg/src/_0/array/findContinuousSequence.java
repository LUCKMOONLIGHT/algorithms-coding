package _0.array;

import java.util.ArrayList;
import java.util.List;

//面试题57 - II. 和为s的连续正数序列
//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）
//思路：双指针作为滑动窗口，注意list[]转数组[][]
public class findContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        //🧠里要有一个区间的概念，这里的区间是(1, 2, 3, ..., target - 1)
        //套滑动窗口模板，l是窗口左边界，r是窗口右边界，窗口中的值一定是连续值。
        //当窗口中数字和小于target时，r右移; 大于target时，l右移; 等于target时就获得了一个解
        List<int[]> list = new ArrayList<>();
        for(int l=1,r=1,sum=0;r<target;r++){
            sum += r; //相加右指针
            while(sum > target) sum -= l++;//相减左指针的值
            if(sum == target){//当获得到一个解后，加入到数组中
                int[] tmp = new int[r - l + 1];
                for(int i=l;i<=r;i++){
                    tmp[i-l] = i;
                }
                list.add(tmp);
            }
        }
        int[][] res = new int[list.size()][];
        for(int i = 0;i<list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
