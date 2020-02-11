package _0.array;

//14. 最长公共前缀
//用第一个字符串取匹配第二个字符串，然后截取公共前缀，用剩余的前缀取匹配后面的字符串
public class longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        String ans = strs[0];
        for(int i =1;i<strs.length;i++) {
            int j=0;
            while(j<ans.length() && j<strs[i].length() && strs[i].charAt(j) == ans.charAt(j)) j++;
            ans = ans.substring(0, j);
            if(ans.equals(""))
                return ans;
        }
        return ans;
    }
}
