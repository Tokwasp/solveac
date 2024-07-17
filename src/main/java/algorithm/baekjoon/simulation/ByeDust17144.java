package algorithm.baekjoon.simulation;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ByeDust17144 {
    static int northRow = 0, southRow = 0;
    static int R,C,T, totalDust = 0;
    static int[][] map;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] RCT = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = RCT[0]; C = RCT[1]; T = RCT[2];

        map = new int[R][C];
        boolean isNorth = true;

        for(int i = 0; i < map.length; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == -1){
                    if(isNorth){
                        northRow = i;  isNorth = false;
                    }
                    else southRow = i;
                }
                if(map[i][j] > 0) totalDust += map[i][j];
            }
        }

        int count = 0;

        while(count != T){
            expendDust();
            cleaner();
            count++;
        }

        System.out.println(totalDust);
    }

    static void expendDust(){
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] > 0) q.add(new int[] {i,j, map[i][j]});
            }
        }

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int row = poll[0]; int col = poll[1];
            int dust = poll[2] / 5;

            for(int i = 0; i < 4; i++){
                int nextX = row + dx[i];
                int nextY = col + dy[i];

                boolean error = nextX < 0 || nextY < 0 || nextX > R-1 || nextY > C -1;

                if(!error && map[nextX][nextY] != -1){
                    map[nextX][nextY] += dust;
                    map[row][col] -= dust;
                }
            }
        }
    }

    static void cleaner(){
        totalDust -= map[northRow-1][0];
        totalDust -= map[southRow+1][0];

        for (int i = northRow - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
        for (int i = 0; i < northRow; i++) map[i][C - 1] = map[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[northRow][i] = map[northRow][i - 1];
        map[northRow][1] = 0;

        for (int i = southRow + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
        for (int i = R - 1; i > southRow; i--) map[i][C - 1] = map[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[southRow][i] = map[southRow][i - 1];

        map[southRow][1] = 0;
    }
}