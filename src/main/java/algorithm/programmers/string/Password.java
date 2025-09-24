package algorithm.programmers.string;

import java.util.HashSet;
import java.util.Set;

public class Password {
    public String solution(String s, String skip, int index) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < skip.length(); i++) {
            set.add((int) skip.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int num = (int) s.charAt(i);
            int remainCount = index;

            while (remainCount != 0) {
                num++;
                if (num > 'z') {
                    num = 'a';
                }

                if (!set.contains(num)) {
                    remainCount--;
                }
            }
            sb.append((char) num);
        }
        return sb.toString();
    }
}
