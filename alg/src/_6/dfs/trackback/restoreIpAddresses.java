package _6.dfs.trackback;

import java.util.ArrayList;
import java.util.List;


//93.复原IP地址
//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
//"25525511135" ["255.255.11.135", "255.255.111.35"]
//回溯 字符串substring
public class restoreIpAddresses {
    // cur : 当前答案，以 String List的形式，最后再join成String形式 例如 [[255],[255],[111],[35]] -> 255.255.111.35
    // pos, 当前扫描到的s的位置， ans最终答案
    private void backtracking(String s, int pos, List<String> cur, List<String> ans) {
        if (cur.size() >= 4) { //满足ip四个字段
            if (pos == s.length()) ans.add(String.join(".", cur));
            return;
        }
        // 分割得到ip地址的一段后，下一段只能在长度1-3范围内选择
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) break; //如果超出边界
            String segment = s.substring(pos, pos+i);
            // 剪枝条件：不能以0开头，不能大于255
            if (segment.startsWith("0") && i > 1 || (i == 3 && Integer.parseInt(segment) > 255)) break; //字段长度大于1且以0开头 || 字段长度等于3且整数大于255
            //回溯
            cur.add(segment);
            backtracking(s, pos+i, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }
}
