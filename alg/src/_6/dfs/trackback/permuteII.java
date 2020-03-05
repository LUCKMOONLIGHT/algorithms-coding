package _6.dfs.trackback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 全排列II去重复 - 回溯 + 减枝  给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * [1,1,2]
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 思路：全排问题去重 - 根据初始提供的nums，for循环分支时去重
 * 1.先从大到小进行排序
 * 1.5 判断当前位置被访问过或者当前位置与前一个被访问的位置相同时，continue，不进行dfs
 * 2.从头开始一次标记mark，然后加入到list中，进行dfs
 * 3.当list长度等于要求长度时，添加到res中result.add(new ArrayList<>(levelList))，返回return
 *
 * 解决有重复元素的序列的排列问题的关键：
 * 当数组中有重复元素的时候，可以先将数组排序，排序以后在递归的过程中可以很容易发现重复的元素。
 * 当发现重复元素的时候，让这一个分支跳过，不再继续搜索，以达到“剪枝”的效果，重复的排列就不会出现在结果集中
 *
 * 1、在开始回溯算法之前，对数组进行一次排序操作，这是上面多次提到的；
 * Arrays.sort(nums)
 * 2、在进入一个新的分支之前，看一看这个数是不是和之前的数一样，如果这个数和之前的数一样，
 * 并且之前的数还未使用过，那接下来如果走这个分支，就会使用到之前那个和当前一样的数，就会发生重复，
 * 此时分支和之前的分支一模一样。（这句话特别关键，可以停下来多看两遍，再看一看上面画的那张图）。
 *  if (visited[i] || (i - 1 >= 0 && visited[i - 1] && nums[i] == nums[i - 1])) continue
 */
public class permuteII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums); //排序的作用是为了让重复的数相邻
        int n = nums.length;
        boolean[] mark = new boolean[n];
        dfs(res,new ArrayList<>(), nums, mark);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] mark){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(mark[i] || ((i - 1 >=0 ) && mark[i-1] && nums[i - 1] == nums[i])){  //判断重复：已经被访问过了 或者 不是第一个数 && 前一个数已经被访问过了 && 前一个数等于后一个数
                continue;
            }
            mark[i] = true;
            list.add(nums[i]);
            dfs(res, list, nums, mark);
            mark[i] = false;
            list.remove(list.size() - 1);
        }
    }


    //面试题38. 字符串的排列
    //s = "abc"  ["abc","acb","bac","bca","cab","cba"]
    public String[] permutation(String s) {
        List<List<Character>> res = new ArrayList<>();
        if(s == null || s.length() == 0) return new String[0];
        char[] chars = s.toCharArray();
        Arrays.sort(chars); //排序的作用是为了让重复的数相邻
        int n = s.length();
        boolean[] mark = new boolean[n];
        dfs(res,new ArrayList<>(), chars, mark);
        String[] arr = new String[res.size()];
        int i = 0;
        for(List<Character> lc:res){
            StringBuilder sb = new StringBuilder();
            for(Character c:lc) sb.append(c);
            arr[i++] = sb.toString();
        }
        return arr;
    }
    public void dfs(List<List<Character>> res, List<Character> list, char[] nums, boolean[] mark){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(mark[i] || ((i - 1 >=0 ) && mark[i-1] && nums[i - 1] == nums[i])){  //判断重复：已经被访问过了 或者 不是第一个数 && 前一个数已经被访问过了 && 前一个数等于后一个数
                continue;
            }
            mark[i] = true;
            list.add(nums[i]);
            dfs(res, list, nums, mark);
            mark[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
