package algorithm.baekjoon.simulation;

import java.io.*;
import java.util.*;

public class MiniShark16236 {
    static int[][] map;
    static int sharkRow = 0; static int sharkCol = 0; static int sharkSize = 0; static int N;
    static int eatCount = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Fish{
        int row; int col; int dist;

        public Fish(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getDist() {
            return dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        //입력 받기 : 상어 위치, 물고기 계산 위해 상어 : 0 표시
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sharkRow = i; sharkCol = j; sharkSize = 2;
                    map[i][j] = 0;
                }
            }
        }

        int count = 0;

        while(true){
            int[][] bfsMap = bfs();
            int result = hunt(bfsMap);

            if(result == 0) break;
            else count += result;
        }
        System.out.println(count);
    }

   static int hunt(int[][] bfsMap){
        // 거리, row, col 오름 차순
       PriorityQueue<Fish> pq = new PriorityQueue<>(Comparator.comparingInt(Fish::getDist).thenComparing(Fish::getRow).thenComparing(Fish::getCol));

       for(int i = 0; i < bfsMap.length; i++){
           for(int j = 0; j < bfsMap[0].length; j++){
               //상어 보다 크기 작은 물고기
               if(bfsMap[i][j] != 0 && map[i][j] != 0 && map[i][j] < sharkSize) pq.add(new Fish(i,j,bfsMap[i][j]));
           }
       }

      if(pq.isEmpty()) return 0;
      else {
          Fish fish = pq.poll();
          int row = fish.row;
          int col = fish.col;
          int result = fish.dist;

          sharkRow = row;
          sharkCol = col;
          map[row][col] = 0;
          eatCount += 1;

          // 물고기 먹은 횟수가 상어 크기 만큼 인 경우
          if (eatCount == sharkSize) {
              sharkSize++;
              eatCount = 0;
          }
          return result;
      }
   }

    static int[][] bfs(){
        int[][] bfsMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> q  = new LinkedList<>();
        q.add(new int[] {sharkRow,sharkCol,0});
        visited[sharkRow][sharkCol] = true;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0]; int y = poll[1];

            for(int i = 0; i < 4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                int dist = poll[2];

                boolean error = nextX < 0 || nextY < 0 || nextX > N-1 || nextY > N-1;

                if(!error){
                    // 상어의 크기 이하인 경우 지니 갈 수 있음
                    if(!visited[nextX][nextY] && map[nextX][nextY] <= sharkSize){
                        bfsMap[nextX][nextY] = dist + 1;
                        q.add(new int[] {nextX,nextY,dist + 1});
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        return bfsMap;
    }
}