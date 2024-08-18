package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class Cheese2638 {
    static int N, M, answer;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = 0;

        int remainCheese = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) remainCheese++;
            }
        }
        
        // 남은 치즈가 0개가 아니면
        while (remainCheese != 0) {
            visited = new boolean[N][M];

            // 외부 공기는 3으로 표시 bfs
            initAir(0, 0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    //치즈 발견 시
                    if (map[i][j] == 1) {
                        // 외부 공기가 2개 이상 인지 체크후 치즈 녹임
                        if(checkMelt(i, j)) remainCheese--;
                    }
                }
            }
            answer++;
        }
        System.out.println(answer);
    }

    public static boolean checkMelt(int x, int y) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx, ny) && map[nx][ny] == 3) {
                count++;
            }
        }
        // 녹는 치즈는 바로 녹임
        if (count >= 2) {
            map[x][y] = 0;
            return true;
        }
        return false;
    }

    //외부 공기와 접촉한 공기는 3으로 표시
    static void initAir(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[] {0,0});
        map[x][y] = 3;

        while(!q.isEmpty()) {
            int[] poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (!isValid(nx, ny)) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;

                visited[nx][ny] = true; map[nx][ny] = 3;
                q.add(new int[] {nx,ny});
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}