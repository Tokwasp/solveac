package algorithm.programmers.dp;

import java.util.Arrays;

public class Hopscotch {
    int solution(int[][] lands) {
        int[][] dp = new int[lands.length][4];

        // 초기화
        for (int i = 0; i < 4; i++) {
            dp[0][i] = lands[0][i];
        }

        for (int row = 1; row < lands.length; row++) {
            int[] land = lands[row];

            for (int col = 0; col < land.length; col++) {
                int num = land[col];
                int max = 0;

                for (int i = 0; i < dp[0].length; i++) {
                    if (col == i) continue;
                    max = Math.max(max, dp[row - 1][i]);
                }
                dp[row][col] = max + num;
            }
        }
        return Arrays.stream(dp[lands.length - 1]).max().getAsInt();
    }
}
