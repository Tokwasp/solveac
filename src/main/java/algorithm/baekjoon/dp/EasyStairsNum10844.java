package algorithm.baekjoon.dp;

import java.io.*;

public class EasyStairsNum10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+1][10];

        for(int i = 1; i < 10; i++) dp[1][i] = 1;

        for(int i = 2; i < dp.length; i++){
            for(int j = 0; j < 10; j++){
                int prior = j - 1;
                int next = j + 1;

                if(prior >= 0) dp[i][j] += dp[i-1][j-1];
                if(next <= 9) dp[i][j] += dp[i-1][j+1];

                dp[i][j] %= 1000000000;
            }
        }

        long sum = 0;
        for(int i = 0; i < 10; i++) sum += dp[N][i];

        System.out.println(sum % 1000000000);
    }
}
