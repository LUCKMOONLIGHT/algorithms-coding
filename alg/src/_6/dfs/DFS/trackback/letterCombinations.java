package _6.dfs.DFS.trackback;

import java.util.*;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *  输入："23"
 *  输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 方法：
 * 1.当string的长度与目标的digits的长度相等时，添加string到list中
 * 2.当digits用完了即返回最终结果
 */
public class letterCombinations {
    public static Map<Character, List<String>> map;
    public List<String> letterCombinations(String digit){
        List<String> res = new ArrayList<>();
        if (digit == null || digit.length() == 0) return res;

        map = new HashMap<>();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
        List<String> list = new ArrayList<>();
        dfs(res, list, digit.toCharArray(), 0);
        return res;
    }
    public void dfs(List<String> res, List<String> list, char[] digit, int level){
        if (list.size() == digit.length){
            StringBuffer sb = new StringBuffer();
            for (String str:list){
                sb.append(str);
            }
            res.add(sb.toString());
            return;
        }

        List<String> letters = map.get(digit[level]);
        for (String letter:letters){
            list.add(letter);
            dfs(res, list, digit, level+1);
            list.remove(list.size() - 1);
        }
    }
}
