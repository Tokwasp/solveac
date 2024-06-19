package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ProcessionRoute24430 {
    static int[][] map;
    static int[][] dp;
    static int[][] middleConfirmMap;
    static Set<String> middleSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][];
        dp = new int[N+1][N+1];
        middleConfirmMap = new int[N+1][N+1];

        // map 입력 받기
        for(int i = 0; i < map.length; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int P = Integer.parseInt(br.readLine());
        middleSet = new HashSet<>();

        // 행렬 x,y 입력 받기
        for(int i = 0; i < P; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            middleSet.add(input[0] + "," + input[1]);
        }

        matrixPath(N);
        System.out.println(dp[N][N] + " " + middleConfirmMap[N][N]);

    }

    static void matrixPath(int N){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){

                int count = middleSet.contains(i + "," + j) ? 1 : 0;

                if (dp[i - 1][j] > dp[i][j - 1]) {

                    dp[i][j] = map[i - 1][j - 1] + dp[i - 1][j];
                    middleConfirmMap[i][j] = middleConfirmMap[i - 1][j] + count;

                } else if (dp[i - 1][j] < dp[i][j - 1]) {

                    dp[i][j] = map[i - 1][j - 1] + dp[i][j - 1];
                    middleConfirmMap[i][j] = middleConfirmMap[i][j - 1] + count;

                } else {

                    dp[i][j] = map[i - 1][j - 1] + dp[i - 1][j];
                    middleConfirmMap[i][j] = Math.max(middleConfirmMap[i - 1][j], middleConfirmMap[i][j - 1]) + count;

                }
            }
        }
    }
}
