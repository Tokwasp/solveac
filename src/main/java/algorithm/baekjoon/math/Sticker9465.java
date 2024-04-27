package algorithm.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Sticker9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[2][n];
        for(int i=0; i<arr.length; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<arr[0].length; j++){
                arr[i][j] = input[j];
            }
        }

        int[][] dp = new int[3][n];
        dp[2][0] = arr[1][0]; dp[1][0] = arr[0][0];
        for(int i=1; i<dp[0].length; i++){
            for(int j=2; j>0; j--){
                if(j == 2) dp[j][i] = Math.max(dp[j-1][i-1],dp[j-2][i-1]);
                if(j == 1) dp[j][i] = Math.max(dp[j-1][i-1],dp[j-2][i-1]);
            }
        }
    }
}
