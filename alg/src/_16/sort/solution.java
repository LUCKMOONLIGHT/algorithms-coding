package _16.sort;

class Solution {
    /**
     * 1.稳定的排序  冒泡排序 插入排序 基数排序
     * 2.不稳定的排序 快速排序 选择排序 堆排序
     */

    /**
     * 插入排序  O(N2)       O(1)       稳定
     * 选择排序  O(N2)       O(1)       不稳定
     * 冒泡排序  O(N2)       O(1)       稳定
     * 快速排序  O(N*log2N)  O(log2n)   不稳定
     * 归并排序  O(N*log2N)  O(n)       稳定
     * 基数排序  O(d(r+n))   O(rd+n)    稳定
     * 堆排序   O(N*log2N)   O(1)       不稳定
     *
     *
     */
    public int[] sortArray(int[] nums) {
        if(nums.length <=1)return nums;
        //qSort(nums,0,nums.length-1);
        //selectSort(nums);
        // insertSort(nums);
        // shellSort(nums);
        // bucketSort(nums);
        // countSort(nums);
        // mergeSort(nums,0,nums.length-1);
        heapSort(nums);
        return nums;
    }
    /**
     * 冒泡排序 O(n^2) 稳定
     * 1.从后向前两两比较数据，一直比较到最前面，第二个数小，就交换位置
     */
    void bubbleSort(int[] arr){
        int tmp = 0;
        boolean flag;
        for (int i=0;i<arr.length;i++){
            flag = false;
            for (int j=arr.length - 1;j>i;j--){ //每次前i个元素已经有序，故遍历到i截至
                if (arr[j-1] > arr[j]){
                    tmp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) break; //如果没有发生交换说明已经有序了，故停止遍历
        }
    }

    /**
     * 选择排序 O(n^2) 不稳定
     * 1.第一次遍历在长度为n个数中找到最小的数与第一个数进行交换
     * 2.第二次遍历在长度为n-1个数中找到最小的数与第二个数进行交换
     */
    void selectSort(int[] arr){
        int min;
        for(int i = 0;i<arr.length;i++){//与第几个数进行交换
            min = i;//最小的数
            for(int j = i;j<arr.length;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i) {//交换
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    /**
     *
     * 插入排序 O(n^2) 稳定   如果数据序列基本有序，使用插入排序会更加高效
     * 数列前面部分看为有序，依次将后面的一个无序数列元素插入到前面的有序数列中，初始状态有序数列仅有一个元素，即首元素。
     * 在将无序数列元素插入有序数列的过程中，采用了逆序遍历有序数列，相较于顺序遍历会稍显繁琐，但当数列本身已近排序状态效率会更高。
     *
     * 时间复杂度：O(N2) 　　稳定性：稳定
     * @param array
     */
    public void insertSort(int array[]){
        int length = array.length;
        int temp;
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j>0;j--){
                if(array[j] < array[j-1]){ //交换
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }else{         //不需要交换
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序 - 插入排序的改进版,控制步长。为了减少数据的移动次数，在初始序列较大时取较大的步长，通常取序列长度的一半，此时只有两个元素比较，交换一次；
     * 之后步长依次减半直至步长为1，即为插入排序，由于此时序列已接近有序，故插入元素时数据移动的次数会相对较少，效率得到了提高。
     *
     * 时间复杂度：通常认为是O(N3/2) ，未验证　　稳定性：不稳定
     * @param arr
     */
    void shellSort(int arr[]){
        int d = arr.length >> 1;
        while(d >= 1){
            for(int i = d; i < arr.length; i++){
                for(int j = i - d; j >= 0; j -= d){
                    if(arr[i] < arr[j]){
                        int tmp = arr[i];
                        arr[j + d] = arr[j];
                        arr[j] = tmp;
                    }else break;
                }
            }
            d >>= 1;
        }
    }

    /**
     快速排序 O(N*logN) 分治思想  稳定排序
     先从数列中取出一个数作为key值；
     将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
     对左右两个小数列重复第二步，直至各区间只有1个数。
     **/
    void qSort(int[] arr,int s,int e){
        int l = s, r = e;
        if(l < r){
            int temp = arr[l];
            while(l < r){
                while(l < r && arr[r] >= temp) r--;
                if(l < r) arr[l] = arr[r];
                while(l < r && arr[l] < temp) l++;
                if(l < r) arr[r] = arr[l];
            }
            arr[l] = temp;
            qSort(arr,s,l);
            qSort(arr,l + 1, e);
        }
    }

    /**
     * 归并排序 - 采用了分治和递归的思想，
     * 递归&分治-排序整个数列如同排序两个有序数列，依次执行这个过程直至排序末端的两个元素，
     * 再依次向上层输送排序好的两个子列进行排序直至整个数列有序（类比二叉树的思想，from down to up）。
     *
     * 时间复杂度：O(NlogN) 　　稳定性：稳定
     * @param arr
     */
    void mergeSortInOrder(int[] arr,int bgn,int mid, int end){
        int l = bgn, m = mid +1, e = end;
        int[] arrs = new int[end - bgn + 1];
        int k = 0;
        while(l <= mid && m <= e){
            if(arr[l] < arr[m]){
                arrs[k++] = arr[l++];
            }else{
                arrs[k++] = arr[m++];
            }
        }
        while(l <= mid){
            arrs[k++] = arr[l++];
        }
        while(m <= e){
            arrs[k++] = arr[m++];
        }
        for(int i = 0; i < arrs.length; i++){
            arr[i + bgn] = arrs[i];
        }
    }
    void mergeSort(int[] arr, int bgn, int end)
    {
        if(bgn >= end){
            return;
        }
        int mid = (bgn + end) >> 1;
        mergeSort(arr,bgn,mid);
        mergeSort(arr,mid + 1, end);
        mergeSortInOrder(arr,bgn,mid,end);
    }

    /**
     * 堆排序 - 堆排序的思想借助于二叉堆中的最大堆得以实现。
     * 堆的排序：1.将待排序数列抽象为二叉树，并构造出最大堆；
     * 2.依次将最大元素（即根节点元素）与待排序数列的最后一个元素交换（即二叉树最深层最右边的叶子结点元素）；
     * 3.每次遍历，刷新最后一个元素的位置（自减1），直至其与首元素相交，即完成排序。
     *
     * 时间复杂度：O(NlogN) 　　稳定性：不稳定
     *
     * 堆：
     * 1.小根堆 父节点小于子节点
     * 2.大根堆  父结点大于子节点
     * 3.左右子节点不比较大小
     *
     * 1.堆的插入，新元素每次都插入到堆的末尾，然后一次与父节点进行比较，相同就交换
     * 2.peek()取的是堆顶元素
     * 3.堆的删除poll()取的是堆顶元素，然后将最后一个数据的值赋给根结点，
     * 然后再从根结点开始进行一次从上向下的调整。
     * 调整时先在左右子结点中找最小的，如果父结点比这个最小的子结点还小说明不需要调整了，
     * 反之将父结点和它交换后再考虑后面的结点。相当于根结点数据的“下沉”过程
     * 4.排序：大根堆
     *     1.建堆：
     *     2.交换：
     *
     * @param nums
     */

    void heapSort(int[] nums) {
        int size = nums.length;
        //从size/2-1数组开始建堆，直到第一个元素(角标为0)，最大值会在堆首
        for (int i = size/2-1; i >=0; i--) {
            adjust(nums, size, i);
        }
        //交换首尾结点，然后递归建堆，每次建堆交换后都可以排除一位元素
        for (int i = size - 1; i >= 1; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            adjust(nums, i, 0);
        }
    }
    //建堆
    void adjust(int[] nums, int len, int index) {
        int l = 2 * index + 1; //左孩子
        int r = 2 * index + 2; //右孩子
        int maxIndex = index; //从结点index开始调整
        if (l<len&&nums[l]>nums[maxIndex])maxIndex = l; //确定最大值结点
        if (r<len&&nums[r]>nums[maxIndex])maxIndex = r;
        if (maxIndex != index) { //大根堆：当左右孩子结点的值大于该结点时，进行交换，继续递归判断
            int temp = nums[maxIndex];
            nums[maxIndex] = nums[index];
            nums[index] = temp;
            adjust(nums, len, maxIndex);
        }
    }

    /**
     * 桶排序 - 实现线性排序，但当元素间值得大小有较大差距时会带来内存空间的较大浪费。首先，找出待排序列中得最大元素max，申请内存大小为max + 1的桶（数组）并初始化为0；然后，遍历排序数列，并依次将每个元素作为下标的桶元素值自增1；
     * 最后，遍历桶元素，并依次将值非0的元素下标值载入排序数列（桶元素>1表明有值大小相等的元素，此时依次将他们载入排序数列），遍历完成，排序数列便为有序数列。
     *
     * 时间复杂度：O(x*N) 　　稳定性：稳定
     * @param arr
     */
    void bucketSort(int[] arr){
        int[] bk = new int[50000 * 2 + 1];
        for(int i = 0; i < arr.length; i++){
            bk[arr[i] + 50000] += 1;
        }
        int ar = 0;
        for(int i = 0; i < bk.length; i++){
            for(int j = bk[i]; j > 0; j--){
                arr[ar++] = i - 50000;
            }
        }
    }
    /**
     * 基数排序 - 桶排序的改进版，桶的大小固定为10，减少了内存空间的开销。首先，找出待排序列中得最大元素max，并依次按max的低位到高位对所有元素排序；
     * 桶元素10个元素的大小即为待排序数列元素对应数值为相等元素的个数，即每次遍历待排序数列，桶将其按对应数值位大小分为了10个层级，桶内元素值得和为待排序数列元素个数。
     * @param arr
     */
    void countSort(int[] arr){
        int[] bk = new int[19];
        Integer max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(max < Math.abs(arr[i])) max = arr[i];
        }
        if(max < 0) max = -max;
        max = max.toString().length();
        int [][] bd = new int[19][arr.length];
        for(int k = 0; k < max; k++) {
            for (int i = 0; i < arr.length; i++) {
                int value = (int)(arr[i] / (Math.pow(10,k)) % 10);
                bd[value+9][bk[value+9]++] = arr[i];
            }
            int fl = 0;
            for(int l = 0; l < 19; l++){
                if(bk[l] != 0){
                    for(int s = 0; s < bk[l]; s++){
                        arr[fl++] = bd[l][s];
                    }
                }
            }
            bk = new int[19];
            fl = 0;
        }
    }




}
