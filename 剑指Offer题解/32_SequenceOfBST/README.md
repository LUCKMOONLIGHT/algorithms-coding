## 题目描述

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true。否则返回false。假设输入的数组的任意两个数字都互不相同。

## 解题思路

前提条件须知：

1. 二叉搜索树的后续遍历结果中，最后一位是根节点，左子树的值小于根节点，右子树的值大于根节点
2. 递归判断每一个节点的左右子树节点与根节点的大小

## 代码

```java
public class Test32 {
    public static boolean verifySequenceOfBST(int[] sequence) {

        // 输入的数组不能为空，并且有数据
        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        // 有数据，就调用辅助方法
        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }
    public boolean verifySequenceOfBST(int[] sequence,int start,int end){
        if(start > end) return true;
        int index = start;
        while(index < end -1 && sequence[index] < sequence[end]) index++;
        int media = index;
        while (index < end - 1 && sequence[index] > sequence[end]) {
            index++;
        }
        if (index != end - 1) {
            return false;
        }
        index = media;
        return verifySequenceOfBST(sequence, start, index - 1) && verifySequenceOfBST(sequence, index, end - 1);
    }
    
}
```








