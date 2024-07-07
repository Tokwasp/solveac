package algorithm.baekjoon.implement;

import java.io.*;
import java.util.stream.Stream;

public class BomberMen {
    static int[][] bomb;
    static int R, N, C;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] RNC = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = RNC[0]; C = RNC[1]; N = RNC[2];

        // 폭탄의 시간을 담는 배열
        bomb = new int[R][C];
        for(int i = 0; i < R; i++){
            String[] input = br.readLine().split("");

            for(int j = 0; j < C; j++){
                char present = input[j].charAt(0);
                if(present == 'O') bomb[i][j] = 3;
            }
        }

        for(int i = 2; i <= N; i++){
            // 짝수 시간 : 폭탄이 셋팅 된다.
           if(i % 2 == 0) setBomb(i);
           // 홀수 시간 : 폭탄이 터진다.
           else explosion(i);
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(bomb[i][j] == 0) sb.append(".");
                else sb.append("O");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void setBomb(int start){
        // 폭탄이 터지는 시간은 현재 시간의 +3 이다.
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(bomb[i][j] == 0) bomb[i][j] = start + 3;
            }
        }
    }

    static void explosion(int bombTime){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(bomb[i][j] == bombTime) bomb(i,j, bombTime);
            }
        }
    }

    static void bomb(int row, int col, int bombTime){
        int x = row; int y = col;

        bomb[row][col] = 0;

        for(int j = 0; j < 4; j++){
            int newX = x + dx[j];
            int newY = y + dy[j];

            // 주위에 시간 초가 같은 폭탄은 터트 리지 않는다.
            boolean error = newX < 0 || newY < 0 || newX >= R || newY >= C || bomb[newX][newY] == bombTime;
            if(!error) bomb[newX][newY] = 0;
        }
    }
}