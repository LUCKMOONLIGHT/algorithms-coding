package _4.dp;

import java.util.Arrays;

/**
 *  分割回文串 II - 动态规划
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。返回符合要求的最少分割次数。
 *
 * 思路：
 * 1.动态规划判断回文，保留最小分割数
 * 表isPal[i][j]：示从i到j能否组成回文字符串
 * dp[i]表示[0,i]的最小分割次数
 */
public class partitionII {
    public int minCut(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = -1;

        for(int i = 1; i <= n; i++){ //从1开始作为末尾
            f[i] = i; //赋初始值
            for(int j = 0; j < i; j++){ //从0开始判断子串
                if(isPal(s.substring(j, i))){ //假如是回文的话
                    f[i] = Math.min(f[j] + 1, f[i]);//总长字符串的分割数等于最小子串分割数+1
                }
            }
        }
        return f[n];
    }

    boolean isPal(String s){
        int left = 0, right = s.length() - 1;
        while(left < right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
}
