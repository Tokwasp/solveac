package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Coin2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        dp[0] = 1;

        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > K){
                continue;
            }
            for(int j = num; j <= K; j++){
                dp[j] += dp[j - num];
            }
        }
        System.out.println(dp[K]);
    }
}