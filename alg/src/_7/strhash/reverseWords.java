package _7.strhash;

public class reverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        for(String str:strs){
            sb.append(new StringBuilder(str).reverse().toString()+' ');
        }
        return sb.toString().trim();
    }
}
