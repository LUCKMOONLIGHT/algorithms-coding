package _6.dfs.DFS;


import java.util.ArrayList;
import java.util.List;

//386. 字典序排数
//给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9]
public class lexicalOrder {
    //dfs 深度优先搜索 建立一颗10叉树
    //将n个数按照一定的顺序构建成树，然后从左到右对每棵树进行dfs
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++)//10叉树
            dfs(n, i, res);
        return res;
    }
    private static void dfs(int n, int target, List<Integer> list) {
        if (target>n) return;
        list.add(target);
        for (int i = 0; i < 10; i++) //下一层10叉树
            dfs(n,target*10+i,list);
    }
}
