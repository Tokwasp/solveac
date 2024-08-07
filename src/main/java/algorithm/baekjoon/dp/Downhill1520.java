package algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Downhill1520 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int row,col;
    static long[][] dp;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = input[0]; col = input[1];

        dp = new long[row][col];
        map = new int[row][col];

        for(int i = 0; i < row; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < row; i++){
            Arrays.fill(dp[i],-1);
        }

        dfs(0,0);

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
        System.out.println(dp[0][0]);
    }

    static long dfs(int start, int end){
        if(start == row - 1 && end == col -1){
            return 1;
        }

        if(dp[start][end] != -1) return dp[start][end];

        dp[start][end] = 0;
        for(int i = 0; i < 4; i++){
            int nextX = start + dx[i];
            int nextY = end + dy[i];

            boolean error = nextX < 0 || nextY < 0 || nextX > map.length - 1 || nextY > map[0].length - 1;

            if(!error && map[start][end] > map[nextX][nextY]){
                dp[start][end] += dfs(nextX,nextY);
            }
        }
        return dp[start][end];
    }
}