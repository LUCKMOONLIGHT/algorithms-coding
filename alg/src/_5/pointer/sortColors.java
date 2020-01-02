package _5.pointer;

public class sortColors {
    public void getsortColors(int[] colors){
        if (colors == null || colors.length == 0){
            return;
        }
        int left = 0;
        int right = 0;
        int cur = 0;
        while (cur < colors.length){
            if (colors[cur] == 0){
                swap(colors, cur, left);
                left ++;
                cur ++;
            }else if(colors[cur] == 1){
                cur ++;
            }else if (colors[cur] == 2){
                swap(colors, cur, right);
            }
        }

    }
    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
