package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class Escape3055 {
    static Queue<int[]> waterQueue;
    static int row, col;
    static boolean[][] animalVisited, waterVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        String[][] map = new String[row][col];
        waterQueue = new LinkedList<>();
        animalVisited = new boolean[row][col];
        waterVisited = new boolean[row][col];

        int startRow = 0, startCol = 0;

        // 맵 입력 받기
        for (int i = 0; i < map.length; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < input.length; j++) {
                map[i][j] = String.valueOf(input[j]);
                // 고슴 도치 시작 위치 찾기
                if (map[i][j].equals("S")) {
                    startRow = i;
                    startCol = j;
                }
                if (map[i][j].equals("*")) {
                    waterQueue.add(new int[]{i, j});
                    waterVisited[i][j] = true;
                }
            }
        }

        int result = bfs(map, startRow, startCol);
        if (result == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }

    static int bfs(String[][] map, int startRow, int startCol) {
        Queue<int[]> animalQueue = new LinkedList<>();
        animalVisited[startRow][startCol] = true;
        animalQueue.add(new int[]{startRow, startCol, 0});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!animalQueue.isEmpty()) {
            // 물 움직임 먼저
            int size = waterQueue.size();

            for (int w = 0; w < size; w++) {
                int[] waterCur = waterQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextRow = waterCur[0] + dx[i];
                    int nextCol = waterCur[1] + dy[i];

                    if (isMapIn(nextRow, nextCol) &&
                            !waterVisited[nextRow][nextCol] &&
                            !map[nextRow][nextCol].equals("X") &&
                            !map[nextRow][nextCol].equals("D")) {

                        waterVisited[nextRow][nextCol] = true;
                        waterQueue.add(new int[]{nextRow, nextCol});
                    }
                }
            }


            // 고슴 도치 움직임
            int animalSize = animalQueue.size();
            for(int a = 0; a < animalSize; a++) {
                int[] animalCur = animalQueue.poll();

                if (map[animalCur[0]][animalCur[1]].equals("D")) {
                    return animalCur[2];
                }

                for (int i = 0; i < 4; i++) {
                    int nextRow = animalCur[0] + dx[i];
                    int nextCol = animalCur[1] + dy[i];
                    int count = animalCur[2];

                    if (isMapIn(nextRow, nextCol) &&
                            !animalVisited[nextRow][nextCol] &&
                            !waterVisited[nextRow][nextCol] &&
                            !map[nextRow][nextCol].equals("X")) {

                        animalVisited[nextRow][nextCol] = true;
                        animalQueue.add(new int[]{nextRow, nextCol, count + 1});
                    }
                }
            }
        }
        return -1;
    }

    static boolean isMapIn(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col;
    }
}