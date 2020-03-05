package _6.dfs.trackback;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class partition {
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> getpartition(String s){
        if (s == null || s.length() == 0) return res;

        dfs(s, new ArrayList<String>(), 0);
        return res;
    }
    public void dfs(String str, List<String> list, int index){
        if (index == str.length()){ //当l==s的len时，表示已经到底了,为回文
            res.add(new ArrayList<>(list));
        }
        for (int i=index;i<str.length();i++){
            if (isPalindrome(str,index, i)){
                list.add(str.substring(index, i+1));
                dfs(str, list, i+1); //从下一个字符开始递归
                list.remove(list.size() - 1);
            }
        }
    }
    /**
     * 判断是否是回文，指针碰撞
     */
    public boolean isPalindrome(String str, int l, int r){
        while (l < r && str.charAt(l) == str.charAt(r)){
            l ++ ;
            r -- ;
        }
        return l >= r;
    }
}
