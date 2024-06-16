package algorithm.baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Zoo1309 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] dp = new int[n+1][3];
        Arrays.fill(dp[1],1);

        for(int i=2; i<dp.length; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }
        System.out.print((Arrays.stream(dp[n]).sum()) % 9901);
    }
}
