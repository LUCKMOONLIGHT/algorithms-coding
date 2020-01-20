package _12.Math;

//365. 水壶问题
//有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
//输入: x = 3, y = 5, z = 4
//输出: True
public class canMeasureWater {
    public boolean canMeasureWater(int x, int y, int z) {
        if(x+y<z)return false;
        if(x==z || y==z || x+y==z) return true;
        return z%gcd(y,x)==0;
        //找到x,y的最大公约数能否z被整除
    }
    public int gcd(int q,int p)
    {
        if(p==0) return q;
        return gcd(p,q%p);
    }
}
