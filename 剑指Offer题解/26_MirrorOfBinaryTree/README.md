## 题目描述

请完成一个函数，输入一个二叉树，该函数输出它的镜像

## 解题思路

1. **递归法：**

   先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子结点（交换两个子结点的写法和交换两个变量值的思路是一样的，设置一个中间变量即可）。当交换完所有非叶子结点的左右子结点后，就得到了数的镜像。

## 代码

```java
public class Test26 {
    /**
     * 请完成一个函数，输入…个二叉树，该函数输出它的镜像
     *
     * @param node 二叉树的根结点
     */
    public static void mirror(BinaryTreeNode node) {
        // 如果当前结点不为空则进行操作
        if (node != null) {
            // 下面是交换结点左右两个子树
            BinaryTreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            // 对结点的左右两个子树进行处理
            if(node.left != null)
            	mirror(node.left);
            if(node.right != null)
            	mirror(node.right);
        }
    }
}
```








