package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class RGBDistance17404 {
    static int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] rgb = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < rgb[0].length; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) { // Red Green Blue

            for (int i = 0; i < 3; i++) { // 첫번째 집을 k 번째 색으로 선택 k = 0 -> 1번째 집은 Red
                dp[1][i] = (i == k) ? rgb[1][i] : INF;
            }

            // 2 ~ N 번째 집 까지 색칠 하기
            for (int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + rgb[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + rgb[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + rgb[i][2];
            }

            for(int i = 0; i < 3; i++){
                //마지막 집은 첫번째 집과 색이 다름
                if(i != k){
                    ans = Math.min(dp[N][i], ans);
                }
            }
        }
        System.out.print(ans);
    }
}