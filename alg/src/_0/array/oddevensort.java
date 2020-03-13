package _0.array;

//奇偶重排：
// 一个数组有2n个元素，其中有n个奇数，n个偶数，数组无序，写一个算法使得奇数位置放置奇数，偶数位置放置偶数
//a={1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2}
//偶数位置是偶数
//思路：
//1.遍历当前元素，判断当前奇数位置的元素值是否为奇数
//2.如果不为奇数，找下一个元素，遇到奇数位置是奇数值得跳过，偶数位置是偶数值得跳过
//3.交换元素值
public class oddevensort {
    public int[] sort(int[] nums){
        for(int i=0;i<nums.length;i++){
            int j = i+1;
            if(i % 2 == 1){ //奇数位置
                if(nums[i] % 2 == 0){//如果当前元素值为偶数
                    while(j < nums.length){
                        if(j % 2 == 1 && nums[j] % 2 == 0) j++;//偶数位置，值是偶数
                        else if(j % 2 == 0 && nums[j] % 2 == 1) j++; //奇数位置存放的值是奇数
                        else break;
                        }
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }else{//偶数位置
                if(nums[i] % 2 == 1){ //如果当前元素是奇数
                    while(j < nums.length){
                        if(j % 2 == 0 && nums[j] % 2 == 1) j++;//奇数位置，值是奇数，跳过
                        else if(j % 2 == 1 && nums[j] % 2 == 0) j++; //偶数位置存放的是偶数
                        else break;
                        }
                    }
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        return nums;
    }
}
