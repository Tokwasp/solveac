package algorithm;

import java.io.*;
import java.util.Arrays;

/*
dp[i][j]
[i] = 자리 수
[j] = j로 끝나는 줄어 들지 않는 수

dp[0][0~9] = 0
dp[1][0~9] = 1;

case : dp[2][0~9]

dp[2][0] = 00 = 1
dp[2][1] = 01,11 = 2
dp[2][2] = 02,12,22 = 3

case : dp[3][0~9]

dp[3][0] = 000 = 1
dp[3][1] = 001, 011, 111 = 3
dp[3][2] = 002, 012, 022, 112, 122, 222 = 6

dp[3][2] = 3자리 수이고, 끝 자리가 2 일 때, ??2 앞에 올수 있는 두자리 경우의 수는 dp[2][0~2] 이다.

의문 : 한 자리 수는 줄어 들지 않는 수가 맞는가?
 */
public class NotDecrease2688 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        long[][] dp = new long[65][10];

        // dp[1] 초기값
        Arrays.fill(dp[1], 1);

        // dp 값 계산 1~64 자리 까지 미리 모두 계산
        for(int i = 2; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                for(int k = 0; k <= j; k++) dp[i][j] += dp[i-1][k];
            }
        }

        //테스트 케이스 계산
        for(int i = 1; i <= t; i++){
            int n = Integer.parseInt(br.readLine());

            long result = Arrays.stream(dp[n]).sum();

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
