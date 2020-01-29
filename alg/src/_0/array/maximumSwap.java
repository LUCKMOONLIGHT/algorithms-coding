package _0.array;

//670. 最大交换
//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
public class maximumSwap {
    //思路：
    // 1.将num转换成char数组便于取值
    //2.将每个数字出现的位置标记在last[10]中
    //3.从左到右迭代每一个数字，用后面出现的大数来替换，替换成功立即返回
    public int maximumSwap(int num) {
        if(num <=10) return num;
        char[] arr = String.valueOf(num).toCharArray();//候选数
        int[] last = new int[10];//标记出现位置
        for(int i=0;i<arr.length;i++) last[arr[i] - '0'] = i;//某数最后出现位置

        for(int i=0;i<arr.length;i++){
            for(int j=9;j>arr[i] - '0';j--){
                if(last[j] > i){//如果某数在i后面出现，说明需要交换
                    char tmp = arr[i];
                    arr[i] = arr[last[j]];
                    arr[last[j]] = tmp;
                    return Integer.valueOf(new String(arr));//如果交换了直接返回
                }
            }
        }
        return num;
    }
}
