package algorithm.programmers.twopointer;

import java.util.ArrayList;
import java.util.List;

public class SubarraySum {
    public int[] solution(int[] sequence, int targetNum) {
        List<int[]> answers = new ArrayList<>();

        int start = 0;
        int end = 0;
        int total = sequence[0];

        while (end < sequence.length) {
            if (total < targetNum) {
                if (++end < sequence.length) {
                    total += sequence[end];
                }
            } else if (total > targetNum) {
                total -= sequence[start++];
            } else {
                answers.add(new int[]{start, end});
                total -= sequence[start++];
            }
        }

        answers.sort((a, b) -> {
            int aLength = a[1] - a[0];
            int bLength = b[1] - b[0];
            if (aLength != bLength) return aLength - bLength;
            return a[0] - b[0];
        });

        return answers.get(0);
    }
}
