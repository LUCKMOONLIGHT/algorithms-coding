package _0.array;

import java.util.ArrayList;
import java.util.List;

//面试题62. 圆圈中最后剩下的数字
//0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
//约瑟夫环问题
public class lastRemaining {
    //1.用链表模拟这个游戏
    //将[0,n]依次存储在链表中
    //通过举例可以得出第一次删除的数字下标为(m-1)%n记为c，之后每一次删除的数字下标均为(c+m-1)%list.size()
    public int lastRemaining(int n, int m) {
        if(m == 0 || n == 0) return -1;  //判空
        List<Integer> list = new ArrayList<>();//环
        for(int i=0;i<n;i++) list.add(i); //0-n-1添加到环中
        int k = (m-1)%n; //取第k位置
        while(list.size() != 1){//当环中只剩下一人的时候
            list.remove(k); //移除第k个位置的人
            k = (k+m-1)%list.size();  //之前的位置k加上第m个数-1，取余
        }
        return list.get(0);
    }



    //2.迭代
    //f(n,m)=（f(n−1,m)+m)modn，且f(1,m)=0
    //代码中可以采用迭代或者递归的方法实现该递归公式。时间复杂度为O(n)，空间复杂度为O(1)
    public int lastRemainingII(int n, int m) {
        int flag = 0;
        for (int i = 2; i <= n; i++) {
            flag = (flag + m) % i;
            //动态规划的思想，将f(n,m)替换成flag存储
        }
        return flag;
    }

    //0 1 2 3 4    3
    //2 - 0 1 3 4
    //0 - 1 3 4
    //4 - 1 3
    //1 - 3

    //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
    public static void main(String[] args) {
        lastRemaining lastRemaining  = new lastRemaining();
        int res = lastRemaining.lastRemaining(10, 17);
        System.out.println(res);
    }

}
