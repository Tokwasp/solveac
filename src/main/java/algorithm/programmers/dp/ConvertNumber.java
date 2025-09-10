package algorithm.programmers.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ConvertNumber {
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
}
