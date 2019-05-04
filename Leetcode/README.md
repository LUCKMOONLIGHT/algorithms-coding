#### [754. 到达终点数字](https://leetcode-cn.com/problems/reach-a-number/)

在一根无限长的数轴上，你站在`0`的位置。终点在`target`的位置。

每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。

返回到达终点需要的最小移动次数。

```java
/***
简单题中的极品。 。。。 首先由于对称性，target是正是负影响不大。

因为比如达到target=2=1-2+3.

如果是-2，那就是-2=-1+2-3

所以相当于是完全对称的一个选择。

那么不妨设这个target是正的（用abs函数）

所以我们尽量往右移动就可以达到目的地。

假设1+2+3+...+k=sum

如果sum=target，毫无疑问那么k就是最终答案。#1

如果sum>target，而且sum-target是一个偶数，那么我们可以翻转一个数字的符号来完成等式。

比如sum-target=4，那么我们把2变成-2，那么sum减小了4.

这是由于（1+2+3+...k）-（1-2+3...k）=4

也就是可以归结为：

当sum-target为偶数，1+...-（sum-target）/2+...+k=target，那么答案依然是k。#2

当sum-target为奇数，那么sum-target+1是一个偶数

类似#2的证明，1+...-(sum-target+1)/2+...k=target-1

此时再考虑k的奇偶性。

如果k是偶数并且k>sum-target+1

那么1+...-(sum-target+1)/2+....-(k/2)...+k+(k+1)=target

由#2相似可证，相当于在1+2....+k+(k+1)减去了sum-target+1和k。

等价于sum+（k+1）-sum+target-1-k====>target也就是答案是k+1.#3

如果k=sum-target+1，由#3可知依然是k+1.#4

如果k是奇数：

1+2+...-(sum-target+1)/2.....+k-(k+1)+(k+2)=sum-(sum-target+1)+1=target,

因此答案是k+2.#5
***/

class Solution {
    public int reachNumber(int target) {
        int t = Math.abs(target);
        int k = 0;
        int sum = 0;
        while(sum < t){
            k++;
            sum += k;
        }
        int dist = sum - t;
        if(dist%2 == 0){
            return k;
        }else{
            if(k%2 == 0)
                return k+1;
            else
                return k+2;
        }
    }
}
```

#### [136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/)

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

```java
/**
1.交换律：a ^ b ^ c <=> a ^ c ^ b
2.任何数于0异或为任何数 0 ^ n => n
3.相同的数异或为0: n ^ n => 0
2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
**/
class Solution {
    public int singleNumber(int[] nums) {
        int len = nums.length;
        int a = 0;
        for(int i=0;i<len;i++){
            a = a ^ nums[i];
        }
        return a;
    }
}
```



#### [137. 只出现一次的数字 II](https://leetcode-cn.com/problems/single-number-ii/)

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

```java
/**
设计一个逻辑电路，a、b变量中，相同位置上，分别取出一位，负责完成00->01->10->00，也就是开头的那句话，当数字出现3次时置零
**/
public static int singleNumber(int[] nums){
        int a=0,b=0;
        for(int i=0;i<nums.length;i++){
            b = (b ^ nums[i]) & ~a;
            a = (a ^ nums[i]) & ~b;
        }
        return b;
    }
```

#### [169. 求众数](https://leetcode-cn.com/problems/majority-element/)

给定一个大小为 *n* 的数组，找到其中的众数。众数是指在数组中出现次数**大于** `⌊ n/2 ⌋` 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

```java
class Solution {
    public int majorityElement(int[] nums) {
		int count = 1;
		int maj = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (maj == nums[i])
				count++;
			else {
				count--;
				if (count == 0) {
					maj = nums[i + 1];
				}
			}
		}
		return maj;
	}
}
```

#### [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

编写一个高效的算法来搜索 *m* x *n* 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int i=row-1,j = 0;
        while(i>=0&&i<row&&j>=0&&j<col){
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] < target) j+=1;
            else i -=1;
        }
        return false;
    }
}
```

#### [88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)

给定两个有序整数数组 *nums1* 和 *nums2*，将 *nums2* 合并到 *nums1* 中*，*使得 *num1* 成为一个有序数组。

**说明:**

- 初始化 *nums1* 和 *nums2* 的元素数量分别为 *m* 和 *n*。
- 你可以假设 *nums1* 有足够的空间（空间大小大于或等于 *m + n*）来保存 *nums2* 中的元素。

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        m = m -1;
        n = n -1;
        
        while(m>=0&&n>=0){
            System.out.print(len);
            if(nums1[m] >= nums2[n]) {
                nums1[len - 1] = nums1[m];
                m --;
                len --;
            }
            else {
                nums1[len - 1] = nums2[n];
                n --;
                len --;
            }
        }
        while(m >=0){
            nums1[len - 1] = nums1[m];
                m --;
                len --;
        }
        while(n >=0){
            nums1[len - 1] = nums2[n];
                n --;
                len --;
        }
    }
}
```

#### [887. 鸡蛋掉落](https://leetcode-cn.com/problems/super-egg-drop/)

你将获得 `K` 个鸡蛋，并可以使用一栋从 `1` 到 `N`  共有 `N` 层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层 `F` ，满足 `0 <= F <= N` 任何从高于 `F` 的楼层落下的鸡蛋都会碎，从 `F` 楼层或比它低的楼层落下的鸡蛋都不会破。

每次*移动*，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 `X` 扔下（满足 `1 <= X <= N`）。

你的目标是**确切地**知道 `F` 的值是多少。

无论 `F` 的初始值如何，你确定 `F` 的值的最小移动次数是多少？

```java
/**
暴力解法
searchTime(K, N) = max( searchTime(K-1, X-1), searchTime(K, N-X) )
**/
class Solution {
    public int superEggDrop(int K, int N) {
        return Solution.recursive(K, N);
    }
    
    public static int recursive(int K, int N) {
        if (N == 0 || N == 1 || K == 1) {
            return N;
        }

        int minimun = N;
        for (int i = 1; i <= N; i++) {
            int tMin = Math.max(Solution.recursive(K - 1, i - 1), Solution.recursive(K, N - i));
            minimun = Math.min(minimun, 1 + tMin);
        }
        return minimun;
    }
}

/**
换一个思路来想：“求k个鸡蛋在m步内可以测出多少层”。我们令dp[k][m]表示k个鸡蛋在m步内可以测出的最多的层数，那么当我们在第X层扔鸡蛋的时候，就有两种情况：

1.鸡蛋碎了，我们少了一颗鸡蛋，也用掉了一步，此时测出N - X + dp[k-1][m-1]层，X和它上面的N-X层已经通过这次扔鸡蛋确定大于F；
2.鸡蛋没碎，鸡蛋的数量没有变，但是用掉了一步，剩余X + dp[k][m-1]，X层及其以下已经通过这次扔鸡蛋确定不会大于F；
也就是说，我们每一次扔鸡蛋，不仅仅确定了下一次扔鸡蛋的楼层的方向，也确定了另一半楼层与F的大小关系，所以在下面的关键代码中，使用的不再是max，而是加法（这里是重点）。评论里有人问到为什么是相加，其实这里有一个惯性思维的误区，上面的诸多解法中，往往求max的思路是“两种方式中较大的那一个结果”，其实这里的相加，不是鸡蛋碎了和没碎两种情况的相加，而是“本次扔之后可能测出来的层数 + 本次扔之前已经测出来的层数”。
所以递推公式是
dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1

将m提出来，要求dp[k]<N,即
while(dp[K] < N)
	dp[i] += dp[i-1] + 1
时间复杂度 O(KN)  空间复杂度O(K)
**/
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

