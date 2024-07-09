package algorithm.baekjoon.simulation;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class PeopleMove16234 {
    static int N,L,R;

    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NLR = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NLR[0]; L = NLR[1]; R = NLR[2];

        map = new int[N][];
        for(int i = 0; i < N; i++) map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int day = 0;

       while(true){
           boolean isMoved = false;
           int[][] unionMap = new int[N][N];
           int unionNum = 1;

           for (int i = 0; i < N; i++) {
               for (int j = 0; j < N; j++) {
                   if (unionMap[i][j] == 0) {
                       int sum = bfs(i, j, unionMap, unionNum);
                       if (sum > 0) {
                           isMoved = true;
                       }
                       unionNum++;
                   }
               }
           }

           if (!isMoved) {
               break;
           }
           day++;
       }
        System.out.println(day);
    }

    static int bfs(int row, int col, int[][] unionMap, int unionNum){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row,col});

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{row,col});

        unionMap[row][col] = unionNum;
        int sum = map[row][col];
        int count = 1;

        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0]; int y = xy[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                boolean error = nextX < 0 || nextY < 0 || nextX > N - 1 || nextY > N - 1;

                if (!error && unionMap[nextX][nextY] == 0) {
                    int diff = Math.abs(map[x][y] - map[nextX][nextY]);

                    if(L <= diff && diff <= R) {
                        int[] nextXY = new int[]{nextX, nextY};
                        q.add(nextXY);
                        list.add(nextXY);
                        unionMap[nextX][nextY] = unionNum;
                        sum += map[nextX][nextY];
                        count++;
                    }
                }
            }
        }

        if (count > 1) {
            int newPopulation = sum / count;
            for (int[] xy : list) {
                map[xy[0]][xy[1]] = newPopulation;
            }
            return sum;
        }
        return 0;
    }
}