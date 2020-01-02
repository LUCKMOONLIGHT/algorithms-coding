package _16.sort;

import java.util.Arrays;

public class radixSort {
    private static void radixSort(int[] array,int d) {
        int n=1;//代表位数对应的数：1,10,100...
        int k=0;//保存每一位排序后的结果用于下一位的排序输入
        int length=array.length;
        int[][] bucket=new int[10][length];//初始化0-9共10个桶，每个桶里面的长度位array的最大长度
        int[] order=new int[10];//桶计数器：用于保存每个桶里有多少个数字
        while(n<d) //迭代每一位数
        {
            for(int num:array) //将数组array里的每个数字放在相应的桶里
            {
                int digit=(num/n)%10;
                bucket[digit][order[digit]]=num;
                order[digit]++; //同一个桶里的数量
            }
            for(int i=0;i<10;i++)//迭代每一个桶，将桶里的数字覆盖到原数组中
            {
                if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for(int j=0;j<order[i];j++)
                    {
                        array[k]=bucket[i][j];
                        k++;
                    }
                }
                order[i]=0;//将桶里计数器置0，用于下一次位排序
            }
            n*=10;
            k=0;//将k置0，用于下一轮保存位排序结果
        }
    }
    public static void main(String[] args)
    {
        int[] A=new int[]{73,29, 93, 43, 55, 14, 28, 65, 39, 81, 16, 28, 99, 36, 72, 46};
        radixSort(A, 100);
        System.out.println(Arrays.toString(A));
    }
}
