package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class EscapeReSolve3055 {
    static Character[][] map;
    static boolean[][] visited;
    static Queue<int[]> waterQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int startRow = 0, startCol = 0;
        map = new Character[R][C];

        waterQ = new LinkedList<>();
        visited = new boolean[map.length][map[0].length];

        // 입력 받기
        for (int row = 0; row < R; row++){
            String[] input = br.readLine().split("");
            for(int col = 0; col < input.length; col++){
                char ch = input[col].charAt(0);
                map[row][col] = ch;

                if(ch == 'S'){
                    startRow = row; startCol = col;
                }
                else if(ch == '*'){
                    waterQ.add(new int[] {row,col});
                }
            }
        }

        // 탈출 게임
        int escapeMinute = escape(startRow, startCol);

        // 결과 출력
        if(escapeMinute == -1){
            System.out.print("KAKTUS");
        }
        else{
            System.out.print(escapeMinute);
        }
    }

    static int escape(int startRow, int startCol){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        // 고슴도치 초기 값 (row, col, 반복 횟수)
        Queue<int[]> animalQ = new LinkedList<>();
        animalQ.add(new int[] {startRow, startCol, 0});
        visited[startRow][startCol] = true;

        // 고슴도치 bfs
        while(true) {

            // 탈출 조건 1 : 목적지 가지 못하는 경우
            if(animalQ.isEmpty()){
                return -1;
            }

            // 물 bfs
            int waterSize = waterQ.size();;
            for (int i = 0; i < waterSize; i++) {
                int[] cur = waterQ.poll();

                for (int repeat = 0; repeat < 4; repeat++) {
                    int nextRow = cur[0] + dx[repeat];
                    int nextCol = cur[1] + dy[repeat];

                    if (isMapIn(nextRow, nextCol) && isNotRockAndWater(nextRow, nextCol) && isNotDestination(nextRow, nextCol)) {
                        waterQ.add(new int[]{nextRow, nextCol});
                        map[nextRow][nextCol] = '*';
                    }
                }
            }

            int animalSize = animalQ.size();
            for (int i = 0; i < animalSize; i++) {
                int[] cur = animalQ.poll();

                // 탈출 조건 2 : 목적지 도착
                if (isDestination(cur[0], cur[1])) {
                    return cur[2];
                }

                for (int repeat = 0; repeat < 4; repeat++) {
                    int nextRow = cur[0] + dx[repeat];
                    int nextCol = cur[1] + dy[repeat];

                    if (isMapIn(nextRow, nextCol) && isNotRockAndWater(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                        animalQ.add(new int[]{nextRow, nextCol, cur[2] + 1});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }

        }
    }

    static boolean isMapIn(int row, int col){
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }

    static boolean isNotRockAndWater(int row, int col){
        return map[row][col] != 'X' && map[row][col] != '*';
    }

    static boolean isNotDestination(int row, int col){
        return map[row][col] != 'D';
    }

    static boolean isDestination(int row, int col){
        return map[row][col] == 'D';
    }
}