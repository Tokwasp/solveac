package algorithm.math;

import java.util.Scanner;

public class oneTwoThreeAdd9095 {
    static int[] dp;

    private static int getSolution(int num){
        if(num == 1) return 1;
        if(num == 2) return 2;
        if(num == 3) return 4;
        return getSolution(num-1) + getSolution(num-2) + getSolution(num-3);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        dp = new int[11];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            for(int j=4; j<=n; j++) {
                dp[j] = getSolution(n);
            }
            sb.append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }
}
