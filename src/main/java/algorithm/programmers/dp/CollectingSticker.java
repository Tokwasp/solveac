package algorithm.programmers.dp;

public class CollectingSticker {
    public int solution(int[] stickers) {
        int max = 0;
        int n = stickers.length;

        if (n == 1) {
            return stickers[0];
        }

        for (int firstSelect = 0; firstSelect <= 1; firstSelect++) {
            int[][] dp = new int[n][2];
            if (firstSelect == 1) {
                dp[0][0] = stickers[0];
            }

            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][1] + stickers[i];
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            }

            if (firstSelect == 1) {
                dp[n - 1][0] -= stickers[n - 1];
            }

            int dpMax = Math.max(dp[n - 1][0], dp[n - 1][1]);
            max = Math.max(max, dpMax);
        }
        return max;
    }
}
