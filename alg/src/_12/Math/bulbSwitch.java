package _12.Math;

//319. 灯泡开关
//第一轮打开所有灯泡，第二轮每两个灯泡关闭一次，第三轮每三个切换一次灯泡，第i轮每i个灯泡切换一次开关
public class bulbSwitch {
    //1.第i个灯泡的反转次数等于它所有因子（包括1和i）的个数
    //2.只有平方数的因子个数不是成对出现
    //3.问题转换为n以内的平方数数量
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
