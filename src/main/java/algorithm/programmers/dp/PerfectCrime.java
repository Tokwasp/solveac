package algorithm.programmers.dp;

import java.util.Arrays;

public class PerfectCrime {
    private static final int INF = 100_000_000;

    public int solution(int[][] infos, int aMax, int bMax) {
        int n = infos.length;
        int[][] dp = new int[n + 1][bMax];

        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int b = 0; b < bMax; b++) {
                if (dp[i][b] == INF) continue;
                int aTrace = infos[i][0];
                int bTrace = infos[i][1];

                // A가 훔칠 경우
                if (dp[i][b] + aTrace < aMax) {
                    dp[i + 1][b] = Math.min(dp[i + 1][b], dp[i][b] + aTrace);
                }
                // B가 훔칠 경우
                if (b + bTrace < bMax) {
                    dp[i + 1][b + bTrace] = Math.min(dp[i + 1][b + bTrace], dp[i][b]);
                }
            }
        }

        int ans = INF;
        for (int b = 0; b < bMax; b++) {
            ans = Math.min(ans, dp[n][b]);
        }
        return ans == INF ? -1 : ans;
    }
}
