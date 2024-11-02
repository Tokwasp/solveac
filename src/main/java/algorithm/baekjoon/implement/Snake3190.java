package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Snake3190 {
    static int[][] map;
    static int[][] direction = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static int snakeDirection = 0, time = 0, N;
    static boolean gameOver = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); int K = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < K; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int row = input[0]; int col = input[1];
            //사과 표시
            map[row-1][col-1] = 4;
        }

        Queue<TimeDirection> q = new LinkedList<>();
        int L = Integer.parseInt(br.readLine());

        for(int i = 0; i < L; i++){
            String[] input = br.readLine().split(" ");
            q.add(new TimeDirection(Integer.parseInt(input[0]), input[1]));
        }

        Deque<int[]> snakeQ = new LinkedList<>();
        snakeQ.add(new int[] {0,0});

        while(!gameOver){
            time++;
            snakeMove(snakeQ);

            if(!q.isEmpty() && q.peek().time == time) {
                ChangeDirection(q.poll().direction);
            }
        }
        System.out.println(time);
    }

    static void snakeMove(Deque<int[]> snakeQ){
        int[] coordinate = snakeQ.peekFirst();
        int nextRow = coordinate[0] + direction[snakeDirection][0];
        int nextCol = coordinate[1] + direction[snakeDirection][1];

        if(!isMapIn(nextRow, nextCol) || map[nextRow][nextCol] == 1){
            gameOver = true;
            return;
        }
        snakeQ.addFirst(new int[] {nextRow,nextCol});

        if(map[nextRow][nextCol] != 4){
            int[] poll = snakeQ.pollLast();
            map[poll[0]][poll[1]] = 0;
        }
        map[nextRow][nextCol] = 1;
    }

    static void ChangeDirection(String direction){
        if(direction.equals("D")){
            snakeDirection = (snakeDirection + 1) % 4;
        }
        else{
            snakeDirection = (snakeDirection + 3) % 4;
        }
    }

    private static boolean isMapIn(int nextRow, int nextCol) {
        return nextRow >= 0 && nextCol >= 0 && nextRow <= N-1 && nextCol <= N-1;
    }

    static class TimeDirection{
        int time;
        String direction;

        public TimeDirection(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }
}