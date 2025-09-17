package algorithm.programmers.datastructure.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostReceivePresent {
    public int solution(String[] friends, String[] gifts) {
        Map<String, List<String>> giveMap = new HashMap<>();
        Map<String, List<String>> reciveMap = new HashMap<>();
        Map<String, Integer> presentMap = new HashMap<>();

        // map 초기화
        for (int i = 0; i < friends.length; i++) {
            String friend = friends[i];
            giveMap.put(friend, new ArrayList<>());
            reciveMap.put(friend, new ArrayList<>());
        }

        // gift 정리
        for (int i = 0; i < gifts.length; i++) {
            String[] input = gifts[i].split(" ");
            String give = input[0];
            String recive = input[1];

            giveMap.get(give).add(recive);
            reciveMap.get(recive).add(give);
        }

        // 문제 진짜 너무하네
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String person1 = friends[i];
                String person2 = friends[j];

                int person1Give = 0;
                int person2Give = 0;

                List<String> person1Gives = giveMap.get(person1);
                List<String> person2Gives = giveMap.get(person2);

                for (String give : person1Gives) {
                    if (give.equals(person2)) {
                        person1Give++;
                    }
                }

                for (String give : person2Gives) {
                    if (give.equals(person1)) {
                        person2Give++;
                    }
                }

                if (person1Give < person2Give) {
                    presentMap.put(person2, presentMap.getOrDefault(person2, 0) + 1);
                } else if (person1Give > person2Give) {
                    presentMap.put(person1, presentMap.getOrDefault(person1, 0) + 1);
                } else {
                    int presentJisu1 = person1Gives.size() - reciveMap.get(person1).size();
                    int presentJisu2 = person2Gives.size() - reciveMap.get(person2).size();

                    if (presentJisu1 < presentJisu2) {
                        presentMap.put(person2, presentMap.getOrDefault(person2, 0) + 1);
                    } else if (presentJisu1 > presentJisu2) {
                        presentMap.put(person1, presentMap.getOrDefault(person1, 0) + 1);
                    }
                }
            }
        }

        int max = 0;
        for (Map.Entry<String, Integer> entry : presentMap.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }
}
