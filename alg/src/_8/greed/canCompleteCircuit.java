package _8.greed;

/**
 * 134. 加油站 - 贪心
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明: 
 *
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 */
public class canCompleteCircuit {

    /**
     * 1.判断总路程和总油量，总油量小于总路程，直接返回false
     * 2.否则，必有解。从0出发，考虑油量-路程，小于0表示不能到达下一个节点，故向后递推下一个出发点。
     *
     * 或者
     * 1.同时判断总油量totaltank和当前油量curr_tank O(n) O(1)
     * 2.当当前油量少于0的时候，表示不能到达，从下一个结点开始
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) return -1;
        int n = gas.length;
        int total_gas = 0;
        int curr_gas = 0;
        int station = 0;
        for (int i=0;i<n;i++){
            total_gas += gas[i] - cost[i];
            curr_gas += gas[i] - cost[i];
            if (curr_gas < 0){
                station = i+1;
                curr_gas = 0;
            }
        }
        return total_gas >= 0? station : -1;

    }
}
