package _3.binaryTree._1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//103. 二叉树的锯齿形层次遍历
//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行
//思路：使用层次遍历，使用LinkedList控制插入的顺序 addFirst addLast
public class zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        TreeNode cur;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while(!queue.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                cur = queue.poll();
                if(flag) list.add(cur.val);
                else list.addFirst(cur.val);
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
            res.add(list);
            flag = !flag;
        }
        return res;
    }
}
