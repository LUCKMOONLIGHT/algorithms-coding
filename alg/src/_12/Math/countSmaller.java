package _12.Math;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  计算右侧小于当前元素的个数
 *  给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 *  思路：使用二叉排序树，倒着插入结点，可以插入结点的同时记录比其小的数量
 *
 *  输入: [5,2,6,1]
 *  输出: [2,1,1,0]
 *
 *  思路：0.用二叉排序树，其中count表示小于当前值的节点数
 *       1.数组中的最后一个元素的count=0，因此从最后一个元素开始进行插入
 *       2.当前元素大于root时，count等于root的count加上1
 *
 *       9 ms
 */
public class countSmaller {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ret = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = 0;
        }
        List<Integer> list = new ArrayList<>();
        TreeNode root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, new TreeNode(nums[i]), ret, i);
        }
        return Arrays.asList(ret);
    }

    public TreeNode insert(TreeNode root, TreeNode node, Integer[] ret, int i) {
        if (root == null) {
            root = node;
            return root;
        }
        if (root.val >= node.val) { // 注意小于等于插入到左子树，防止多加1
            root.count++;
            root.left = insert(root.left, node, ret, i);
        } else {
            ret[i] += root.count + 1;
            root.right = insert(root.right, node, ret, i);
        }
        return root;
    }
}

class TreeNode {
    int val;
    int count;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
        count = 0;
    }
}