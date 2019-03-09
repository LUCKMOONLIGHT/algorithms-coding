## 题目描述

题目：数字序列中的某一位的数字：数字序列以012345678910111213...的格式序列化到一个字符串。求在这个序列中，第n位对应的数字。

## 解题思路

思想：假设n=1001

1. 序列前10位0-9只有一位数字。1001>10,从后面序列中找1001-10=991
2. 接下来180位数字是90个10-99的两位数。991>180.继续从后面找991-180=881
3. 接下来的2700位是900个100-999的三位数。由于881<2700,所以第881位是某个三位数中的一位。881/3+100=270.881%3=1.说明881位是100开始的270个数字即370的中间一位，即7

## 代码

```java
public class Test40 {
    public int nNumber(int n){
        if(n<0)
			return -1;
		if(n==0)
			return 0;
		int sum = 1;
		int beforeSum=1;
		int len = 1;
        //首先看n处于哪个区间段
		while(sum<n){
			beforeSum = sum;
			sum += 9*Math.pow(10, len-1)*len;
			len++;
		}
		
		
		int res = (n-beforeSum)/(len-1);
		
		int left = (n-beforeSum)%(len-1);
		int number = (int) (Math.pow(10, len-1)+res);
		while(left!=0){
			number/=10;
			left--;
		}
		return number%10;
    }
}
```








