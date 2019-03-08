## 题目描述

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

## 解题思路

层次遍历打印二叉树。使用队列结构，每次将队列头部取出，遍历左右子节点，将子节点插入队列尾部

## 代码

```java
public class Test31 {
    public void printFromToBottom(BinaryTree root){
        if(!root.isEmpty){
            Queue<BinaryTree> queue = new LinkList<>();
            queue.add(root);
            BinaryTree cur;
            while(!queue.isEmpty){
                cur = queue.remove();
                System.out.print(cur.value + " ");
                
                if(cur.left != null)
                    queue.add(cue.left);
                if(cur.right != null)
                    queue.add(cue.right);
            }
        }
    }
}
```








