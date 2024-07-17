package algorithm.baekjoon.graph;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class AlgoSpot1261 {
    static int row, col;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] colAndRow = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        col = colAndRow[0]; row = colAndRow[1];

        arr = new int[row][];
        for(int i = 0; i < row; i++){
            arr[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }

    static int bfs(){
        // 처음 시작은 무조건 0,0 이다.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        pq.add(new int[] {0,0,0});

        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;

        while(!pq.isEmpty()){
            int[] poll = pq.poll();
            int x = poll[0]; int y = poll[1]; int count = poll[2];

            if(x == row - 1 && y == col - 1){
                return count;
            }

            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};

            for(int i = 0; i < 4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                boolean error = nextY < 0 || nextX < 0 || nextX > row - 1 || nextY > col - 1 ;

                if(!error){
                    if(!visited[nextX][nextY]){
                        if(arr[nextX][nextY] == 1) pq.add(new int[] {nextX,nextY,count + 1});
                        else pq.add(new int[] {nextX,nextY,count});
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        return 0;
    }
}
