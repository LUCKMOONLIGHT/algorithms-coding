package _0.array;

//将a、b的值互换，不使用第三个变量
public class swap {
    public void swap(int a, int b){
        a = a + b;
        b = a - b;
        a = a - b;
    }
}
