package algorithm.swea.d3;

import java.io.IOException;
import java.util.Scanner;

/*
    문제 풀이 :

    풀이 방법 : 구현

    보드판을 입력 받고 현태 탱크의 위치와(row,col)을 찾고, 방향을 찾는다.
    명령어가 이동 명령어 일 경우:
    1. 현재 탱크 방향을 명령어 방향 으로 바꾼다.
    2. 이동 좌표가 보드판 안이고, 이동 좌표가 "." 일경우 탱크의 좌표와, "."의 좌표를 바꾼다.

    명령어가 "S" 일 경우 :
    탱크 방향에 따라 "*" 탐색 하고, 발견시 평지 "." 로 바꾼다.
    "*" / "#" 벽을 발견시 탐색을 종료 한다.
 */
public class SanghoBattleField {
    static String battleFieldArr[][];
    static String Attitude;
    static int tankRow = 0;
    static int tankCol = 0;

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for(int t=1; t <= testCase; t++) {
            int H = sc.nextInt(); int W = sc.nextInt(); sc.nextLine();

            battleFieldArr = new String[H][];
            String[] battleFieldAttitude = {"^","v","<",">"};

            // 필드에 값을 넣고, 탱크의 위치와 현재 상태를 찾는다.
            for(int i = 0; i < H; i++) {
                battleFieldArr[i] = sc.nextLine().split("");

                for(int j = 0; j < W; j++) {

                    for(int k = 0; k < battleFieldAttitude.length; k++) {

                        if(battleFieldArr[i][j].equals(battleFieldAttitude[k])) {
                            Attitude = battleFieldAttitude[k];
                            tankRow = i;
                            tankCol = j;
                        }
                    }
                }
            }

            int N = sc.nextInt(); sc.nextLine();
            String[] command = sc.nextLine().split("");

            for(int j = 0; j < N; j++) {
                battleField(command[j]);
            }
            print(t);
        }
    }

    static void battleField(String command) {
        String[] move = {"U","D","L","R"};
        String[] newAttitude = {"^", "v", "<", ">"};
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        if(command.equals("S")) {
            if(Attitude.equals("^")) {
                for(int i = tankRow; i >= 0; i--) {
                    if(battleFieldArr[i][tankCol].equals("#")) break;
                    if(battleFieldArr[i][tankCol].equals("*")) {
                        battleFieldArr[i][tankCol] = ".";
                        break;
                    }
                }
            }
            if(Attitude.equals("v")) {
                for(int i = tankRow; i < battleFieldArr.length; i++) {
                    if(battleFieldArr[i][tankCol].equals("#")) break;
                    if(battleFieldArr[i][tankCol].equals("*")) {
                        battleFieldArr[i][tankCol] = ".";
                        break;
                    }
                }
            }
            if(Attitude.equals("<")) {
                for(int i = tankCol; i >= 0; i--) {
                    if(battleFieldArr[tankRow][i].equals("#")) break;
                    if(battleFieldArr[tankRow][i].equals("*")) {
                        battleFieldArr[tankRow][i] = ".";
                        break;
                    }
                }
            }
            if(Attitude.equals(">")) {
                for(int i = tankCol; i < battleFieldArr[0].length; i++) {
                    if(battleFieldArr[tankRow][i].equals("#")) break;
                    if(battleFieldArr[tankRow][i].equals("*")) {
                        battleFieldArr[tankRow][i] = ".";
                        break;
                    }
                }
            }
        }
        else {
            for(int i = 0; i < move.length; i++) {
                if(command.equals(move[i])) {
                    //탱크의 방향 바꾸기
                    Attitude = newAttitude[i];
                    battleFieldArr[tankRow][tankCol] = Attitude;

                    int prevX = tankRow;
                    int prevY = tankCol;

                    int x = tankRow + dx[i];
                    int y = tankCol + dy[i];

                    //이동 방향이 보드판 넘어 서지 않고, "." 일 경우에 탱크 이동
                    boolean error = (x >= battleFieldArr.length || x < 0 || y >= battleFieldArr[0].length || y < 0);

                    if(!error && battleFieldArr[x][y].equals(".")) {
                            battleFieldArr[x][y] = Attitude;
                            battleFieldArr[prevX][prevY] = ".";
                            tankRow = x; tankCol = y;
                    }
                }
            }
        }
    }
    // 보드판 프린트 메서드
    static void print(int testCase) {
        String st = "#" + testCase + " ";
        System.out.print(st);

        for(int i = 0; i < battleFieldArr.length; i++) {
            for(int j = 0; j < battleFieldArr[0].length; j++) {
                System.out.print(battleFieldArr[i][j]);
            }
            System.out.println();
        }

    }
}
