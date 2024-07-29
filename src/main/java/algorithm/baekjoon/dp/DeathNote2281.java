package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class DeathNote2281 {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> inputs = new ArrayList<>();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = INF;
        }

        for (int i = 0; i < n; i++) {
            inputs.add(Integer.parseInt(br.readLine()));
        }

        // 뒤에서부터 탐색 시작
        for (int i = n - 1; i >= 0; i--) {
            // 남은 노트 길이
            int p = m - inputs.get(i);

            // m을 넘지 않을 때 까지 이어붙이기
            for (int j = i + 1; j < n; j++) {
                if (p < 0) {
                    break;
                }

                // 이어 붙이기 or 다른 페이지에 작성하기 비교
                dp[i] = Math.min(dp[i], (p * p) + dp[j]);

                p -= inputs.get(j) + 1;
            }

            // 모든 페이지를 이어붙여도 0보다 크다면 한 문장안에 작성 가능
            if (p >= 0) {
                dp[i] = 0;
            }
        }
        System.out.println(dp[0]);
    }
}