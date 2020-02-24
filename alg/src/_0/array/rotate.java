package _0.array;


//48. 旋转图像
//给定一个 n × n 的二维矩阵表示一个图像。
//将图像顺时针旋转 90 度。
public class rotate {

    //方法 1 ：转置加翻转
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    //在单次循环中旋转 4 个矩形
    public void rotateI(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }



    /**
     * 189. 旋转数组
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 翻转
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void rotate_2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }


    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    //面试题11. 旋转数组的最小数字
    //[3,4,5,1,2] 1    [2,2,2,0,1] 0
    //思路；1.二分法，mid节点与r节点比较，判断为递增的区间，最小数字在左边，为递减的区间，最小数字在右边，相同大小，右指针减小，返回左指针位置
    public int minArray(int[] numbers) {
        if(numbers.length == 1) return numbers[0];
        int l = 0, r = numbers.length - 1;
        while(l < r){
            int mid = l+(r-l)/2;
            if(numbers[mid] < numbers[r]) r = mid;
            else if(numbers[mid] > numbers[r]) l = mid + 1;
            else r--;
        }
        return numbers[l];
    }

}
