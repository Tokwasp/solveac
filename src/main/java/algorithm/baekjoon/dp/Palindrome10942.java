package algorithm.baekjoon.dp;

import java.io.*;
import java.util.stream.Stream;

public class Palindrome10942 {
    static int[][] dp;
    static String[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        int m = Integer.parseInt(br.readLine());

        dp = new int[n + 1][n + 1];
        // dp 초기화
        for(int i = 1; i <= n; i++){
            dp[i][i] = 1;
        }

        // 펠린드롬 찾기
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int end = start + len - 1;
                if (input[start - 1].equals(input[end - 1]) && (len == 2 || dp[start + 1][end - 1] == 1)) {
                    dp[start][end] = 1;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        // 질문 입력 받기
        for(int i = 1; i <= m; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = input[0];
            int end = input[1];
            result.append(dp[start][end]).append("\n");
        }
        System.out.print(result);
    }
}