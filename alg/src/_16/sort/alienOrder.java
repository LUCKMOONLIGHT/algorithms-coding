package _16.sort;

import java.util.*;

public class alienOrder {
    public String alienOrder(String[] words) {
        //1.构建图
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
                //如果字符相同，比较下一个
                if (words[i].charAt(j) == words[i + 1].charAt(j)) continue;
                //保存第一个不同的字符顺序
                Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
                set.add(words[i + 1].charAt(j));
                map.put(words[i].charAt(j), set);
                break;
            }
        }

        //2.拓扑排序
        //创建保存入度的数组
        int[] degrees = new int[26];
        Arrays.fill(degrees, -1);
        //注意，不是26字母都在words中出现，所以出度分为两种情况：没有出现的字母出度为-1，出现了的字母的出度为非负数
        for (String str : words) {
            //将出现过的字符的出度设定为0
            for (char c : str.toCharArray())
                degrees[c - 'a'] = 0;
        }
        //将map中的value的入度都加一
        for (char key : map.keySet()) {
            for (char val : map.get(key)) {
                degrees[val - 'a']++;
            }
        }
        //创建StringBuilder保存拓扑排序
        StringBuilder sb = new StringBuilder();
        //创建一个Queue保存入度为0的节点
        Queue<Character> list = new LinkedList<>();

        int count = 0;//计算图中节点数
        //！=-1 节点数  0 入度为0 起始点 加入list
        for (int i = 0; i < 26; i++) {
            if (degrees[i] != -1) count++;
            if (degrees[i] == 0) {
                list.add((char) ('a' + i));
            }
        }

        while (!list.isEmpty()) {
            Character cur = list.poll();
            sb.append(cur);
            //将邻接点出度-1
            if (map.containsKey(cur)) {
                Set<Character> set = map.get(cur);
                for (Character c : set) {
                    degrees[c - 'a']--;
                    if (degrees[c - 'a'] == 0) list.add(c);
                }
            }
        }

        //判断是否有环
        if (sb.length() != count) return "";
        else return sb.toString();

    }


    public static void main(String[] args) {
        alienOrder alienOrder = new alienOrder();
        String[] strs = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String str = alienOrder.alienOrder(strs);
        System.out.println(str);
    }
}
