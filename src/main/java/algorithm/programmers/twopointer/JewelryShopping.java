package algorithm.programmers.twopointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JewelryShopping {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        int[] result = new int[2];
        int targetSize = set.size();
        int minLength = Integer.MAX_VALUE;

        Map<String, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;

        while (end <= gems.length - 1) {
            String gem = gems[end];
            map.put(gem, map.getOrDefault(gem, 0) + 1);
            end++;

            while (map.size() == targetSize) {
                int length = end - start + 1;
                if (length < minLength) {
                    result[0] = start + 1;
                    result[1] = end;
                    minLength = length;
                }

                String startGem = gems[start];
                int next = map.get(startGem) - 1;

                if (next == 0) {
                    map.remove(startGem);
                } else {
                    map.put(startGem, next);
                }
                start++;
            }
        }
        return result;
    }
}
