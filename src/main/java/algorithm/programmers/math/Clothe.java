package algorithm.programmers.math;

import java.util.HashMap;
import java.util.Map;

public class Clothe {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothMap = new HashMap<>();

        // 입력 받기
        for (int row = 0; row < clothes.length; row++) {
            String name = clothes[row][0];
            String type = clothes[row][1];

            clothMap.put(type, clothMap.getOrDefault(type, 0) + 1);
        }

        int answer = 1;
        for (int count : clothMap.values()) {
            answer *= (count + 1);
        }
        return answer - 1;
    }
}
