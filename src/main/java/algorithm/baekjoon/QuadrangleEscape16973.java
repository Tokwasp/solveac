package algorithm.baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class QuadrangleEscape16973 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int rowLength, colLength, endRow, endCol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        map = new int[row][];
        visited = new boolean[row][col];
        for (int i = 0; i < map.length; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rowLength = input[0];
        colLength = input[1];
        int startRow = input[2] - 1;
        int startCol = input[3] - 1;
        endRow = input[4] - 1;
        endCol = input[5] - 1;

        System.out.println(bfs(startRow, startCol));
    }

    static int bfs(int row, int col) {
        visited[row][col] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col,0});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int dist = poll[2];

            if(x == endRow && y == endCol) return dist;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                boolean error = nextX < 0 || nextY < 0 || nextX > map.length - 1 || nextY > map[0].length - 1;
                if (error || visited[nextX][nextY]) continue;

                if(movePossible(nextX,nextY)){
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY, dist + 1});
                }
            }
        }
        return -1;
    }

    static boolean movePossible(int x, int y){
        int xRange = x + rowLength - 1;
        int yRange = y + colLength - 1;

        boolean mapOut = xRange > map.length - 1 || yRange > map[0].length - 1;
        if(mapOut) return false;

        for(int i = 0; i < rowLength; i++){
            if(map[x + i][y] == 1 || map[x + i][yRange] == 1) return false;
        }

        for(int i = 0; i < colLength; i++){
            if(map[x][y + i] == 1 || map[xRange][y + i] == 1) return false;
        }

        return true;
    }
}