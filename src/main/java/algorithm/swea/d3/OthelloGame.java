package algorithm.swea.d3;

import java.io.IOException;
import java.util.Scanner;

/*
   문제 풀이 :

   풀이 방법 : dfs (완전 탐색)

   돌을 놓을때, 여덟 방향에 대해서 돌이 놓인 경우에 dfs 함수를 실행한다.

   dfs(int x, int y, int d_x, int d_y, int color, int idx)
   x, y : 방향을 체크 했을떄 돌이 놓인 곳
   d_x , d_y : 어떤 방향 으로 체크 했는가.
   color : 현재 놓은 돌의 색깔
   idx : 후보 군의 숫자

   돌이 다른 색깔 인 경우:
   후보 v 배열에 좌표 값을 입력 하고 해당 방향에 돌이 더 있다면,
   dfs(idx + 1)를 실행 한다.

   돌이 같은 색깔 인 경우 :
   후보 군(idx) 의 숫자 만큼
   자신의 색깔로 바꾼다.

   한 방향 탐색을 다 한 경우 :
   v[idx][0] v[idx][1] = 0; 으로 초기화 한다.
   그후 다음 방향 으로 dfs를 호출 한다.
 */

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
