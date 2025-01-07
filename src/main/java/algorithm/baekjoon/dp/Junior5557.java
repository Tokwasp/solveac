package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Junior5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N + 1];
        long[][] dp = new long[N + 1][21];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][num[1]] = 1;
        for(int i = 2; i < N; i++){
            for(int j = 0; j <= 20; j++){
                if(dp[i-1][j] != 0){
                    long pre = dp[i-1][j];

                    if(j + num[i] <= 20){
                        dp[i][j + num[i]] += pre;
                    }

                    if(0 <= j - num[i]){
                        dp[i][j - num[i]] += pre;
                    }
                }
            }
        }
        System.out.print(dp[N-1][num[N]]);
    }
}