package _5.pointer;

/**
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
        int begin = 0;
        int current = 0;
        int end = A.length - 1;
        while(current <= end){
            if(A[current] == 0) {
                swap(A, current, begin);
                current++;
                begin++;
            }else if(A[current] == 1){
                current ++;
            }else if(A[current] == 2){
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
}
