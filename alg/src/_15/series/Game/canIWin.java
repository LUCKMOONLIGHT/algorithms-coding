package _15.series.Game;

import java.util.HashMap;
import java.util.Map;

//464. 我能赢吗  https://leetcode-cn.com/problems/can-i-win/
//在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
//两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。玩家不能重复使用整数
//给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
public class canIWin {
    //记忆化搜索
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //第一种特殊情况，第一个人选一次即可到达预期值
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            sum += i;
        }
        //第二种特殊情况，所有元素的和都小于预期值，则永远无法赢
        if (sum < desiredTotal) {
            return false;
        }
        Map<Integer, Boolean> map = new HashMap<>(); //表示选择的数与能否赢
        return canWin(maxChoosableInteger, desiredTotal, 0, map);
    }

    public boolean canWin(int maxChoosableInteger, int desiredTotal, int used, Map<Integer, Boolean> map) {
        if (map.containsKey(used)) {
            return map.get(used);//如果之前搜索过
        }
        //穷举当前可选的元素
        for (int i = 1; i <= maxChoosableInteger; i++) {
            // 判断该数字是否已经被选
            if ((used & (1 << i)) == 0) { ////第i位表示选择[1,2,3, maxChoosableInteger]选择i + 1这个值,这个值没有使用过
                // used | (1 << i) 状态转移，表示记录该数字已经被选
                //desiredTotal <= i是代表已经达到预期值
                //!canWin(maxChoosableInteger, desiredTotal - i, used | (1 << i), map))表示的是对方选择输了
                if (desiredTotal <= i || !canWin(maxChoosableInteger, desiredTotal - i, used | (1 << i), map)) {
                    map.put(used, true);
                    return true;
                }
            }
        }
        map.put(used, false);
        return false;
    }
}
