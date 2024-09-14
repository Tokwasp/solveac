package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
 * 예제 1   -> DP
 * 0 0 0      0 0 0
 * 1 2 3      1 2 3
 * 4 5 6      6 8 9
 * 4 9 0     12 18 9
 *
 * 설명 :
 * dp[i][j]란 i는 사다리 타고 내려온 레벨 이고, j는 사다리를 의미 합니다
 * 위의 예제 DP 에서 DP[2][0] = 6 입니다.
 * 사다리는 모두 이전 레벨 에서 타고 내려올 수 있는데
 *
 * dp[2][0]로 왔을 때 최대 값을 찾는 방법은
 * dp[2][0]으로 내려 오려면 이전 레벨 에서 dp[i-1][j], dp[i-1][j + 1] 밖에 내려 오지 못 합니다.
 * 그러므로 둘중 큰 값을 찾고 자신의 값인 arr[i][j]를 더 해 주면 됩니다.
 *
 * */
public class GoDown2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 1][3];

        //입력 받기
        for (int i = 1; i <= N; i++) {
            arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];

        for (int i = 1; i <= N; i++) {

            int curr = i % 2;
            int prev = 1 - curr;

            maxDp[curr][0] = Math.max(maxDp[prev][0], maxDp[prev][1]) + arr[i][0];
            minDp[curr][0] = Math.min(minDp[prev][0], minDp[prev][1]) + arr[i][0];

            maxDp[curr][2] = Math.max(maxDp[prev][1], maxDp[prev][2]) + arr[i][2];
            minDp[curr][2] = Math.min(minDp[prev][1], minDp[prev][2]) + arr[i][2];

            maxDp[curr][1] = Math.max(Math.max(maxDp[prev][0], maxDp[prev][1]), maxDp[prev][2]) + arr[i][1];
            minDp[curr][1] = Math.min(Math.min(minDp[prev][0], minDp[prev][1]), minDp[prev][2]) + arr[i][1];
        }

        int max = Arrays.stream(maxDp[N % 2]).max().getAsInt();
        int min = Arrays.stream(minDp[N % 2]).min().getAsInt();
        System.out.println(max + " " + min);
    }
}
