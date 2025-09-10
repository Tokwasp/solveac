package algorithm.programmers.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ConvertNumber {

    // dp 풀이
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];

        // 초기화
        Arrays.fill(dp, -1);
        dp[x] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);

        while (!queue.isEmpty()) {
            int num = queue.poll();

            int plusN = num + n;
            int productTwo = num * 2;
            int productThree = num * 3;

            if (plusN <= y && dp[plusN] == -1) {
                dp[plusN] = dp[num] + 1;
                queue.add(plusN);
            }

            if (productTwo <= y && dp[productTwo] == -1) {
                dp[productTwo] = dp[num] + 1;
                queue.add(productTwo);
            }

            if (productThree <= y && dp[productThree] == -1) {
                dp[productThree] = dp[num] + 1;
                queue.add(productThree);
            }
        }
        return dp[y];
    }

    // 2번째 풀이 bfs
    public int solution2(int x, int y, int n) {
        boolean[] visited = new boolean[y + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, 0});
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int num = poll[0];
            int dist = poll[1];

            if (num == y) {
                return dist;
            }

            int nextPlus = num + n;
            int nextProductTwo = num * 2;
            int nextProductThree = num * 3;

            if (nextPlus <= y && !visited[nextPlus]) {
                visited[nextPlus] = true;
                queue.add(new int[]{nextPlus, dist + 1});
            }

            if (nextProductTwo <= y && !visited[nextProductTwo]) {
                visited[nextProductTwo] = true;
                queue.add(new int[]{nextProductTwo, dist + 1});
            }

            if (nextProductThree <= y && !visited[nextProductThree]) {
                visited[nextProductThree] = true;
                queue.add(new int[]{nextProductThree, dist + 1});
            }
        }
        return -1;
    }
}
