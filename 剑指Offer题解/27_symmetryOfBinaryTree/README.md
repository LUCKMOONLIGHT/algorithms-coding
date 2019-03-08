## 题目描述

判断一颗二叉树是不是对称的。

## 解题思路

1. 前序遍历法：对称二叉树的前序遍历与镜像前序遍历一致
   1. 先遍历父节点，判断父节点是否一致
   2. 再分别遍历二叉树的左右子树

## 代码

```java
public class Test27 {
    
    public static boolean symmetryOfBinaryTree(BinaryTreeNode node) {
        BinaraTreeNode node2 = node;
        if(node == null && node2 == null)
            return true;
        if(node == null || node2 == null)
            return false;
        if(node.value == node2.value){
            return symmetryOfBinaryTree(node.left,node2.right) || symmetryOfBinaryTree(node.right,node2.left)
        return false;
        }
        }
        }
    }
}
```








