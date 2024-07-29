package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class treasure2589 {
    static int maxTime = 0;
    static int row,col;
    static char[][] map;
    static Queue<int[]> q;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] rowAndCol = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = rowAndCol[0]; col = rowAndCol[1];

        map = new char[row][col];
        q = new LinkedList<>();

        for(int i = 0; i < row; i++){
            String input = br.readLine();
            for(int j = 0; j < input.length(); j++){
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'L') q.add(new int[] {i,j});
            }
        }

        eachStartIndexAndBFS();
        System.out.println(maxTime);
    }

    static void eachStartIndexAndBFS(){
        while(!q.isEmpty()){
            int[] init = q.poll();
            int x = init[0]; int y = init[1];

            bfs(x,y);
        }
    }

    static void bfs(int startRow, int startCol){
        Queue<int[]> bfsQ = new LinkedList<>();
        bfsQ.add(new int[] {startRow,startCol,0});

        boolean[][] visited = new boolean[row][col];
        visited[startRow][startCol] = true;

        while(!bfsQ.isEmpty()){
            int[] poll = bfsQ.poll();
            int x = poll[0]; int y = poll[1]; int time = poll[2];

            for(int i = 0; i < 4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                boolean error = nextX < 0 || nextY < 0 || nextX > row-1 || nextY > col-1;

                if(!error && !visited[nextX][nextY] && map[nextX][nextY] == 'L'){
                    int nextTime = time + 1;
                    bfsQ.add(new int[] {nextX, nextY, nextTime});
                    visited[nextX][nextY] = true;
                    maxTime = Math.max(nextTime, maxTime);
                }
            }
        }
    }
}