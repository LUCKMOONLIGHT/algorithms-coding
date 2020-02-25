package _12.Math;


//面试题17. 打印从1到最大的n位数
public class printNumbers {
    //朴素法，找到最大的数，迭代
    public int[] printNumbers(int n) {
        int max = (int)Math.pow(10, n);
        int[] ans = new int[max - 1];
        for(int i = 1 ; i <= max - 1 ; i++){
            ans[i - 1] = i;
        }
        return ans;
    }

    //当数字大小超过了long的最大值，
    // 字符串模拟加法
    // 全排列：求从1~pow(10,n)-1实际上可以转化为0-9在n个位置上的全排列

    //在字符串上模拟加法

    /**
     * 1.初始化长度为num的字符串
     * 2.从个位数上面开始字符相加，0-8相加成功返回true，9->0,继续个位相加
     * 3.从左边开始第一个不为0的位置开始打印
     */
    public static void print1ToMaxOfNDigits(int num){
        if(num<=0)
            return;
        StringBuilder number = new StringBuilder(num);
        for(int i=0;i<num;i++)
            number.append('0');
        while(increment(number)){
            printNumber(number);
        }
    }
    public static boolean increment(StringBuilder str){
        for(int i=str.length()-1;i>=0;i--){ //从个位数字符开始相加，如果相加成功返回
            if(str.charAt(i)<'9' && str.charAt(i)>='0'){
                str.setCharAt(i,(char)(str.charAt(i)+1));
                return true;
            }
            else if(str.charAt(i)=='9'){ //如果为9，个位加1为0，迭代十位加1
                str.setCharAt(i,'0');
            }
            else{
                return false;
            }
        }
        return false;
    }
    public static void printNumber(StringBuilder number){
        boolean flag = false;
        for(int i=0;i<number.length();i++){ //从左边开始打印
            if(flag)
                System.out.print(number.charAt(i));
            else{
                if(number.charAt(i)!='0'){
                    flag = true;//当前i开始不为0
                    System.out.print(number.charAt(i));
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args){
        print1ToMaxOfNDigits(2);
    }


    //字符串做大数相加
    /**
     * public int[] printNumbers(int n) {
     *         int len = (int) Math.pow(10, n);
     *         int[] res = new int[len-1];
     *         StringBuilder sb = new StringBuilder(n);
     *         for(int i=0;i<n;i++){
     *             sb.append('0');
     *         }
     *         int k = 0;
     *         while(increment(sb)){
     *             res[k++] = printnumbers(sb);
     *         }
     *         return res;
     *     }
     *
     *     public boolean increment(StringBuilder sb){
     *         for(int i=sb.length() - 1;i>=0;i--){
     *             if(sb.charAt(i) < '9' && sb.charAt(i) >= '0'){
     *                 sb.setCharAt(i, (char)(sb.charAt(i)+1));
     *                 return true;
     *             }
     *             else if(sb.charAt(i) == '9') sb.setCharAt(i, '0');
     *             else return false;
     *         }
     *         return false;
     *     }
     *
     *     public int printnumbers(StringBuilder sb){
     *         boolean f = false;
     *         StringBuilder res = new StringBuilder();
     *         for(int i=0;i<sb.length();i++){
     *             if(f){
     *                 res.append(sb.charAt(i));
     *             }else{
     *                 if(sb.charAt(i) != '0'){
     *                     f = true;
     *                     res.append((char)sb.charAt(i));
     *                 }
     *             }
     *         }
     *         return Integer.parseInt(res.toString());
     *     }
     */
}
