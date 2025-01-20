package algorithm.baekjoon.graph;

import java.io.*;

public class NQueen9663 {
    static int N, count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] queen = new int[N + 1];

        dfs(1, queen);
        System.out.println(count);
    }

    static void dfs(int depth, int[] queen){
        if(depth == N + 1){
            count++;
            return;
        }

        for(int i = 1; i <= N; i++){
            queen[depth] = i;
            if(isPossible(depth, queen)){
                dfs(depth + 1, queen);
            }
        }
    }

    static boolean isPossible(int depth, int[] queen){
        if(depth == 1) return true;

        for(int row = 1; row <= depth - 1; row++){
            int col = queen[row];
            int depthCol = queen[depth];

            // 열 같은지 확인
            if(col == depthCol) {
                return false;
            }

            // 왼쪽 위 대각선 오른짝 아래 대각선
            if((row - col) == (depth - depthCol)){
                return false;
            }

            // 왼쪽 아래 오른쪽 위 대각선
            if((row + col) == (depth + depthCol)) return false;
        }
        return true;
    }
}