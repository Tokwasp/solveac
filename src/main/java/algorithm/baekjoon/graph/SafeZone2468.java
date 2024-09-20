package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class SafeZone2468 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int safeZoneCount = 0;
    static int max = Integer.MIN_VALUE;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(map[i][j], max);
            }
        }

        for(int rain = 0; rain <= max; rain++) {
            visited = new boolean[N][N];
            count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > rain) {
                        bfs(i, j, rain);
                        count++;
                    }
                }
            }
            safeZoneCount = Math.max(count,safeZoneCount);
        }
        System.out.println(safeZoneCount);
    }

    static void bfs(int row, int col, int rain){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {row,col});
        visited[row][col] = true;

        while(!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                boolean error = nextX < 0 || nextY < 0 || nextX > map.length - 1 || nextY > map[0].length - 1;

                if (!error && !visited[nextX][nextY] && map[nextX][nextY] > rain) {
                    q.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
}