package algorithm.implement;

import java.io.*;
import java.util.stream.Stream;

public class Gear14891 {
    static void checkSolution(int[][] gear,int[][] checkArr,int gearNum, int direction){
        int directionNum = direction;
        //왼쪽체크
        for(int i=gearNum; i > 0; i--){
            if(gear[i][6] != gear[i-1][2]) {
                checkArr[i-1][0] = 1;
                directionNum = - directionNum;
                checkArr[i-1][1] = directionNum;
            }
            else break;
        }
        directionNum = direction;
        //오른쪽체크
        for(int i=gearNum; i<3; i++){
            if(gear[i][2] != gear[i+1][6]) {
                checkArr[i+1][0] = 1;
                directionNum = - directionNum;
                checkArr[i+1][1] = directionNum;
            }
            else break;
        }
    }
    static void rotationSolution(int[][] gear, int[][] checkArr){
        for(int i=0; i<4; i++){
            //시계 방향 돌리는법
            if(checkArr[i][0] == 1 && checkArr[i][1] == 1){
                int temp = gear[i][0];
                for(int j=0; j < 7 ; j++){
                    int next = gear[i][j+1];
                    gear[i][j+1] = temp;
                    temp = next;
                    if(j == 6) gear[i][0] = temp;
                }
            }
            //반시계 방향 돌리는법
            if(checkArr[i][0] == 1 && checkArr[i][1] == -1){
                int temp = gear[i][7];
                for(int j = 7; j > 0; j--){
                    int next = gear[i][j-1];
                    gear[i][j-1] = temp;
                    temp = next;
                    if(j == 1) gear[i][7] = temp;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] gear = new int[4][8];

        for(int i=0; i<gear.length; i++){
            String[] input = br.readLine().split("");
            for(int j=0; j<gear[0].length; j++){
                gear[i][j] = Integer.parseInt(input[j]);
            }
        }
        int rotation = Integer.parseInt(br.readLine());
        for(int i=0; i<rotation; i++){
            int[] command = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int gearNum = command[0] - 1;
            int direction = command[1];

            //4개 기어를 체크 첫열[0] 에는 돌릴지 체크, [1] 에는 방향 체크
            int[][] checkArr = new int[4][2];
            checkArr[gearNum][0] = 1;
            checkArr[gearNum][1] = direction;
            checkSolution(gear,checkArr,gearNum, direction);
            rotationSolution(gear,checkArr);
        }
        int sum = 0;
        for(int i=0; i<4; i++){
            sum += gear[i][0] * (int)Math.pow(2, i);
        }
        System.out.print(sum);
    }
}
