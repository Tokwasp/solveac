package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Retire15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        int[] duration = new int[N + 1];
        int[] value = new int[N + 1];

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            duration[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            int period = duration[i] + i - 1;

            if(period <= N){
                dp[period] = Math.max(dp[period], dp[i-1] + value[i]);
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        System.out.println(dp[N]);
    }
}