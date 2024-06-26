package algorithm.baekjoon.graph;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class RedGreenBlindness11026 {
    static char[][] map;
    static boolean[][] visited;
    static Set<Character> set;
    static int area = 0;
    static int greenEqualsRedArea = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String[] RGBInput = br.readLine().split("");

            for(int j = 0; j < RGBInput.length; j++){
                map[i][j] = RGBInput[j].charAt(0);
            }
        }

        //일반 모드
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(!visited[i][j]) {
                    area++;
                    dfs(i, j, map[i][j], false);
                }
            }
        }
        System.out.print(area + " ");

        //적록 색약 모드
        visited = new boolean[N][N];
        set = new HashSet<>();
        set.add('R'); set.add('G');

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(!visited[i][j]) {
                    greenEqualsRedArea++;
                    dfs(i, j, map[i][j], true);
                }
            }
        }
        System.out.println(greenEqualsRedArea);
    }

    static void dfs(int row, int col, char ch, boolean redEqualsGreenMode){
        visited[row][col] = true;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        for(int i = 0; i < 4; i++){
            int newX = row + dx[i];
            int newY = col + dy[i];

            boolean error = newX < 0 || newY < 0 || newX > map.length - 1 || newY > map[0].length - 1;

            if(!error && !visited[newX][newY]){
                //일반 모드
                if(!redEqualsGreenMode) {
                    if(map[newX][newY] == ch) dfs(newX, newY, ch, false);
                }
                //적록 색약 모드
                else {
                    // 둘다 적록 색약에 포함 되는 경우('R','G') || B인 경우 map[newX][newY] == ch
                    if ((set.contains(map[newX][newY]) && set.contains(ch)) || map[newX][newY] == ch)
                        dfs(newX, newY, ch, true);
                }
            }
        }
    }
}
