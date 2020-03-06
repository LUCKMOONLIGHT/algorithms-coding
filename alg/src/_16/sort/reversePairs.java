package _16.sort;

//面试题51. 数组中的逆序对
//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//输入: [7,5,6,4]
//输出: 5
public class reversePairs {
    //思路：归并本质就是2分，在左右数组合并的时候，检测左大于右的次数，即可以组成有序对的个数
    public int reversePairs(int[] nums) {
        MergeSort(nums, 0, nums.length-1);
        return cnt;
    }

    private int cnt;
    //递归二分法
    private void MergeSort(int[] array, int start, int end){
        if(start>=end)return;
        int mid = (start+end)/2;
        MergeSort(array, start, mid);
        MergeSort(array, mid+1, end);
        MergeOne(array, start, mid, end);
    }
    //两个从小到大有序的元素合并
    private void MergeOne(int[] array, int start, int mid, int end){
        int[] temp = new int[end-start+1];
        int k=0,i=start,j=mid+1;
        while(i<=mid && j<= end){
            //如果前面的元素小于后面的不能构成逆序对
            if(array[i] <= array[j])
                temp[k++] = array[i++];
            else{
                //如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                temp[k++] = array[j++];
                cnt = (cnt + (mid-i+1));
            }
        }
        while(i<= mid)
            temp[k++] = array[i++];
        while(j<=end)
            temp[k++] = array[j++];
        for(int m=0; m<k; m++){
            array[start+m] = temp[m];
        }
    }
}
