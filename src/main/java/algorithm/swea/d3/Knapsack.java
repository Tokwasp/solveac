package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

public class Knapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            int[] NandK = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = NandK[0];
            int K = NandK[1];

            int[] size = new int[N + 1];
            int[] price = new int[N + 1];

            int[][] dp = new int[N + 1][K + 1];

            for(int i = 1; i <= N; i++) {
                int[] boopieAndPrice = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                size[i] = boopieAndPrice[0];
                price[i] = boopieAndPrice[1];
                dp[i][size[i]] = price[i];
            }

            for (int i = 1; i < dp.length; i++) {
                for(int j = 1; j < dp[0].length; j++) {
                    if(j < size[i]) {
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - size[i]] + price[i]);
                    }
                }
            }
            String st = "#" + t + " " + dp[N][K];
            System.out.println(st);
        }
    }
}
