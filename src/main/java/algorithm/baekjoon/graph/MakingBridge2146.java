package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MakingBridge2146 {
    static ArrayList<int[]> startList;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int num = 2, N;
    static int minLength = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][];
        startList = new ArrayList<>();

        for(int i = 0; i < map.length; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //각 섬을 구분 하기 위해 넘버링 bfs
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == 1) {
                    bfs(i, j, false);
                    num++;
                }
            }
        }

        // 각섬의 가장 자리 bfs
        for (int[] dots : startList) {
            int x = dots[0]; int y = dots[1];
            bfs(x,y,true);
        }
        System.out.println(minLength);
    }

    static void bfs(int row, int col, boolean isFind){
        boolean[][] visited = new boolean[N][N];

        visited[row][col] = true;
        Queue<int[]> q = new LinkedList<>();

        if(!isFind) {
            q.add(new int[]{row, col});
            map[row][col] = num;
        }
        else{
            q.add(new int[]{row,col,0});
        }

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                boolean error = nextX < 0 || nextY < 0 || nextX > N - 1 || nextY > N - 1;

                //섬을 넘버링 하는 경우
                if (!isFind) {
                    if(!error) {
                        // 이어진 섬일 경우
                        if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                            visited[nextX][nextY] = true;
                            map[nextX][nextY] = num;
                            q.add(new int[]{nextX, nextY});
                        }

                        // 가장 자리 인 경우
                        if (map[nextX][nextY] == 0) {
                            startList.add(new int[]{x, y});
                        }
                    }
                }
                //최단 거리 찾는 경우
                else{
                    if(!error) {
                        // 거리 이동중
                        if (!visited[nextX][nextY] && map[nextX][nextY] == 0) {
                            visited[nextX][nextY] = true;
                            int nextDist = poll[2] + 1;
                            q.add(new int[]{nextX, nextY, nextDist});
                        }
                        // 도착한 곳이 출발지X, map[nextX][nextY] != 0 -> 다른 섬 일경우
                        else if (map[nextX][nextY] != map[row][col] && map[nextX][nextY] != 0) {
                            minLength = Math.min(minLength, poll[2]);
                            //BFS 단계별 거리가 1씩 늘어 나는데 도착 하면 그 거리가 최단 거리 이다.
                            return;
                        }
                    }
                }
            }
        }
    }
}