## 题目描述
**剪绳子**：给你一根长度为n的绳子，请把绳子剪成m段(m、n都是整数，n>1并且m>1)，每段绳子的长度记为K[0],K[1]....K[m]。请问K[0]\*K[1].....\*K[m]可能的最大乘积是多少？

## 解题思路
1. [**动态规划**](https://blog.csdn.net/u013309870/article/details/75193592)：

   1. 形式

      - **自顶向下的备忘录法**

      - **自底向上的动态规划**

   2. 特点
      - 分析能否把大问题分解成小问题，分解后的每个小问题也存在最优解。把小问题的最优解组合起来能够得到整个问题的最优解。
      - 整体问题的最优解是依赖各个子问题的最优解；（i\n-i）
      - 把大问题分解成若干个小问题，小问题之间还有相互重叠的更小问题

2. 思路：首先定义函数f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值。在剪第一刀时，我们有n-1种选择，也就是说第一段绳子的可能长度分别为1,2,3.....，n-1。因此f(n)=max(f(i)*f(n-i))，其中0<i<n。
   这是一个自上而下的递归公式。由于递归会有大量的不必要的重复计算。一个更好的办法是按照从下而上的顺序计算，也就是说我们先得到f(2),f(3)，再得到f(4),f(5)，直到得到f(n)。

   当绳子的长度为2的时候，只能剪成长度为1的两段，所以f(2) = 1，当n = 3时，容易得出f(3) = 2;



## 代码

```java
public class Test{
	public static void main(String[] args) {
		System.out.println(maxAfterCutting(8));
	}
	/**
	 * 常规的需要O(n2)的时间复杂度和O(n)的空间复杂度的动态规划思路
	 * 题目的意思是:绳子至少是2米，并且必须最少剪一刀。
	 */
	public static int maxAfterCutting(int length){
		if(length<2)
			return 0;
		if(length==2)
			return 1;
		if(length==3)
			return 2;
		// 子问题的最优解存储在f数组中，数组中的第i个元素表示把长度为i的绳子剪成若干段后各段长度乘积的最大值。
		int[] f = new int[length+1]; 
		f[0] = 0;
		f[1] = 1;
		f[2] = 2;
		f[3] = 3;
		int result = 0;
		for(int i = 4;i<=length;i++){
			int max = 0;
			for(int j = 1;j<=i/2;j++){
				int num = f[j]*f[i-j];
				if(max<num)
					max = num;
				f[i] = max;
			}
		}
		result = f[length];
		return result;
	}
}

    
   
```








