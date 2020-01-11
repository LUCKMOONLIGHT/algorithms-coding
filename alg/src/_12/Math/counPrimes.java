package _12.Math;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算质数
 * 统计所有小于非负整数 n 的质数的数量。
 * 质数=素数：1不是质数。质数又称素数。一个大于1的自然数，除了1和它自身外，不能被其他自然数整除的数叫做质数
 *
 * 1.排除所有不是素数的；素数的倍数都不是素数
 */
public class counPrimes {
    public int counPrimes(int n){
        Boolean[] bool = new Boolean[n];
        Arrays.fill(bool, true);//所有标记位素数
        for (int i=2;i*i < n;i++){
            if (bool[i]){ //素数的倍数都不是素数
                for(int j=i*i;j<n;j+=i){
                    bool[j] = false;
                }
            }
        }
        int cnt=0;
        for (int i=2;i<n;i++){
            if (bool[i]) cnt++;
        }
        return cnt;
    }

    //找到n以内的质数：只能被1和自身整除的数
    public List<Integer> findPrime2(int n){
        List<Integer> prime = new ArrayList<>();
        prime.add(2);
        for (int i=3;i<=n;i++){
            int temp = (int) Math.sqrt(i) + 1;
            for (int j=2;j<=temp;j++){
                if (i % j == 0) break; //如果能被1和自身外的数整除的话
                if (j == temp) prime.add(i);
            }
        }
        return prime;

    }
}
