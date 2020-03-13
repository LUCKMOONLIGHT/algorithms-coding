package _0.array;

import java.util.Arrays;
//575. 分糖果I
//输入: candies = [1,1,2,2,3,3]
//输出: 3
//解析: 一共有三种种类的糖果，每一种都有两个。
//     最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
//
// 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，
// 每一个数字代表一个糖果。
// 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
//时间复杂度：O(nlogn)。排序需要 O(nlogn) 的时间。
//空间复杂度：O(1)。
public class distributeCandies {
    //思路：最大糖果的种类数  = min(set(kind), n/2)
    public int distributeCandies(int[] candies) {
        int count = 1;
        Arrays.sort(candies);//排序从小到大
        for(int i=1;i<candies.length && count < candies.length / 2;i++){
            if(candies[i-1] < candies[i]) count++;//去除重复，计数种类数，最大不超过n/2
        }
        return count;
    }

    //1103. 分糖果 II
    //给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
    //然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果
    //每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始,剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友

    public int[] distributeCandiesII(int candies, int num_people) {
        int[] arr = new int[num_people];
        Arrays.fill(arr, 0);
        int i=0, cnt = 1;
        while(candies > 0){
            i = i % num_people;
            if(candies >= cnt) arr[i] += cnt;
            else arr[i] += candies;
            candies -= cnt;
            i++;
            cnt++;
        }
        return arr;
    }

    //1103. 分糖果 II
    //迭代糖果数，更新数组存储位置和存储数，更新剩余糖果数，原地加上糖果的负数
    public int[] distributeCandiesIII(int candies, int num_people) {
        int[] ans = new int[num_people];
        int i;
        for (i = 0; candies > 0; i++) {
            ans[i % num_people] += i + 1; //加上上一轮自己分发的糖果数
            candies -= i + 1;  //糖果总数减去已经分发的糖果数，如果减去数后负数
        }
        ans[(i - 1) % num_people] += candies; //原地加上这个负数，退出for循环的时候i+1了，因此此处减一
        return ans;
    }
}
