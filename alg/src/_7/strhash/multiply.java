package _7.strhash;

//43. 字符串相乘
//num1 = "2", num2 = "3" "6"
//num1 = "123", num2 = "456"  "56088"
public class multiply {
    /**
     * 1.如果任意数字为0，返回0
     * 2.从num2的个位数开始迭代，乘以num1的每位数
     * 3.注意补0
     * 4.字符串相加
     * @param num1
     * @param num2
     * @return
     */
    public String multiplyI(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStringsI(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStringsI(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) return "0";
        String res = "0";
        for(int i=num2.length() - 1;i>=0;i--){
            int carry = 0;
            int n = num2.charAt(i) - '0';
            StringBuilder builder = new StringBuilder();
            for(int k = 0;k<num2.length() -1 -i;k++) builder.append('0');
            for(int j=num1.length() - 1;j>=0 || carry != 0;j--){
                int m = j<0?0:num1.charAt(j) - '0';
                int sum = (m*n+carry) % 10;
                builder.append(sum);
                carry = (m*n+carry) / 10;
            }
            res = addString(res, builder.reverse().toString());
        }
        return res.toString();
    }

    public String addString(String num1, String num2){
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i=num1.length() -1,j=num2.length() - 1;i>=0 || j>=0|| carry != 0;i--,j--){
            int m = i<0?0:num1.charAt(i);
            int n = j<0?0:num2.charAt(j);
            int sum = (m+n+carry)%10;
            sb.append(sum);
            carry = (m+n+carry)/10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        multiply multiply = new multiply();
        multiply.multiplyI("123", "456");
    }
}
