package algorithm.baekjoon.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class App7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bytes = new int[N + 1];
        int[] costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            bytes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int sumCost = Arrays.stream(costs).sum();
        int[][] dp = new int[N + 1][sumCost + 1];

        for (int i = 1; i <= N; i++) {
            int currentCost = costs[i];
            int currentByte = bytes[i];

            for (int j = 0; j <= sumCost; j++) {
                if (j >= currentCost) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - currentCost] + currentByte);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j <= sumCost; j++) {
            if (dp[N][j] >= M) {
                minCost = j;
                break;
            }
        }
        System.out.println(minCost);
    }
}