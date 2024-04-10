package algorithm.math;

import java.io.*;
import java.util.stream.Stream;

public class Move11048 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[][] arr = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<arr.length; i++){
            int[] numInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<arr[0].length -1; j++){
                arr[i][j+1] = numInput[j];
            }
        }
        for(int i=1; i<arr.length; i++){
            for(int j=1; j<arr[0].length; j++){
                if(dp[i][j] == 0){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + arr[i][j];
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
