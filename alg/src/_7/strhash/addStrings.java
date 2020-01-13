package _7.strhash;


//415. 字符串相加
//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和
//你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
//charAt(i) 从右到左开始 即从个位开始   sb拼接后也是从右边拼接  最后结果需要反转
//carry 记录进位  sum记录当前和
//123 456 579
//0-n charAt(i) 从左到右遍历
public class addStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i=num1.length() - 1,j=num2.length() - 1; i>=0 || j>=0 || carry != 0;i--,j--){
            int x1 = i < 0 ? 0 : num1.charAt(i) - '0';
            int x2 = j < 0 ? 0 : num2.charAt(j) - '0';
            int num = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            sb.append(num);
        }
        return sb.reverse().toString();
    }
}
