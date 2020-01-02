package _9.search;

/**
 * 744. 寻找比目标字母大的最小字母 - 二分查找
 */
public class nextGreatestLetter {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, len = letters.length, h = len - 1;
        while(l <= h){
            int mid = l + (h-l)/2;
            if(letters[mid] <= target) l = mid + 1;
            else h = mid - 1;
        }
        return l < len ? letters[l]:letters[0];
    }
}
