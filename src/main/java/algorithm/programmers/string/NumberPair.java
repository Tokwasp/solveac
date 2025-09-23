package algorithm.programmers.string;

import java.util.*;

public class NumberPair {
    public String solution(String X, String Y) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < X.length(); i++) {
            char ch = X.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < Y.length(); i++) {
            char ch = Y.charAt(i);

            if (map.containsKey(ch)) {
                int next = map.get(ch) - 1;

                if (next == 0) {
                    map.remove(ch);
                } else {
                    map.put(ch, next);
                }
                list.add(ch - '0');
            }
        }

        list.sort(Comparator.reverseOrder());
        if (list.size() == 0) {
            return "-1";
        }

        if (list.get(0) == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num);
        }
        return sb.toString();
    }
}
