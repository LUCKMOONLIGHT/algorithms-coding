package _6.dfs.DFS.trackback;

import java.util.*;

/**
 * 全排列 - DFS 回溯
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 1.确定第一位的字符
 * 2.剩下的记录继续做全排列
 *
 *输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 *
 */
public class permute {
    public static void main(String[] args){
        permute permute = new permute();
        int[] arr = {1,2,3};
        List<List<Integer>> res = new ArrayList<>();
        res = permute.permute(arr);
        for (List list:res){
            System.out.println(list);
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(nums, 0, nums.length - 1, res);
        return res;
    }

    public void backtrace(int[] nums, int start, int end, List<List<Integer>> res){
        if (start == end){ // 递归终止条件
            List<Integer> tmp = new ArrayList<>();
            for (int num:nums){
                tmp.add(num);
            }
            res.add(tmp);
        }
        for (int i=start;i<=end;i++){ //确定第一位的字符
            swap(nums, i, start); //保证当前i指向的记录出现在第一个位置
            backtrace(nums, start + 1, end, res); //改起始位置start+1，剩下的记录做全排
            swap(nums, i, start); //为了保证每一次的交换不会对下一次的交换产生影响，要重新交换一下位置，也就是复原
        }
    }
    public static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}

/*题目描述：将编号为1~10的10本书排放在书架上，要求编号相邻的书不能放在相邻的位置。
1.先求出全排列
2.去掉不满足条件的情况，check*/
/*
public class BookSort {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        fullSort(arr, 0, arr.length - 1);
        System.out.println(res);
    }

    static int res = 0;

    static void fullSort(int[] arr, int start, int end) {
        // 递归终止条件
        if (start == end) {
            // 求出了全排列的一种情况，然后检查是否满足条件
            if (check(arr))
                res++;
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, start, i);
            fullSort(arr, start + 1, end);
            swap(arr, start, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static boolean check(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) == 1)
                return false;
        }
        return true;
    }

}*/

/*全排列-dfs
public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
        return result;
        }
        permuteDFS(result, new ArrayList<Integer>(), nums);
        return result;
        }

private void permuteDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int[] nums) {
        if (levelList.size() == nums.length) {
        result.add(new ArrayList<>(levelList));
        return;
        }
        for (int i = 0; i < nums.length; i++) {
        if (levelList.contains(nums[i])) {
        continue;
        }
        levelList.add(nums[i]);
        permuteDFS(result, levelList, nums);
        levelList.remove(levelList.size() - 1);
        }
        }*/


/*全排列去重复
- 排序,
- Mark visited. 通过permutation规律查看是否排出了重复结果
- 并且要检查上一层recursive时有没有略过重复element


        boolean[] visited;
public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
        return result;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        permuteUniqueDFS(result, new ArrayList<Integer>(), nums);
        return result;
        }

private void permuteUniqueDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int[] nums) {
        if (levelList.size() == nums.length) {
        result.add(new ArrayList<>(levelList));
        return;
        }
        for (int i = 0; i < nums.length; i++) {
        if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) {
        continue;
        }
        visited[i] = true;
        levelList.add(nums[i]);
        permuteUniqueDFS(result, levelList, nums);
        visited[i] = false;
        levelList.remove(levelList.size() - 1);
        }


        }*/



