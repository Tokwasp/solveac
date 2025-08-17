package algorithm.programmers.greedy;

import java.util.Arrays;

public class InterceptSystem {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[0] - b[0]);

        int count = 1;
        int end = targets[0][1];

        for (int row = 1; row < targets.length; row++) {
            int start = targets[row][0];
            int currentEnd = targets[row][1];

            if (end <= start) {
                count++;
                end = currentEnd;
            } else {
                end = Math.min(end, currentEnd);
            }
        }

        return count;
    }
}
