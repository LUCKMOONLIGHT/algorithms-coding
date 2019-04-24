## 动态规划

### 解题思路：

#### 一维动态规划

1. 确认原问题与子问题
2. 确认状态
3. 确认边界状态的值{dp[0],dp[1]}
4. 确认状态转移方程dp[i] = dp[i-1]+nums[i]



#### 二维动态规划(填表法)

​	1.分析最优解的结构特征，确定合适的数据结构  (输入数据:r\[i][j]  各个子问题最优解m\[i][j]  各个子问题的最优决策s\[][])

​	2.初始化m\[i][j] = r\[i][j] s\[i][j] = 0

​	3.循环阶段

​	4.构造最优值状态转移方程(m\[i][j] = 0  i=j     min{m\[i][k]+m\[k+1][j]+p\[i-1]\*p[k]\*p\[j]} i < j)



### 一维动态规划

##### 1. 斐波拉契

```java
f[1] = 1
f[2] = 2
for(int i=3;i<=n;i++)
	f[i] = f[i-1]+f[i-2]
```

##### 2. 53最大子序和

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = nums[0];
        for(int i=1;i<nums.length;i++){
            if(sum < 0) sum = nums[i];
            else sum += nums[i];
            res = Math.max(res,sum);
        }
        return res;
    }
}
```

3. ##### 121买卖股票的最佳时机

```java
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i=0;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            max = Math.max(max,prices[i]-min);
        }
        return max;
    }
}
```

4. ##### 198打家劫舍

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n <= 1) return n == 0? 0:nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);     
        for(int i=2;i<n;i++){
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[n-1];
       
    }
}
```

5. ##### 303区域和检索 - 数组不可变

```java
class NumArray {
        int[] sum;  //保存前n项的和
    public NumArray(int[] nums) {
        sum = new int[nums.length];
        if(nums.length == 0) return ;
        sum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            sum[i] += sum[i-1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        if(i == 0) return sum[j];
        else return sum[j] - sum[i-1];
    }
}
```

6. ##### 746使用最小花费爬楼梯

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int pre = 0;
        int ppre = 0;
        if(n == 0) return 0;
        else if(n <= 2) return cost[n];
        else{
            ppre = cost[0];
            pre = cost[1];
            for(int i=2;i<n;i++){
               int num = Math.min(pre+cost[i],ppre+cost[i]);
               ppre = pre;
               pre = num;
            }
            return Math.min(ppre,pre);
        }
        
    }
}
```

7. ##### 1025除数博弈

```java
1.确认原问题与子问题=>原问题：对于N来说爱丽丝是否能赢，子问题：对于i来说爱丽丝是否会赢
2.确认状态=>
3.确认边界状态的值=>dp[1]=false;dp[2]=true
4.确定状态转移方程=>dp[i]=!dp[i-1]
//把偶数留给自己, 把奇数留给对手, 最后剩2的时候选择1即可赢得比赛.
//若己方拿到的是偶数, 每次选择 1, 就可以把奇数留给对手, 己方赢.
//若己方拿到的是奇数, 一定不能选择偶数, 留给对手的一定是偶数, 对手可以留给你奇数, 对手赢.
//动态规划，初始值。
if (N == 1) return false;

boolean[] dp = new boolean[N + 1];
dp[1] = false;

for (int i = 2; i <= N; i++) {
	dp[i] = !dp[i - 1];
}
return dp[N];
```

