package _0.array;

//面试题67. 把字符串转换成整数
//输入: "   -42"
//输出: -42
public class strToInt {
    public int strToInt(String str) {
        char[] s = str.toCharArray();  //转换为数组
        int n = s.length, i = 0;
        while(i < n && s[i] == ' ') i++;  //去除空格
        if(i == n) return 0;
        int flag = 1, res = 0;
        if(s[i] == '+'){   //判断正负
            i++;
        }else if(s[i] == '-'){
            i++;
            flag = -1;
        }
        while(i < n && s[i] >= '0' && s[i] <= '9'){  //迭代数字
            int value = s[i] - '0';
            if(flag > 0){//判断是否溢出
                if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && value > 7)) return Integer.MAX_VALUE;
            }
            else if(flag < 0){
                if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && value > 8)) return Integer.MIN_VALUE;
            }

            res = res * 10 + value; //相加求和
            i++;
        }
        return res * flag;//结果
    }
}
