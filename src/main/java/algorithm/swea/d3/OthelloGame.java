package algorithm.swea.d3;

import java.io.IOException;
import java.util.Scanner;

public class OthelloGame {
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[][] v;
    static int[][] board;
    static int n;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for(int t = 1; t <= tc; t++) {
            n = sc.nextInt();
            int m = sc.nextInt();
            board = new int[n][n];
            v = new int[n][2];
            int b = 0;
            int w = 0;

            board[n/2][n/2] = 2; board[n/2-1][n/2-1] = 2;
            board[n/2-1][n/2] = 1;
            board[n/2][n/2-1] = 1;

            for(int i = 0; i < m; i++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int color = sc.nextInt();

                board[x][y] = color;

                for(int j = 0; j < 8; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 0) {
                        dfs(nx, ny, dx[j], dy[j], color, 0);
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] == 1)
                        b++;
                    if(board[i][j] == 2)
                        w++;

                }
            }

            System.out.println("#" + t + " " + b + " " + w);
        }
    }

    public static void dfs(int x, int y, int d_x, int d_y, int color, int idx) {
        if(board[x][y] == color) {
            for(int i = 0; i < idx; i++) {
                board[v[i][0]][v[i][1]] = color;
            }
            return;
        }
        v[idx][0] = x;
        v[idx][1] = y;

        int nx = x + d_x;
        int ny = y + d_y;

        if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
            if(board[nx][ny] != 0)
                dfs(nx, ny, d_x, d_y, color, idx+1);
        }
        v[idx][0] = 0;
        v[idx][1] = 0;
    }
}
