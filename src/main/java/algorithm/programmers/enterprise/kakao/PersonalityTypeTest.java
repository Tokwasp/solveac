package algorithm.programmers.enterprise.kakao;

import java.util.HashMap;
import java.util.Map;

public class PersonalityTypeTest {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = new HashMap<>();

        // 설문조사
        for (int i = 0; i < survey.length; i++) {
            String sur = survey[i];
            String element1 = String.valueOf(sur.charAt(0));
            String element2 = String.valueOf(sur.charAt(1));

            int choice = choices[i];
            if (choice >= 4) {
                choice = choice % 4;
                map.put(element2, map.getOrDefault(element2, 0) + choice);
            } else {
                choice = 4 - choice;
                map.put(element1, map.getOrDefault(element1, 0) + choice);
            }
        }

        String[][] arr = new String[][]{{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        StringBuilder sb = new StringBuilder();

        // 결과
        for (int i = 0; i < arr.length; i++) {
            String mbti1 = arr[i][0];
            String mbti2 = arr[i][1];

            int count1 = map.getOrDefault(mbti1, 0);
            int count2 = map.getOrDefault(mbti2, 0);

            if (count1 >= count2) {
                sb.append(mbti1);
            } else {
                sb.append(mbti2);
            }
        }
        return sb.toString();
    }
}
