package _7.strhash;

//38. 外观数列
//1.     1
//2.     11
//3.     21
//4.     1211
//5.     111221
public class countAndSay {
    public String countAndSay(int n) {
        String s = "1";
        if(n == 1) return s;
        for(int i=1;i<n;i++){
            StringBuilder sb = new StringBuilder();
            char pre = s.charAt(0);
            int cnt = 1;
            for(int j=1;j<s.length();j++){
                char c = s.charAt(j);
                if(c == pre) cnt++;
                else {
                    sb.append(cnt).append(pre);
                    pre = s.charAt(j);
                    cnt = 1;
                }
            }
            sb.append(cnt).append(pre);
            s = sb.toString();
        }
        return s;
    }
}
