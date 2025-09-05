package algorithm.programmers.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionValidityPeriod {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int currentDay = convertDayFrom(today);
        Map<String, Integer> contractDayMap = new HashMap<>();

        // 계약 input 
        for (int i = 0; i < terms.length; i++) {
            String[] termArr = terms[i].split(" ");
            String alphabet = termArr[0];
            int month = Integer.parseInt(termArr[1]);
            contractDayMap.put(alphabet, month * 28);
        }

        // 프라이버시
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] privacyArr = privacies[i].split(" ");
            int day = convertDayFrom(privacyArr[0]);

            String alphabet = privacyArr[1];
            int limitDay = day + contractDayMap.get(alphabet);

            if (currentDay >= limitDay) {
                result.add(i + 1);
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int convertDayFrom(String input) {
        String[] times = input.split("\\.");
        int year = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]);
        int day = Integer.parseInt(times[2]);

        return day + month * 28 + year * 12 * 28;
    }
}
