package _1.stack;

import java.util.Arrays;
import java.util.Stack;

/** 556. 下一个更大元素 III
 * 12 21
 * 输入一个32位正整数，找到下一个最大元素
 */
public class nextGreaterElements3 {
    /**
     * 1.从右到左寻找第一个下降数i
     * 2.从右到左寻找第一个大于i的数j
     * 3.交换i j
     * 4.反转i+1 len-1
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        char[] arr = (""+n).toCharArray();//32位的数组转换成为字符数组
        int i = arr.length - 2;
        while(i>=0 && arr[i+1] <= arr[i]) i--;//从右到左寻找第一个下降数
        if(i < 0) return -1;
        int j = arr.length - 1;
        while(j >= 0 && arr[j] <= arr[i]) j--;//从右到左寻找第一个大于i的数
        swap(arr, i, j);//交换i,j  增大i的下一个更大元素
        reverse(arr, i+1);//反转i+1-len-1  单调递减，变为单调递增
        try{
            return Integer.parseInt(new String(arr)); //字符数组转换位字符串再转换为数字
        }catch(Exception e){
            return -1;
        }
    }

    //交换两个元素
    public void swap(char[] arr, int i, int j){
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //反转指定端
    public void reverse(char[] arr, int start){
        int i=start,j=arr.length - 1;
        while(i < j){
            swap(arr, i, j);
            i++;
            j--;
        }
    }
}
