package algorithm.baekjoon.dp;

import java.io.*;
import java.util.StringTokenizer;

public class PrefixSum5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];
        int[][] arrSum = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            int prior = 0;

            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                arrSum[i][j] = prior + arr[i][j];
                prior = arrSum[i][j];
            }
        }

        // 구간합 구하기
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            int result = 0;
            for(int j = startRow; j <= endRow; j++){
                result += arrSum[j][endCol] - arrSum[j][startCol-1];
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}