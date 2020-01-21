package _7.strhash;
//67. 二进制求和
//给定两个二进制字符串，返回他们的和（用二进制表示）。
//
//输入为非空字符串且只包含数字 1 和 0。

//输入: a = "1010", b = "1011"
//输出: "10101"
public class addBinary {
    public String addBinary(String a, String b) {
        //字符串相加
        StringBuilder sb = new StringBuilder();
        char[] ar = a.toCharArray();
        char[] br = b.toCharArray();
        int carry = 0;
        for(int i=ar.length-1, j = br.length-1;i >= 0 || j >= 0;i--,j--){
            int ai = i >= 0 ? ar[i] - '0':0;
            int bi = j >= 0 ? br[j] - '0':0;
            int sum = (ai + bi + carry) % 2;
            carry = (ai + bi + carry) / 2;
            sb.append(sum);
        }
        sb.append(carry == 1 ? 1:"");
        return sb.reverse().toString();
    }
}
