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



二维动态规划

1. 最长公共子序列 | 最长公共子串

```java
public static int findLCS(String A, int n, String B, int m) {
                int[][] dp = new int[n + 1][m + 1];
                for (int i = 0; i <= n; i++) {
                        for (int j = 0; j <= m; j++) {
                                dp[i][j] = 0;
                        }
                }

                for (int i = 1; i <= n; i++) {
                        for (int j = 1; j <= m; j++) {
                                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                                        dp[i][j] = dp[i - 1][j - 1] + 1;
                                } else {
                                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                                }
                        }
                }
                return dp[n][m];
        }

最长公共子串
# 两个字母相同
if word_a[i] == word_b[j]:
    cell[i][j] = cell[i-1][j-1] + 1
# 两个字母不同
else:
    cell[i][j] = 0
```

2. 游艇租赁问题

```java
/**1.确定合适的数据结构
输入数据r[][] 二维数组m[][]存放各个子问题的最优解  s[][]存放各个子问题的最优决策
2.初始化 m[i][j]=r[i][j] s[i][j]=0
3.循环阶段
4.构造最优解；递归构造两个子问题的最优解
m[][]记录最少租金 循环考虑站点数2-6个
s[][]记录最优策略
**/
void rent()
{
	int i,j,k,d;
	for(d=3;d<=n;d++) #规模
	{
		for(int i=1;i<=n-d+1;i++) #每个规模子问题的最优解
		{
			j = i+d-1; #结尾
			for(int k=i+1;k<j;k++)
			{
				int temp = m[i][k]+m[k][j];
				if(temp < m[i][j])
				{
					m[i][j]=temp;
					s[i][j]=k;
				}
			}
		}
	}
}

//最优解构造函数
void path(int i,int j)
{
	if(s[i][j] == 0)
	{
		print(j)
	}
	path(i,s[i][j])
	path(s[i][j],j)
}
```

3. 快速计算-矩阵连乘

```java
/**
对于给定n个连乘的矩阵，找出一种加括号的方法，使得矩阵连乘的计算量（次数）最小
1.分析最优解的结构特征
假设在第k个位置加括号会得到最优解，那么原问题就变成了两个子问题；原问题的最优解包含子问题的最优解
2.建立最优值递归式
m[i][j] =   0 i=j
			min{m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]} i < j
3.自底向上计算并记录最优值
先求两个矩阵相乘的最优值，再求3个矩阵相乘的最优值，直到n个矩阵连乘的最优值
4.构造最优解
将问题分为小规模的问题，自底向上，将规模放大，直到得到所求规模的问题的解

1.初始化
p[] = {3,5,10,8,2,4}
m[][]
s[][]
2.计算两个矩阵相乘的最优值
r = 2
m[i][j] = min{m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]}  s[i][j]=k
r = 3
m[1][3] = min{k=1,k=2} m[1][1]+m[2][3]+p0p1p3=520 m[1][2]+m[3][3]+p0p2p3=390
**/

void matrixchain()
	int i,j,r,k;
	for(r=2;r <= n; r++)
		for(int i=1;i <= n-r+1; i++)
			j = i+r-1;
			m[i][j] = m[i+1][j]+p[i-1]*p[i]*p[j]
			s[i][j] = i
			for(k = i+1;k < j;k++)
				int t = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j]
				if(t < m[i][j])
					m[i][j] = t
					s[i][j] = k
					
//最优解输出函数
void path(int i,int j)
	if ( i == j)
		print(i)
	path(i,s[i][j])
	path(s[i][j]+1,j)
```

4. 1024视频拼接

```java
/**
找到一个视频之后，下一个视频应该跟上一个视频可以连上，同时保证延续的时间最长；
将clips排序，第一个视频应从零开始，最后一个视频的结束时间应该大于T，否则返回-1；
index记录遍历的位置，end记录已选择视频的结束时间，s记录选择的视频数，若index位置的clip的开始时间小于end，继续后移，知道找到满足条件的最完结束的视频，修改end；
若index没有超出范围，修改start，s；
反之返回-1；
结束返回s。
--------------------- 
作者：redtongue 
来源：CSDN 
原文：https://blog.csdn.net/redtongue/article/details/89163774 
版权声明：本文为博主原创文章，转载请附上博文链接！
**/
class Solution(object):
    def videoStitching(self, clips, T):
        clips.sort() //按行进行排序
        start=0
        end=0
        if(clips[0][0] > 0 or clips[-1][1] < T):
            return -1
        s=0
        index=-1
        while(end < T):
            while(index+1 < len(clips) and clips[index+1][0] <= start):
                index+=1
                end=max(end,clips[index][1])
            if(index < len(clips)):
                start=end
                s+=1
            else:
                return -1
        return s
        
class Solution {
    public int videoStitching(int[][] clips, int T) {
        for (int i = 0; i < clips.length; i++) {
            Arrays.sort(clips[i]);
        }
        if(clips[0][0] > 0 || clips[clips.length-1][1] < T) return -1;
        int start = 0;
        int end = 0;
        int s = 0;
        int index = -1;
        while(end < T){
            while(index + 1 < clips.length && clips[index + 1][0] <= start){
                index +=1;
                end = Math.max(end,clips[index+1][1]);
            }
            if (index < clips.length){
                s+=1;
                start = end;
            }else return -1;
        }
        return s;
    }
}
```

