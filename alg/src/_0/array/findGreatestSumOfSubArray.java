package _0.array;

//连续子数组的最大和
//思路：1.临时子数组的最大和:要么当前元素最大，要么加上当前元素和最大
//      2.保存全局子数组的最大和
//子数组要么是加上当前元素，要么就是从当前原始开始，求最大值arr[i] > curSum + arr[i]
public class findGreatestSumOfSubArray {
    public static int findGreatestSumOfSubArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("Array must contain an element");
        }
        int curSum = 0;
        int maxSum = arr[0];
        for (int i =0;i<arr.length;i++) {
            curSum = (arr[i] > curSum + arr[i]) ? arr[i] : curSum + arr[i];//1---------
            maxSum = (maxSum > curSum) ? maxSum : curSum;//2---------
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(findGreatestSumOfSubArray(data));
    }
}
