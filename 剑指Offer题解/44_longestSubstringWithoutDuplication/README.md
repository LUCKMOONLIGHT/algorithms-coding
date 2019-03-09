## 题目描述

**题目**：输入一个字符串，输出其中最长的不重复子串的长度

## 解题思路

主要思路：使用数组记录每个字符出现的位置。找到重复的字母，记录位置，并求重复字符到重复字符之间的距离，取每次迭代最大值

## 代码

```java
public class Test44 {
     public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s="";
        s=in.nextLine();
        char[] c=new char[256];
        int[] charMap=new int[256];      //记录重复字母的下标
        c=s.toCharArray();
        Arrays.fill(charMap,-1);        //初始化为-1,表示没有找到重复的字母
        int max=0;
        int left=0;                     //left用来记录出现重复字母右边的那一个字母位置
        for(int i=0;i<s.length();i++) {
            //找到了重复的字母，记录位置
            if(charMap[c[i]]>=left) {
                left=charMap[c[i]]+1;
            }
            //计算出此时不重复子串的长度
            int tmp=i-left+1;
            if(tmp>max)
                max=tmp;
            //改变记录的位置
            charMap[c[i]]=i;
        }
        System.out.println(max);
    }
}
```








