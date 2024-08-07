package algorithm.baekjoon.dp;

import java.io.*;
import java.util.stream.Stream;

public class Fubao1937 {
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max = 0, N = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                max = Math.max(max, DFS(i,j));
            }
        }
        System.out.println(max);
    }

    static int DFS(int row, int col){
        if(dp[row][col] != 0) return dp[row][col];

        dp[row][col] = 1;

        for(int i = 0; i < 4; i++){
            int nextX = row + dx[i];
            int nextY = col + dy[i];

            boolean error = nextX < 0 || nextY < 0 || nextX > N-1 || nextY > N-1;

            if(!error && map[row][col] < map[nextX][nextY]){
               dp[row][col] = Math.max(dp[row][col], DFS(nextX,nextY) + 1);
            }
        }
        return dp[row][col];
    }
}