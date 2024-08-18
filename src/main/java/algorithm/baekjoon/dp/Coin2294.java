package algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Coin2294 {
    static final int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > 10000) continue;

            for(int j = num; j <= K; j++){
                dp[j] = Math.min(dp[j],dp[j-num] + 1);
            }
        }
        if(dp[K] == INF) System.out.println(-1);
        else System.out.println(dp[K]);
    }
}
