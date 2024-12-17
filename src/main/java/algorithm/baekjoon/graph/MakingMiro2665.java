package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MakingMiro2665 {
    static int[][] map;
    static int[][] wallMap;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        wallMap = new int[N][N];

        for(int i = 0; i < N; i++){
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        bfs();
        System.out.println(wallMap[N-1][N-1]);
    }

    private static void bfs(){
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[2]));
        queue.add(new int[] {0, 0, 0});

        boolean[][] visited = new boolean[N][N];

        int[] dx = {-1,0,0,1};
        int[] dy = {0,-1,1,0};

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            for(int i = 0; i < 4; i++){
                int nextRow = poll[0] + dx[i];
                int nextCol = poll[1] + dy[i];
                int breakWall = poll[2];

                if(isMapIn(nextRow,nextCol) && !visited[nextRow][nextCol]){
                    // 벽인 경우
                    if(map[nextRow][nextCol] == 0){
                        breakWall += 1;
                    }
                    queue.add(new int[] {nextRow,nextCol, breakWall});
                    visited[nextRow][nextCol] = true;
                    wallMap[nextRow][nextCol] = breakWall;
                }
            }
        }
    }

    private static boolean isMapIn(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow <= N -1 && 0 <= nextCol && nextCol <= N -1;
    }
}