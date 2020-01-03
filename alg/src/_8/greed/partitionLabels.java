package _8.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间 - 贪心算法
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * S = "ababcbacadefegdehijhklij"
 * [9,7,8]
 *
 */
public class partitionLabels {
    /**
     * 1.计算每个字符出现的最大位置
     * 2.当字符出现的最大位置和位置索引一致时，保存当前长度。
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0) return null;
        int[] last = new int[26];
        for (int i=0;i<S.length();i++){
            last[S.charAt(i) - 'a'] = i; //每个字符出现的最大位置
        }
        List<Integer> res = new ArrayList<>();
        int start = -1,end = 0;
        for (int i=0;i<S.length();i++){
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (end == i){ //当字符出现的最大位置和位置索引一致时，保存当前长度。
                res.add(end - start);
                start = i;
            }
        }
        return res;
    }
   public static void main(String[] args){
        String S = "ababcbacadefegdehijhklij";
        partitionLabels partitionLabels = new partitionLabels();
        partitionLabels.partitionLabels(S);
   }
}
