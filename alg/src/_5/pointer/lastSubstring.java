package _5.pointer;

//1163. 按字典序排在最后的子串
//给你一个字符串 s，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
//输入："abab"
//输出："bab"
//解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"
//
public class lastSubstring {
    public static void main(String[] args) {
        lastSubstring lastSubstring = new lastSubstring();
        String res = lastSubstring.lastSubstring("cacacb");
        System.out.println(res);
    }

    //12ms

    /**
     *
     * 思路：1.最大字母到结尾的字符串  2.考虑当有两个相同最大字母时，判断后面子串的大小
     * 1.迭代数组，找到最大元素的起始位置
     * 2.当前元素值与最大元素值相同，且与前一个元素不重复的话
     * 3.for 判断k开头的字符与i开头的字符元素大小，如果后者大，更新k，否则break
     */
    public String lastSubstring(String s) {
        char[] array = s.toCharArray();
        int k = 0;
        for(int i=1; i<s.length(); i++){
            if(array[k] < array[i]){
                k = i;
            }else if(array[k] == array[i] && array[i] != array[i - 1]){
                for(int j=0; i+j<s.length(); j++){
                    if(array[k+j] < array[i+j]){
                        k = i;
                        break;
                    }else if(array[k+j] > array[i+j]){
                        break;
                    }
                }
            }
        }
        return s.substring(k);
    }

}
