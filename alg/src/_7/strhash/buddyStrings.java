package _7.strhash;

//859. 亲密字符串
//给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。

//1.如果两个字符串长度不一致，直接false
// 2.如果两个字符串顺序一致，只有有2及2个以上的重复字符，返回false
// 3.当两个字符串顺序不一致时，当且仅当只有两处位置不一致，且不一致的字符相等，返回true，其余返回false
public class buddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;  //1.如果长度不一直，直接返回false
        if (A.equals(B)) { //2.如果两字符串相等（顺序一致），只有有2个重复字符，交换后依旧true
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i)
                count[A.charAt(i) - 'a']++;

            for (int c: count)
                if (c > 1) return true;
            return false;
        } else { //2.如果两字符串顺序不一致，只有当有两个地方不一致，且两个地方的字符串相等时，才返回true
            int first = -1, second = -1; //不相等位置标记
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && A.charAt(first) == B.charAt(second) &&
                    A.charAt(second) == B.charAt(first)); //判断是否2次不相等，且字符相同
        }
    }
}
