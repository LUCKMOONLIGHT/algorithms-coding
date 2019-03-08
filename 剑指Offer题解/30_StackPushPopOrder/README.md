## 题目描述

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

## 解题思路

解决此问题的技巧在于，**构建一个辅助栈stack**。该辅助栈stack就是来模拟pushV的弹出情况是否能和popV一致。如果一致，则表示pushV和popV是满足"栈的压入、弹出序列"关系的，否则，不满足关系。

通过将pushV的元素依次压入到stack中，在每压入新元素时，需要将stack栈顶元素与popV栈顶元素进行比较。如果相同，则将stack和popV栈顶元素都弹出；如果不同，则继续压入新元素。当把pushV序列中所有元素压入stack，并进行stack和popV栈顶严元素比较后，判断此时stack是否为空的情况。

如果stack为空，则表示辅助栈stack与popV序列对应关系一致，即pushV和popV是满足"栈的压入、弹出序列"关系的，否则，不满足关系。

## 代码

```java
public class Test30 {
    public boolean isPopOrder(int[] push,int[] pop){
        boolean isPossible = false;
        if(!push.isEmpty && !pop.isEmpty && push.length > 1 && push.length == pop.length){
            int pushIndex = 0;
            int popIndex = 0;
            Stack<Integer> stack = new Stack<Integer>();
            while(popIndex < pop.length){
                while(stack.isEmpty() || stack.peek()!=pop[popIndex]){
                    if(push >= push.length) break;
                	stack.push(push[pushIndex])
                	pushIndex++;
                }
                if(stack.peek() != pop[popIndex]) break;
                stack.pop();
                popIndex++;
            }
            if(stack.isEmpty()) return true;
        }
    }
}
```








