package algorithm.baekjoon.dp;

import java.io.*;
import java.util.stream.Stream;

public class Backpack12865 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numAndWeight = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = numAndWeight[0]; int weight = numAndWeight[1];

        int[] weightArr = new int[num + 1];
        int[] scoreArr = new int[num + 1];
        int[][] dp = new int[num + 1][100001];

        for(int i=1; i <= num; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            weightArr[i] = input[0]; scoreArr[i] = input[1];
            dp[i][weightArr[i]] = scoreArr[i];
        }

        for(int i = 1; i <= num; i++) {
            for (int j = 1; j <= weight; j++) {
                if (weightArr[i] > j) dp[i][j] = dp[i - 1][j];
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weightArr[i]] + scoreArr[i]);
                }
            }
        }
        System.out.println(dp[num][weight]);
    }
}
