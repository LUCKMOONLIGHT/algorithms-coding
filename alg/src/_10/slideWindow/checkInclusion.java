package _10.slideWindow;

/**
 * 567. 字符串的排列
 * 写一个函数来判断 s2 是否包含 s1 的排列
 * s1 = "ab" s2 = "eidbaooo"  True
 */
public class checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if(s1 == null || s2 == null || n > m) return false;
        int[] target = new int[256];
        int[] source = new int[256];
        for(char c:s1.toCharArray()) target[c]++;
        int left = 0, right = 0;
        while(right < m){
            if(!valid(source, target)) source[s2.charAt(right++)]++;
            while(valid(source, target) || (right - left) > n){
                if(valid(source, target)) return true;
                source[s2.charAt(left++)]--;
            }
        }
        return false;
    }
    public boolean valid(int[] source, int[] target){
        for(int i=0;i<source.length;i++){
            if(source[i] != target[i]) return false;
        }
        return true;
    }
}
