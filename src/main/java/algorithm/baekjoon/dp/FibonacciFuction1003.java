package algorithm.baekjoon.dp;

import java.util.Scanner;

public class FibonacciFuction1003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] zeroDp = new int[41];
        int[] oneDp = new int[41];
        StringBuilder sb = new StringBuilder();

        zeroDp[0] = 1; oneDp[1] = 1;
        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            for(int j=2; j<=n; j++){
                if(zeroDp[j] == 0 && oneDp[j] == 0){
                    zeroDp[j] = zeroDp[j-2] + zeroDp[j-1];
                    oneDp[j] = oneDp[j-2] + oneDp[j-1];
                }
            }
            sb.append(zeroDp[n]).append(" ").append(oneDp[n]).append("\n");
        }
        System.out.print(sb);
    }
}
