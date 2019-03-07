## 题目描述
**机器人的运动范围**：地上有一个m行n列的方格。一个机器人从坐标（0,0）的格子开始移动，它每次可以向左，向右，向上，向下移动一格，但不能进入行坐标和列坐标的位数之和大于k的格子。例如：当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18；但它不能进入方格（35,38），因为3 + 5+3+8 = 19.请问该机器人最多能到达多少个格子？

## 解题思路
1. 和前面的题目类似，这个方格也可以看出一个m*n的矩阵。同样在这个矩阵中，除边界上的格子之外其他格子都有四个相邻的格子。
2. 机器人从坐标(0,0)开始移动。当它准备进入坐标为(i,j)的格子时，通过检查坐标的数位和来判断机器人是否能够进入。如果机器人能够进入坐标为(i,j)的格子，我们接着再判断它能否进入四个相邻的格子(i,j-1)、(i-1,j),(i,j+1)和(i+1,j)。





## 代码

```java

class SolutionMethod1{
	public int movingCount(int threshold,int rows,int cols){	
        //创建标记数组，初始化为false
		boolean[] visted = new boolean[rows*cols];
		for(int i = 0; i < visted.length; i++)
			visted[i] = false;
		
		int count = movingCountCore(threshold,rows,cols,0,0,visted);
		return count;
	}
	
	/*
	递归回溯方法：
	@param threshold	约束值
	@param rows			方格行数
	@param cols			方格列数
	@param row			当前处理的行号
	@param col			当前处理的列号
	@param visted		访问标记数组
	@return				最多可走的方格
	*/
	public int movingCountCore(int threshold,int rows,int cols,int row,int col,boolean[] visted){
		int count = 0;
		if(row >= 0 && row < rows && col >= 0 && col < cols
				&& (getDigitSum(row) + getDigitSum(col) <= threshold)
				&& !visted[row* cols + col]){
			visted[row*cols + col] = true;
			//首先加1表示已经满足条件，然后上下左右尝试
			count = 1 + movingCountCore(threshold,rows,cols,row - 1,col,visted) +
					movingCountCore(threshold,rows,cols,row,col - 1,visted) + 
					movingCountCore(threshold,rows,cols,row + 1,col,visted) +
					movingCountCore(threshold,rows,cols,row,col + 1,visted);
		}
		return count;
	}
	
	/*
	一个数字的位数之和
	@param	number 数字
	@return 数字的位数之和
	*/
	public int getDigitSum(int number){
		int sum = 0;
		while(number > 0){
			sum += number%10;
			number /= 10;
		}
		return sum;
	}
}
public class MovingCount {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入限制条件k:");
		int k = scanner.nextInt();
		System.out.println("请输入方格的行数m：");
		int m = scanner.nextInt();
		System.out.println("请输入方格的列数n:");
		int n = scanner.nextInt();
		
		SolutionMethod1 solution1 = new SolutionMethod1();
		
		scanner.close();
		System.out.println("矩阵能到达的方格数是：");
		System.out.println(solution1.movingCount(k, m, n));
	}
}

```








