package algorithm.baekjoon.dp;

import java.io.*;
import java.util.stream.Stream;

public class WayCount1577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NM[0]; int M = NM[1];
        int forbidCount = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][M + 1];
        // 세로 공사중
        long[][] prohibitRow = new long[N + 1][M + 1];
        // 가로 공사중
        long[][] prohibitCol = new long[N + 1][M + 1];

        for(int i = 0; i < forbidCount; i++){
            int[] rowAndCol = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(rowAndCol[1] == rowAndCol[3]) {
                prohibitRow[Math.min(rowAndCol[0],rowAndCol[2])][rowAndCol[1]] = 1;
            }
            else{
                prohibitCol[rowAndCol[0]][Math.min(rowAndCol[1],rowAndCol[3])] = 1;
            }
        }

        //공사 시작 지점 까지 1로 초기화
        for(int i = 0; i <= N; i++){
            if(prohibitRow[i][0] == 1){
                dp[i][0] = 1;
                break;
            }
            dp[i][0] = 1;
        }

        //공사 시작 지점 까지 1로 초기화
        for(int i = 0; i <= M; i++){
            if(prohibitCol[0][i] == 1){
                dp[0][i] = 1;
                break;
            }
            dp[0][i] = 1;
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M ; j++){
                // 위 왼쪽 둘다 공사중 인경우
                if(prohibitRow[i-1][j] == 1 && prohibitCol[i][j-1] == 1) dp[i][j] = 0;
                // 위쪽만 공사중 인경우
                else if(prohibitRow[i-1][j] == 1) dp[i][j] = dp[i][j-1];
                // 왼쪽만 공사중 인경우
                else if(prohibitCol[i][j-1] == 1) dp[i][j] = dp[i-1][j];
                // 공사 아닌 경우
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        System.out.println(dp[N][M]);
    }
}