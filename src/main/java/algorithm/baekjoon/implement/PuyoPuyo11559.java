package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;

public class PuyoPuyo11559 {
    private static final int MAP_ROW = 12;
    private static final int MAP_COL = 6;
    private static int puyoChain = Integer.MAX_VALUE, time = 0;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static Puyo[][] map = new Puyo[MAP_ROW][MAP_COL];
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < MAP_ROW; i++){
            String[] input = br.readLine().split("");
            for(int j = 0; j < input.length; j++){
                map[i][j] = new Puyo(input[j], i, j);
            }
        }

        while(puyoChain != 0){
            puyoChain = 0;
            visited = new boolean[MAP_ROW][MAP_COL];
            findShapePuyoAndBfs();
            getOffPuyo();
            if(puyoChain >= 1) {
                time++;
            }
        }
        System.out.println(time);
    }

    private static void findShapePuyoAndBfs() {
        for(int i = 0; i < MAP_ROW; i++){
            for(int j = 0; j < MAP_COL; j++){
                Puyo current = map[i][j];
                if(current.isNotShapeDot() && !visited[i][j]){
                    bfs(map[i][j]);
                }
            }
        }
    }

    private static void getOffPuyo() {
        for (int j = 0; j < MAP_COL; j++) {
            int emptyRow = MAP_ROW - 1;
            for (int i = MAP_ROW - 1; i >= 0; i--) {
                Puyo prev = map[i][j];

                if (prev.isNotShapeDot()) {
                    if (emptyRow != i) {
                        Puyo movePuyo = map[emptyRow][j];
                        movePuyo.changeShape(prev.shape);
                        prev.changeShape(".");
                    }
                    emptyRow--;
                }
            }
        }
    }

    private static void bfs(Puyo puyo) {
        Queue<Puyo> queue = new LinkedList<>();
        List<Puyo> connectList = new ArrayList<>();
        queue.add(puyo);
        connectList.add(puyo);
        visited[puyo.row][puyo.col] = true;

        while(!queue.isEmpty()) {
            Puyo current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if(isMapIn(nextRow,nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol].isSameShape(puyo)){
                    Puyo nextPuyo = map[nextRow][nextCol];
                    connectList.add(nextPuyo);
                    queue.add(nextPuyo);
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        if(connectList.size() >= 4){
            for (Puyo p : connectList) {
                p.shape = ".";
            }
            puyoChain++;
        }
    }

    private static boolean isMapIn(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow <= MAP_ROW -1 && 0 <= nextCol && nextCol <= MAP_COL -1;
    }

    static class Puyo {
        String shape;
        int row;
        int col;

        public Puyo(String shape, int row, int col) {
            this.shape = shape;
            this.row = row;
            this.col = col;
        }

        public boolean isNotShapeDot(){
            return !shape.equals(".");
        }

        public void changeShape(String changeShape) {
            shape = changeShape;
        }

        public boolean isSameShape(Puyo puyo){
            return shape.equals(puyo.shape);
        }
    }
}