package _0.array;

//238. 除自身以外数组的乘积
//输入: [1,2,3,4]
//输出: [24,12,8,6]
//请不要使用除法，且在 O(n) 时间复杂度内完成此题。常数空间复杂度，除了输出数组以外
public class productExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k=1;
        for(int i=0;i<nums.length;i++){
            res[i] = k;
            k = k * nums[i];
        }
        k = 1;
        for(int i=nums.length - 1;i>=0;i--){
            res[i] = res[i] * k;
            k = k * nums[i];
        }
        return res;
    }

}
