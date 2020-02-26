package _5.pointer;

/**
 * 荷兰国旗问题
 * 例如，给定数组：[2, 3, 1, 9, 7, 6, 1, 4, 5]，给定一个值4，那么经过处理原数组可能得一种情况是：[2, 3, 1, 1, 4, 9, 7, 6, 5]，
 * 需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序，返回等于4部分的左右两个下标，即[4, 4]
 */
public class banner {
    //输出荷兰国旗问题后的排序结果，时间复杂度为O(n)，空间复杂度为O(1)
    public static void main(String[] args) {
        int[] A = {2,0,2,0,0,2,1,1,0,2,1,0,1,2,0,1,2,0,1,0,2,1,0,2,0,1,2,0,1,2,0,2,1,0};
        getHollandSort(A);
    }

    public static void getHollandSort(int[] A){
        //begin，end双指针
        int begin = 0;
        int current = 0;
        int end = A.length - 1;
        while(current <= end){//current滑动指针，当滑动为0的时候，与begin交换
            if(A[current] == 0) {
                swap(A, current, begin);
                current++;
                begin++;
            }else if(A[current] == 1){
                current ++;
            }else if(A[current] == 2){//当滑动到2的时候，与end交换
                swap(A, current, end);
                end--;
            }
        }
        for (int i=0;i<A.length;i++){
            System.out.print(A[i]+ " ");
        }
    }
    public static void swap(int[] A,int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


    //面试题21. 调整数组顺序使奇数位于偶数前面
    public int[] exchange(int[] nums) {
        if(nums == null || nums.length == 0) return nums;
        int l = 0, r = nums.length - 1, i = 0;
        while(l < r){
            if(nums[i] % 2 == 1) swap(nums, l++, i++);
            else swap(nums, i, r--);
        }
        return nums;
    }
}
