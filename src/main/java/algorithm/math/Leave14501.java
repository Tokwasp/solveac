package algorithm.math;

import java.util.Scanner;

public class Leave14501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int day = sc.nextInt();
            int endDay = i + day - 1;
            if (endDay > n) {
                sc.nextInt();
                continue;
            }
            int max = 0;
            for (int j = 1; j <= endDay-day; j++) {
                max = Integer.max(dp[j],max);
            }
            dp[endDay] = Integer.max(dp[endDay],max+sc.nextInt());
        }
        int max = 0;
        for(int i=0; i<dp.length; i++){
            max = Integer.max(dp[i],max);
        }
        System.out.print(max);
    }
}

