package _3.binaryTree._1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


//297. 二叉树的序列化与反序列化
//面试题37. 序列化二叉树
//序列化
//1.层次遍历访问TreeNode，当节点不为null时，当前值加入queue中，否则加入null
//2.while queue，将结果进行拼接
public class serialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        Queue<String> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        res.offer(String.valueOf(root.val));
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 有左节点就插入左节点，没有就插入null
            if (node.left != null) {
                queue.offer(node.left);
                res.offer(String.valueOf(node.left.val));
            } else {
                res.offer("null");
            }
            // 有右节点就插入右节点，没有就插入null
            if (node.right != null) {
                queue.offer(node.right);
                res.offer(String.valueOf(node.right.val));
            } else {
                res.offer("null");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!res.isEmpty()) {
            sb.append(res.poll());
            if (!res.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1, data.length()-1);//去除[]
        if (data.length() == 0) {
            return null;
        }
        Queue<String> ls = new LinkedList<>(Arrays.asList(data.split(",")));//数组转换为链表
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = null;
        while(!ls.isEmpty()) {
            String res = ls.poll();
            // 创建根节点
            if (root == null) {
                root = new TreeNode(Integer.valueOf(res));
                queue.offer(root);
                continue;
            }
            // 注意：ls的长度总是奇数的，所以除了根节点，其余节点创建时可以一次取两个ls中的元素
            TreeNode father = queue.poll();
            // 创建左节点
            if (!res.equals("null")) {
                TreeNode curr = new TreeNode(Integer.valueOf(res));
                father.left = curr;
                queue.offer(curr);
            }
            // 创建右节点
            res = ls.poll();
            if (!res.equals("null")) {
                TreeNode curr = new TreeNode(Integer.valueOf(res));
                father.right = curr;
                queue.offer(curr);
            }
        }
        return root;
    }
}
