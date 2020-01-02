package _16.sort;

/**
 * 791. 自定义字符串排序 - 桶排序/计数排序
 * 字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次
 * S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。
 * 返回任意一种符合条件的字符串T。
 *
 * S = "cba"
 * T = "abcd"
 * 输出: "cbad"
 */
public class customSortString {
    /**
     * 统计字符数量重新构造字符串【通过】
     * 1.找到T中出现的所有S中的元素
     * 2.将元素按照S中的顺序排序
     * 3.将T中出现了但是S中未出现的元素添加到排好序的元素后面
     *
     * 桶排序之计数排序alpha[26]，将符合条件的放入桶中，并计数，取出时有序,取出的时候按照S的顺序进行去除判断
     * 其余字符找个字符数组存起来即可stringbuffer
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        int[] alpha = new int[26];
        StringBuffer res = new StringBuffer();
        StringBuffer other = new StringBuffer();
        for (char c:T.toCharArray()){
            if(S.indexOf(c) == -1){
                other.append(c);
            }else {
                alpha[c - 'a']++;
            }
        }
        for (char c:S.toCharArray()){
            for (int i=0;i<alpha[c - 'a'];i++){
                res.append(c);
            }
            alpha[c - 'a'] = 0;
        }
        return res.append(other).toString();
    }
}
