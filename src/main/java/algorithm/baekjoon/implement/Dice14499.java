package algorithm.baekjoon.implement;

import java.io.*;
import java.util.stream.Stream;

public class Dice14499 {
    // 차례 대로 0(x),1(동),2(서),3(북),4(남)
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[] dice = new int[7];
    static int[][] map;
    static int m, n, x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0]; m = input[1]; x = input[2]; y = input[3]; int commandNum = input[4];

        map = new int[n][];

        // 지도에 입력값 넣기
        for(int i = 0; i < n; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 명령어 배열
        int[] commandInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 각 명령어 수행 (동,서,북,남)
        for(int i = 0; i < commandNum; i++){
            int command = commandInput[i];
            cmd(command);
        }
    }

    public static void cmd(int command) {
        //이동 하고자 하는 좌표
        int nextX = dx[command] + x;
        int nextY = dy[command] + y;

        //좌표가 지도 밖을 벗어 나가면 error
        boolean error = nextX < 0 || nextY < 0 || nextX > (n-1) || nextY > (m-1);
        if(!error) {
            x = nextX;
            y = nextY;
            // 주사위 굴리기
            roll(command);
        }
    }

    public static void roll(int command) {
        // 주사위 바닥면
        int tmp = dice[3];
        switch (command) {
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
                break;
            case 4:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
        }
        // map 이동한 곳이 0이 아닐 경우
        if(map[x][y] != 0) {
            // 주사위 바닥면 교체
            dice[3] = map[x][y];
            map[x][y] = 0;
        }
        // map 이동한 곳이 0 일 경우
        else {
            map[x][y] = dice[3];
        }
        System.out.println(dice[6]);
    }
}

