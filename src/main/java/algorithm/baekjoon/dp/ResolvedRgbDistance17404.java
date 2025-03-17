package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class ResolvedRgbDistance17404 {
    static final int MAX_COLOR_SIZE = 3;
    static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int homeCount = Integer.parseInt(br.readLine());

        int[][] rgbPrice = new int[homeCount + 1][MAX_COLOR_SIZE];

        // 입력 받기
        for(int row = 1; row <= homeCount; row++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int color = 0; color < MAX_COLOR_SIZE; color++) {
                rgbPrice[row][color] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[homeCount + 1][3];
        int minPrice = Integer.MAX_VALUE;

        // 첫 색상 정하기
        for(int firstColor = 0; firstColor < MAX_COLOR_SIZE; firstColor++){
            dp[1][firstColor] = rgbPrice[1][firstColor];

            // 첫 색상 아닌 경우 INF 초기화
            for(int init = 0; init < MAX_COLOR_SIZE; init++){
                if(init == firstColor) continue;
                dp[1][init] = INF;
            }

            for(int repeat = 2; repeat <= homeCount; repeat++){
                dp[repeat][0] = Math.min(dp[repeat-1][1], dp[repeat-1][2]) + rgbPrice[repeat][0];
                dp[repeat][1] = Math.min(dp[repeat-1][0], dp[repeat-1][2]) + rgbPrice[repeat][1];
                dp[repeat][2] = Math.min(dp[repeat-1][0], dp[repeat-1][1]) + rgbPrice[repeat][2];
            }

            // 마지막 색상은 첫 색상과 같지 않아야 한다.
            for(int last = 0; last < MAX_COLOR_SIZE; last++) {
                if (firstColor == last) continue;
                minPrice = Math.min(minPrice, dp[homeCount][last]);
            }
        }
        System.out.print(minPrice);
    }
}