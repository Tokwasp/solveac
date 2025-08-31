package algorithm.programmers.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SeesawPartner {
    public long solution(int[] weights) {
        Map<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights);

        long count = 0;
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];

            if (map.containsKey(weight * 1.0 / 2)) count += map.get(weight * 1.0 / 2);
            if (map.containsKey(weight * 2.0 / 3)) count += map.get(weight * 2.0 / 3);
            if (map.containsKey(weight * 3.0 / 4)) count += map.get(weight * 3.0 / 4);
            if (map.containsKey(weight * 1.0)) count += map.get(weight * 1.0);

            map.put(weight * 1.0, map.getOrDefault(weight * 1.0, 0) + 1);
        }
        return count;
    }
}
