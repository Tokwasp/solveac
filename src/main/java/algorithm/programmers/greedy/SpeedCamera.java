package algorithm.programmers.greedy;

import java.util.Arrays;

public class SpeedCamera {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[0] - b[0]);
        int lastCamera = -30001;
        int count = 0;

        for (int i = 0; i < routes.length; i++) {
            int start = routes[i][0];
            int end = routes[i][1];

            if (lastCamera < start) {
                count++;
                lastCamera = end;
            }

            if (lastCamera > end) {
                lastCamera = end;
            }
        }
        return count;
    }
}
