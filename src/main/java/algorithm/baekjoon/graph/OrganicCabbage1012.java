package algorithm.baekjoon.graph;

import java.io.*;
import java.util.stream.Stream;

public class OrganicCabbage1012 {
    static boolean[][] visited;
    static int[][] board;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= testCase; t++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int boardCol = input[0];
            int boardRow = input[1];
            int cabbageNum = input[2];

            board = new int[boardRow][boardCol];
            visited = new boolean[boardRow][boardCol];

            for (int i = 0; i < cabbageNum; i++) {
                int[] cabbageIndex = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int col = cabbageIndex[0];
                int row = cabbageIndex[1];
                board[row][col] = 1;
            }


            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (!visited[i][j] && board[i][j] == 1) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
            count = 0;
        }
        System.out.print(sb);
    }

    static void dfs(int row , int col){

        visited[row][col] = true;

        //상하 좌우
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        for(int i=0; i<4; i++){
            int newRow = row + dx[i];
            int newCow = col + dy[i];

            boolean error = (newRow < 0 || newCow < 0 || newRow >= board.length || newCow >= board[0].length);
            if(!error && board[newRow][newCow] == 1 && !visited[newRow][newCow]) {
                dfs(newRow, newCow);
            }
        }
    }
}
