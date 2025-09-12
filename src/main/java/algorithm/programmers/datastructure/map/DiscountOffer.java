package algorithm.programmers.datastructure.map;

import java.util.HashMap;
import java.util.Map;

public class DiscountOffer {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            indexMap.put(want[i], i);
        }

        // 개수 카운팅
        int[] countArr = new int[want.length];

        // 초기 10개 값 등록
        for (int i = 0; i < 10; i++) {
            String eat = discount[i];

            if (indexMap.containsKey(eat)) {
                int index = indexMap.get(eat);
                countArr[index]++;
            }
        }

        // 처음 10개가 맞다면 카운트 + 1
        int count = 0;
        if (isMatch(number, countArr)) {
            count++;
        }

        // 투포인터
        int start = 0;
        int end = 10;
        while (end < discount.length) {
            String startEat = discount[start];
            String endEat = discount[end];

            // 처음건 빼야함
            if (indexMap.containsKey(startEat)) {
                int index = indexMap.get(startEat);
                countArr[index]--;
            }

            // 끝에건 넣어야함
            if (indexMap.containsKey(endEat)) {
                int index = indexMap.get(endEat);
                countArr[index]++;
            }

            if (isMatch(number, countArr)) {
                count++;
            }

            start++;
            end++;
        }

        return count;
    }

    private static boolean isMatch(int[] arr, int[] compareArr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != compareArr[i]) {
                return false;
            }
        }
        return true;
    }
}
