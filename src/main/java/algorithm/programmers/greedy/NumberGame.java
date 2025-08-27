package algorithm.programmers.greedy;

import java.util.ArrayList;
import java.util.List;

public class NumberGame {
    public int solution(int[] A, int[] B) {
        List<Integer> aTeam = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            aTeam.add(A[i]);
        }

        List<Integer> bTeam = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            bTeam.add(B[i]);
        }

        aTeam.sort((a, b) -> b - a);
        bTeam.sort((a, b) -> b - a);

        int count = 0;
        while (!bTeam.isEmpty()) {
            if (aTeam.get(0) < bTeam.get(0)) {
                aTeam.remove(0);
                bTeam.remove(0);
                count++;
            } else {
                aTeam.remove(0);
                bTeam.remove(bTeam.size() - 1);
            }
        }

        return count;
    }
}
