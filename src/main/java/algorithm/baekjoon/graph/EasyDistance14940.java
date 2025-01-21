package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class EasyDistance14940 {
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int startRow = 0, startCol = 0;
        for(int i = 0; i < N; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < M; j++){
                if(map[i][j] == 2){
                    startRow = i;
                    startCol = j;
                }
            }
        }

        boolean[][] visited = new boolean[N][M];
        visited[startRow][startCol] = true;
        bfs(startRow, startCol, map, visited);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(i == startRow && j == startCol){
                    sb.append(0).append(" ");
                }
                else if(!visited[i][j] && map[i][j] != 0){
                    sb.append(-1).append(" ");
                }
                else{
                    sb.append(map[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int startRow, int startCol, int[][] map, boolean[][] visited){
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startRow,startCol,0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nextRow = cur[0] + dx[i];
                int nextCol = cur[1] + dy[i];

                if(isMapIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1){
                    map[nextRow][nextCol] = cur[2] + 1;
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[] {nextRow, nextCol, cur[2] + 1});
                }
            }
        }
    }

    static boolean isMapIn(int row, int col){
        return 0 <= row && row < N && 0 <= col && col < M;
    }
}