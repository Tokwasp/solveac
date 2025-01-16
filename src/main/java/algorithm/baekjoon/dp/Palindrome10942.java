package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Palindrome10942 {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] stArr = new String[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            stArr[i] = st.nextToken();
        }
        int m = Integer.parseInt(br.readLine());

        dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            dp[i][i] = 1;
        }

        // 펠린드롬 찾기
        for(int len = 2; len <= n; len++){
            for(int repeat = 1; repeat <= n - len + 1; repeat++){
                int end = len + repeat - 1;
                if(stArr[repeat].equals(stArr[end]) && (dp[repeat + 1][end - 1] == 1 || len == 2)){
                    dp[repeat][end] = 1;
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