package _6.dfs.DFS;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//386. 字典序排数
//给定 n =13，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9]
//dfs(1-9)
//dfs(0-9)
public class lexicalOrder {
    //dfs 深度优先搜索 建立一颗10叉树
    //将n个数按照一定的顺序构建成树，然后从左到右对每棵树进行dfs
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++)//10叉树  dfs(1-9)  十位
            dfs(n, i, res);
        return res;
    }
    private static void dfs(int n, int target, List<Integer> list) {
        if (target>n) return;  //target>13
        list.add(target);     //满足条件保存
        for (int i = 0; i < 10; i++) //下一层10叉树  dfs(0-9)  个位
            dfs(n,target*10+i,list);
    }



    //面试题45. 把数组排成最小的数
    //按照字典序列排序，重写comparator方法，根据拼接大小的结果进行排序
    //输入: [3,30,34,5,9]
    //输出: "3033459"
    public String minNumber(int[] nums) {
        //按照字典顺序进行排序，然后拼接起来
        if(nums == null || nums.length == 0) return "";
        List<Integer> arr = new ArrayList<>();
        for(int num:nums) arr.add(num);
        Collections.sort(arr, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                String s1 = o1+""+o2;
                String s2 = o2+""+o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int num:arr){
            sb.append(num);
        }
        return sb.toString();
    }
}
