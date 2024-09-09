package algorithm.baekjoon.dp;

import java.io.*;
import java.util.stream.Stream;

public class Hello1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] blood = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] happiness = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[100];
        for(int i = 0; i < N; i++){
            for(int j = 99; j >= 0; j--){
                if(j >= blood[i]){
                    dp[j] = Math.max(dp[j],dp[j-blood[i]] + happiness[i]);
                }
            }
        }
        System.out.println(dp[99]);
    }
}
