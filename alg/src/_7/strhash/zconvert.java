package _7.strhash;

import java.util.ArrayList;
import java.util.List;

//6. Z 字形变换   И字形变换
//L   C   I   R
//E T O E S I I G
//E   D   H   N
public class zconvert {
    //1.将所有的字符保存至List中，设定保存的位置i
    //2.从所有list中拼接字符串，返回结果
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }

}
