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

4. #### [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/)

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

#### [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)**中等**

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 `-1`。

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1]; //amount元钱所需要凑的最小硬币数
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
            dp[i] = Integer.MAX_VALUE;
            boolean flag = false;
            for(int coin:coins){
                if(i-coin >=0 && dp[i-coin] != -1){
                    flag = true;
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
            if(flag == false)
                dp[i] = -1;
        }
        return dp[amount];
    }
}
```

#### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)**中等**

给定一个无序的整数数组，找到其中最长上升子序列的长度。

```java
/**
定义状态：dp[i] = 1  ；表示以第 i 个数字为结尾的最长上升子序列的长度
状态转移方程： dp[i] = Math.max(dp[i],dp[j]+1); 
如果当前的数 nums[i] 大于之前的某个数nums[j]，那么 nums[i] 就可以接在这个数后面形成一个更长的 LIS
**/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len < 2) return len;
        int[] dp = new int[len];
        Arrays.fill(dp,1);
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1); 
                }
            }
        }
        int max = dp[0];
        for(int k=0;k<len;k++){
            max = Math.max(max,dp[k]);
        }
        return max;
    }
}
```

#### [279. 完全平方数](https://leetcode-cn.com/problems/perfect-squares/)**中等**

```java
/**
动态规划：转换为子问题的解dp[i]表示数i的完全平方数
定义状态：dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
状态转移方程：dp[i] = Math.min(dp[i],dp[i-j*j]+1); 需要当前或者减去完全平方数后的最小值
**/

class Solution {
    public int numSquares(int n) {
        if(n<=3) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for(int i=4;i<=n;i++){
            dp[i] = i;//先将其置为最差解，然后判断
            for(int j=1;j*j <= i;j++){
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}
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
1.找到一个视频之后，下一个视频应该跟上一个视频可以连上，同时保证延续的时间最长；
2.将clips排序，第一个视频应从零开始，最后一个视频的结束时间应该大于T，否则返回-1；
3.index记录遍历的位置，end记录已选择视频的结束时间，s记录选择的视频数，若index位置的clip的开始时间小于end，继续后移，知道找到满足条件的最完结束的视频，修改end；
4.若index没有超出范围，修改start，s；
5.反之返回-1；
结束返回s。

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

5. 5最长回文子串

```java
public String longestPalindrome(String s) {
    String S="";
	if(s==null||s.length()==0) {//s为空
		return S;
	}
    int n=s.length();
    char[] a=s.toCharArray();
    int i,j,low=0,high=0,length;//low记录最长的低坐标，high记录最长的高坐标
    for(int k=0;k<n;k++) {
    	//奇回文串，以k为中心点，向左右两边判断
    	i=j=k;
    	length=high-low;
    	while(i>=0&&j<n&&a[i]==a[j]) {
    		if(length<j-i+1) {
    			length=j-i+1;
    			low=i;
    			high=j;
    		}
    		i--;
    		j++;
    	}
    	//偶回文串，以k和k+1为中心点，向左右两边判断
    	i=k;
    	j=k+1;
    	while(i>=0&&j<n&&a[i]==a[j]) {
    		if(length<j-i+1) {
    			length=j-i+1;
    			low=i;
    			high=j;
    		}
    		i--;
    		j++;
    	}
    }
    return S=s.substring(low, high+1);
}
```

887.鸡蛋掉落 **困难**

```java
/**
你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。

每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。

你的目标是确切地知道 F 的值是多少。

无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？

示例 1：

输入：K = 1, N = 2
输出：2
解释：
鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
如果它没碎，那么我们肯定知道 F = 2 。
因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
示例 2：

输入：K = 2, N = 6
输出：3
示例 3：

输入：K = 3, N = 14
输出：4
 

提示：

1 <= K <= 100
1 <= N <= 10000

鸡蛋只可能出现碎或者不碎两种情况：

鸡蛋碎了，鸡蛋数为k-1，再考虑下层，下层为子问题 dp[step-1][k-1]
鸡蛋不碎，鸡蛋数仍为k，再考虑上层，上层子问题为 dp[step-1][k]
再加上当前楼层 f 最终能确定的最大楼层高度为: dp[step-1][k-1] + dp[step-1][k] + 1
得到动态规划的递推公式：
dp[step][k]=dp[step−1][k−1]+dp[step−1][k]+1 

把step看做循环次数，那么方程变为dp[k]+=dp[k-1]+1

疑问是”为什么不是上层与下层的最大值加 1“???
因为摆在我们面前的楼是一个黑盒，我们不能确定F是在上层还是下层或者是f层。我们要保证能确定F的次数，必须要考虑所有的情况，即上层下层和f层之和。
**/
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        return helper(K, N, memo);
    }
    private int helper(int K, int N, int[][] memo) {
        if (N <= 1) {
            return N;
        }
        if (K == 1) {
            return N;
        }
        if (memo[K][N] > 0) {
            return memo[K][N];
        }
        int min = N;
        for (int i = 1; i <= N; i++) {
            int left = helper(K - 1, i - 1, memo);
            int right = helper(K, N - i, memo);
            min = Math.min(min, Math.max(left, right) + 1);
        }
        memo[K][N] = min;
        return min;
    }
}

class Solution {
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K+1];
        int count = 0;
        while(dp[K] < N){
            for(int i=K;i>0;i--){
                dp[i] += dp[i-1] + 1;
            }
            count ++;
        }
        return count;
    }
}
```

#### [329. 矩阵中的最长递增路径](https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/)**困难**

给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

```java
class Solution {

    private int[] row = {-1,1,0,0};
    private int[] col = {0,0,-1,1};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length ==0 || matrix[0].length == 0)
            return 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int[][] len = new int[matrix.length][matrix[0].length];
        int max = 0;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                max = Math.max(max,find(matrix,visited,len,i,j));
            }
        }
        return max;
    }
    private int find(int[][] matrix,boolean[][] visited,int[][] len,int x,int y){
        if(visited[x][y])
            return len[x][y];
        len[x][y] = 1;
        for(int i=0;i<4;i++){
            int curX = x + row[i];
            int curY = y + col[i];
            if(curX >=0 && curX < matrix.length && curY >=0 && curY<matrix[0].length && matrix[curX][curY] > matrix[x][y]){
                len[x][y] = Math.max(len[x][y],find(matrix,visited,len,curX,curY)+1);
            }
        }
        visited[x][y] = true;
        return len[x][y];
    }
}

```



#### [128. 最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/)**困难**

给定一个未排序的整数数组，找出最长连续序列的长度。

输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res=0;
        for (int num:nums){
            if (map.containsKey(num)){
                continue;
            }
            int left = map.containsKey(num-1)?map.get(num-1):0;
            int right = map.containsKey(num+1)?map.get(num+1):0;
            int len = left+right+1;
            res = Math.max(res, len);
            map.put(num, len);  //随便赋值,标记num在map中已存在
            map.put(num-left, len);  //左边端点
            map.put(num+right, len); //右边端点
        }
        return res;
    }
}
```

#### [124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/) **困难**

给定一个**非空**二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径**至少包含一个**节点，且不一定经过根节点。

```java
class Solution {
    
    private int ret = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        /**
        对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
        1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
        2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
        **/
        getMax(root);
        return ret;
    }
    
    private int getMax(TreeNode r) {
        if(r == null) return 0;
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        ret = Math.max(ret, r.val + left + right); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        return Math.max(left, right) + r.val;
    }
}
```

