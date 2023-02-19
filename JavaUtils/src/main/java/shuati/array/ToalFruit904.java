package shuati.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mltang
 * @version 2023/2/19 8:31
 * @since JDK8
 */
public class ToalFruit904 {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        totalFruit(test);
    }
}

