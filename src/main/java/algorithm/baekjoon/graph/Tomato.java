package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Tomato {
    private static int day = 0;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] colAndRow = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int initCol = colAndRow[0];
        int initRow = colAndRow[1];

        map = new int[initRow][initCol];

        for(int i = 0; i < initRow; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 현재 토마토 찾기
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[initRow][initCol];

        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                if(map[row][col] == 1){
                    queue.add(new int[] {row, col});
                    visited[row][col] = true;
                }
            }
        }

        while(true) {
            // 익어야 하는 토마토들
            List<int[]> list = bfs(queue);

            // 없다면
            if(list.isEmpty()){
                // 전체가 다 토마토 아닌 경우
                if(isNotSuccess()){
                    System.out.println(-1);
                    return;
                }

                // 전체가 다 토마토인 경우
                System.out.println(day);
                return;
            }

            // 토마토 익게 하기
            process(list);
            queue = new LinkedList<>(list);
            day++;
        }
    }

    private static List<int[]> bfs(Queue<int[]> queue){
        List<int[]> list = new ArrayList<>();

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];

            for(int i = 0; i < 4; i++){
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if(!isMapIn(nextRow, nextCol)) continue;
                if(visited[nextRow][nextCol]) continue;

                if(map[nextRow][nextCol] == 0){
                    visited[nextRow][nextCol] = true;
                    list.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return list;
    }

    private static void process(List<int[]> list){
        for (int[] tomatos : list) {
            int row = tomatos[0];
            int col = tomatos[1];

            map[row][col] = 1;
        }
    }

    private static boolean isMapIn(int row, int col){
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }

    private static boolean isNotSuccess() {
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                if(map[row][col] == 0) return true;
            }
        }
        return false;
    }
}
