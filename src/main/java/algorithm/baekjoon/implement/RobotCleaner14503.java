package algorithm.baekjoon.implement;

import java.io.*;
import java.util.stream.Stream;

public class RobotCleaner14503 {
    static int[][] map;
    static int[][] direction = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // 북, 동, 남, 서 0 1 2 3 (방향 + 3) % 4
    static int cleanerRow, cleanerCol, cleanerDirection, N, M;
    static boolean isMoved = true;
    static int cleanCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0]; M = input[1];

        int[] cleanerInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cleanerRow = cleanerInput[0]; cleanerCol = cleanerInput[1]; cleanerDirection = cleanerInput[2];

        map = new int[N][];
        for(int i = 0; i < N; i++){
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        while(isMoved){
            myBoxCleaning();

            if(isClean()){
                directionMove();
            }
            else{
                cleaning();
            }
        }
        System.out.println(cleanCount);
    }

    static void myBoxCleaning(){
        if(map[cleanerRow][cleanerCol] == 0) {
            cleanCount++;
            map[cleanerRow][cleanerCol] = 2;
        }
    }

    static boolean isClean(){
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,-1,1};

        for(int i = 0; i < 4; i++){
            int checkRow = cleanerRow + dx[i];
            int checkCol = cleanerCol + dy[i];

            if(!mapIn(checkRow,checkCol)) continue;
            if(map[checkRow][checkCol] == 0){
                return false;
            }
        }
        return true;
    }

    static void directionMove() {
        int tempCleanerDirection = ( cleanerDirection + 2 ) % 4;
        int moveRow = cleanerRow + direction[tempCleanerDirection][0];
        int moveCol = cleanerCol + direction[tempCleanerDirection][1];

        if(!mapIn(moveRow, moveCol)) return;
        if(map[moveRow][moveCol] == 1){
            isMoved = false;
            return;
        }

        else{
            cleanerRow = moveRow; cleanerCol = moveCol;
        }
    }

    static void cleaning(){
        cleanerDirection = ( cleanerDirection + 3 ) % 4;

        int moveRow = cleanerRow + direction[cleanerDirection][0];
        int moveCol = cleanerCol + direction[cleanerDirection][1];

        if(!mapIn(moveRow, moveCol)) return;

        if(map[moveRow][moveCol] == 0){
            cleanerRow = moveRow; cleanerCol = moveCol;
        }
    }

    static boolean mapIn(int row, int col){
        return 0 <= row && 0 <= col && row <= N-1 && col <= M - 1;
    }
}
