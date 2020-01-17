package _8.greed;


//45. 跳跃游戏 II - hard
//使用最少的跳跃次数到达数组的最后一个位置
//[2,3,1,1,4]  2
public class jump {
    //贪心算法
    public int jump(int[] nums) {
        //每次找局部最优，最后达到全局最优
        //每次在跳跃范围内找跳跃最远的数
        if(nums == null || nums.length == 0) return 0;
        int end = 0;
        int position = 0;
        int step = 0;
        for(int i=0;i<nums.length-1;i++){
            position = Math.max(position, nums[i]+i);
            if(end == i){//找end范围内的最大position,遇到边界，就更新边界，并且步数加一
                end = position;
                step++;
            }
        }
        return step;
    }
}
