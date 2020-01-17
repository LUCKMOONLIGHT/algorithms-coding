package _6.dfs.DFS.trackback;

import java.util.ArrayList;
import java.util.List;

//22. 括号生成
//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合
public class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0, 0, "", n, res);
        return res;
    }

    public void dfs(int left, int right, String cur, int max, List<String> res){
        if(cur.length() == max*2){ //当长度满足条件时添加
            res.add(cur);
            return;
        }
        if(left < max) dfs(left+1, right, cur+'(', max, res);//1.左括号不能超过n
        if(right < left) dfs(left, right+1, cur+')', max, res);//2.右括号不能超过左括号的个数
    }
}
