package algorithm.baekjoon.dp;

import java.io.*;

public class makeOne1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = 0;

        for(int i=2; i<=n; i++){
            if(dp[i] == 0){
                if(i%6==0){
                    dp[i]=Math.min(dp[i/3],Math.min(dp[i/2],dp[i-1]))+1;
                }
                else if(i % 3 == 0){
                    dp[i] = Math.min(dp[i/3],dp[i-1]) + 1;
                }
                else if(i % 2 == 0){
                    dp[i] = Math.min(dp[i/2],dp[i-1]) + 1;
                }
                else{
                    dp[i] = dp[i-1] + 1;
                }
           }
        }
        if(n == 1) System.out.print("0");
        else System.out.print(dp[n]);
    }
}
