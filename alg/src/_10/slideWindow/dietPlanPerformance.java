package _10.slideWindow;

/**
 * 健身计划评估
 * 以k为滑动窗口，当 T < lower时，减一，当 T > lower时，加一
 *
 * calories = [6,5,0,0], k = 2, lower = 1, upper = 5   0
 * calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, 总分 = 0.
 */
public class dietPlanPerformance {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int scores = 0;
        int tmp = 0;
        if (k > calories.length) return scores;
        for (int i = 0; i < k; i++) tmp += calories[i];//先滑动一个窗口，判断
        if (tmp < lower) scores--;
        else if (tmp > upper) scores++;
        for (int i = k; i < calories.length; i++) {//然后更新窗口中的值，进而进行判断
            tmp += calories[i];
            tmp -= calories[i - k];
            if (tmp < lower) scores--;
            else if (tmp > upper) scores++;
        }
        return scores;
    }
}
