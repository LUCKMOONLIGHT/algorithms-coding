package _14.Okami.kmp;

/**
 * 给定一个主串S及一个模式串P，判断模式串是否为主串的子串；
 * 若是，返回匹配的第一个元素的位置（序号从1开始），否则返回0；如S=“abcd”，P=“bcd”，则返回2；S=“abcd”，P=“acb”，返回0。
 */
public class KMP {

    /**
     * 1.朴素算法： 每次失配，S串的索引i定位的本次尝试匹配的第一个字符的后一个。P串的索引j定位到1；T(n)=O(n*m) s回溯到i-j+1 p回溯到0
     * 两个串都有一次遍历，时间复杂度为O(n*m)，效率不高
     */

    /**
     * 2.KMP算法： 每次失配，S串的索引i不动，P串的索引j定位到某个数。T(n)=O(n+m)，时间效率明显提高
     *next数组求解算法：
     * 1.next[0]=-1, next[1]=0
     * 2.求解next[j]时，令k=next[j-1]，
     * 3.比较T[j-1]与T[k]的值，a. 若T[j-1]等于T[k]，则next[j]=k+1。b. 若T[j-1]不等于T[k]，令k=next[k]，若k等于-1，则next[j]=0，否则跳至3。
     */

    /**
     * 求出一个字符数组的next数组
     * @param t 字符数组
     * @return next数组
     * PMT中的值是字符串的前缀集合与后缀集合的交集中最长元素的长度
     * next数组等于‘最大对称长度的前缀后缀’整体向右移动一位，然后初始值赋-1
     */
    public static int[] getNextArray(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int j = 2; j < t.length; j++) {
            k=next[j-1];
            while (k!=-1) {
                if (t[j - 1] == t[k]) {
                    next[j] = k + 1;
                    break;
                }
                else {
                    k = next[k];
                }
                next[j] = 0;  //当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
            }
        }
        return next;
    }

    /**
     * 对主串s和模式串t进行KMP模式匹配
     * @param s 主串
     * @param t 模式串
     * @return 若匹配成功，返回t在s中的位置（第一个相同字符对应的位置），若匹配失败，返回-1
     */
    public static int kmpMatch(String s, String t){
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        int[] next = getNextArray(t_arr);
        int i = 0, j = 0;
        while (i<s_arr.length && j<t_arr.length){
            if(j == -1 || s_arr[i]==t_arr[j]){
                i++;
                j++;
            }
            else
                j = next[j];
        }
        if(j == t_arr.length)
            return i-j;
        else
            return -1;
    }
    // 暴力匹配（伪码）
    public static int search(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (pat.charAt(j) != txt.charAt(i+j))
                    break;
            }
            // pat 全都匹配了
            if (j == M) return i;
        }
        // txt 中不存在 pat 子串
        return -1;
    }

    /**
     * 对于暴力算法，如果出现不匹配字符，同时回退txt和pat的指针，
     * 嵌套 for 循环，时间复杂度 O(MN)，空间复杂度O(1)。最主要的问题是，如果字符串中重复的字符比较多，该算法就显得很蠢。
     * KMP 算法的不同之处在于，它会花费空间来记录一些信息，让pat指针不做不必要的回退
     * KMP 算法永不回退txt的指针i，不走回头路（不会重复扫描txt），而是借助dp数组中储存的信息把pat移到正确的位置继续匹配，
     * 时间复杂度只需 O(N)，用空间换时间，所以我认为它是一种动态规划算法。
     *
     * 计算这个dp数组，只和pat串有关
     * @param args
     */
//    public static void main(String[] args) {
//        System.out.println(kmpMatch("abcabaabaabcacb", "abaabcac"));
//    }
    public static void main(String[] args) {
        System.out.println(kmpMatch("hello", "ll"));
    }
}
