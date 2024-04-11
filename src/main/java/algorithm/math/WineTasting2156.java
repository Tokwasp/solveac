package algorithm.math;

import java.io.*;

public class WineTasting2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][3];

        int max = Integer.MIN_VALUE;
        for(int i=1; i<dp.length; i++){
            int num = Integer.parseInt(br.readLine());
            if(i == 1) {dp[1][0] = num; dp[1][1] = num;}
            if(i > 1){
                dp[i][0] = dp[i-2][2] + num;
                dp[i][1] = Math.max(dp[i-2][2],dp[i-1][0]) + num;
            }
            max = Math.max(Math.max(dp[i][0],dp[i][1]),max);
            dp[i][2] = max;
        }
        System.out.print(max);
    }
}
