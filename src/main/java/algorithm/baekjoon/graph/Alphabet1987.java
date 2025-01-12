package algorithm.baekjoon.graph;

import java.io.*;
import java.util.StringTokenizer;

public class Alphabet1987 {
    static char[][] map;
    static int row = 0 ,col = 0, max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        // 맵 입력 받기
        map = new char[row][col];
        for(int i = 0; i < row; i++){
            char[] input = br.readLine().toCharArray();
            for(int j = 0; j < col ; j++){
                map[i][j] = input[j];
            }
        }

        boolean[] visited = new boolean['Z' - 'A' + 1];
        visited[map[0][0] - 'A'] = true;
        dfs(0,0, 1, visited);

        System.out.print(max);
    }

    private static void dfs(int row, int col, int depth, boolean[] visited){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        max = Math.max(depth, max);

        for(int i = 0; i < 4; i++){
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if(!isMapIn(nextRow, nextCol) || visited[map[nextRow][nextCol] - 'A']) continue;
            int nextNode = map[nextRow][nextCol] - 'A';

            visited[nextNode] = true;
            dfs(nextRow, nextCol, depth + 1, visited);
            visited[nextNode] = false;
        }
    }

    static boolean isMapIn(int nextRow, int nextCol){
        return 0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col;
    }
}