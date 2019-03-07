## 题目描述
矩阵中的路径：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。

## 解题思路
通常在**二维矩阵**上**找路径**这类问题都可以应用**回溯法**解决

1. 两层遍历二维数组的每一个点，作为起始点
2. 如果该点超出边界||不满足str[k]||已经被标注flag=1，则直接返回false
3. 如果已经达到了k个字符，返回true
4. 表示满足条件，直接打上标注flag=1，上下左右递归该方法
5. 不满足条件。标注为0，返回false



#### **回溯法**

- 回溯法可以看成蛮力法的升级版，它从解决问题每一步的所有可能选项里系统地选择出一个可行的解决方案。
- 回溯法解决的问题的所有选项可以形象地用树状图结构来表示。在每一步有n个可能的选项，如果叶节点的状态不满足约束条件，那么只能回溯到它的上一个节点再尝试其他的选项。



## 代码

```java
public class Test11 {
	public boolean hasPath(char[] matrix,int rows,int cols,char[] str){
		int flag[] = new int[matrix.length];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols;j++){
				if(helper(matrix,rows,cols,i,j,str,0,flag))
					return true;
			}
		}
		return false;
	}
	
	public boolean helper(char[] matrix,int rows,int cols,int i,int j, char[] str,int k,int[] flag){
		int index = i * cols + j;
		if( i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
			return false;
		if(k == str.length - 1)
			return true;
		flag[index] = 1;
		if(helper(matrix,rows,cols,i - 1,j, str,k + 1,flag)
				|| helper(matrix,rows,cols,i + 1,j,str,k + 1,flag)
				|| helper(matrix,rows,cols,i,j - 1,str,k + 1,flag)
				|| helper(matrix,rows,cols,i,j + 1,str,k + 1,flag)){
			return true;
		}
		flag[index] = 0;
		return false;
	}
}
```








