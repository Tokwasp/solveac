package algorithm.math;

import java.io.*;

public class RgbDistance1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] colorAndPrice = new int[n][3];
        int[][] dp = new int[n][3];
        for(int i=0; i<n; i++){
            String[] input = br.readLine().split(" ");

            colorAndPrice[i][0] = Integer.parseInt(input[0]);
            colorAndPrice[i][1] = Integer.parseInt(input[1]);
            colorAndPrice[i][2] = Integer.parseInt(input[2]);

            dp[0][0] = colorAndPrice[0][0];
            dp[0][1] = colorAndPrice[0][1];
            dp[0][2] = colorAndPrice[0][2];

            if(i > 0){
                dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + colorAndPrice[i][0];
                dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + colorAndPrice[i][1];
                dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + colorAndPrice[i][2];
            }
        }
        int min = Math.min(dp[n-1][0],dp[n-1][1]);
        min = Math.min(min,dp[n-1][2]);
        System.out.println(min);
    }
}
