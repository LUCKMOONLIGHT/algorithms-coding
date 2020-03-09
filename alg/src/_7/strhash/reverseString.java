package _7.strhash;

import java.util.PriorityQueue;

/**
 * 344. 反转字符串
 *  编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 思路：左右两个指针i，j i左移，j右移，当两个指针碰撞即停止
 */
public class reverseString {
    public void reversestring(char[] s){
        int i=0,j=s.length-1;
        char tmp;
        while (i<j){
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

    //反转字符串II 每隔K个字符反转K个字符，剩余不足K个字符，全部反转
    public String reverseStr2(String s, int k) {
        char[] s_arr = s.toCharArray();

        for(int i = 0;i < s_arr.length;){
            //剩余不足K个字符，全部反转
            if(i + k >= s_arr.length){
                reverseArr(s_arr,i,s_arr.length - 1);
                break;
            }else{ //反转K个字符
                reverseArr(s_arr,i,i + k-1);
            }
            i += k;

            //每隔K个字符反转
            if(i + k >= s_arr.length){
                break;
            }
            i += k;
        }
        return String.valueOf(s_arr);
    }

    //面试题58 - I. 翻转单词顺序
    //split 逆序for StringBuilder
    public String reverseWords(String s) {
        String[] strs = s.trim().split(" ");//删除字符串的头尾空白符
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for(int i=strs.length - 1;i>=0;i--){
            if("".equals(strs[i])) continue; //空字符串不添加
            sb.append(strs[i].trim());
            if(i != 0) {
                sb.append(" ");  //末尾不添加空字符串
            }

        }
        return sb.toString();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        int[] res = new int[nums.length - 2];
        int j = 0;
        for(int i = 0;i<nums.length;i++){
            queue.offer(nums[i]);
            if(i<k-1) continue;
            res[j++] = queue.peek();
            queue.remove(nums[i-k+1]);
        }
        return res;
    }
    public static void main(String[] args) {
        reverseString reverseString = new reverseString();
        reverseString.reverseWords("  hello world!  ");
    }

    public void reverseArr(char[] arr , int from , int end){
        for(int i = from , j = end; i < j;i++ , j--){
            char t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}
