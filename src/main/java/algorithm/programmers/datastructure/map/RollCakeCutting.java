package algorithm.programmers.datastructure.map;

import java.util.HashMap;
import java.util.Map;

public class RollCakeCutting {
    public int solution(int[] toppings) {
        Map<Integer, Integer> yourToppingMap = new HashMap<>();

        // input 처리
        for (int i = 0; i < toppings.length; i++) {
            int topping = toppings[i];
            yourToppingMap.put(topping, yourToppingMap.getOrDefault(topping, 0) + 1);
        }

        // 내 케이크
        Map<Integer, Integer> myToppingMap = new HashMap<>();

        int count = 0;
        for (int i = toppings.length - 1; i >= 0; i--) {
            int topping = toppings[i];
            myToppingMap.put(topping, myToppingMap.getOrDefault(topping, 0) + 1);

            // 상대편 토핑 줄이기
            int nextTopping = yourToppingMap.get(topping) - 1;
            if (nextTopping == 0) {
                yourToppingMap.remove(topping);
            } else {
                yourToppingMap.put(topping, nextTopping);
            }

            if (yourToppingMap.size() == myToppingMap.size()) {
                count++;
            }
        }
        return count;
    }
}
