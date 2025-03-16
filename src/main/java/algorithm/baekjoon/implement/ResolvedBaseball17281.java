package algorithm.baekjoon.implement;

import java.io.*;
import java.util.StringTokenizer;

public class ResolvedBaseball17281 {
    public static final int HIT_MAN_COUNT = 9;
    static int[][] inningResult;
    static int inningCount;
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inningCount = Integer.parseInt(br.readLine());
        inningResult = new int[inningCount + 1][HIT_MAN_COUNT + 1];

        // 입력 받기
        for(int inning = 1; inning <= inningCount; inning++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int hitManNum = 1; hitManNum <= HIT_MAN_COUNT; hitManNum++) {
                inningResult[inning][hitManNum] = Integer.parseInt(st.nextToken());
            }
        }

        int[] order = new int[HIT_MAN_COUNT + 1];
        boolean[] select = new boolean[HIT_MAN_COUNT + 1];

        select[4] = true;
        order[4] = 1;

        orderDecisionAndBaseBallGame(order, select, 2);
        System.out.print(maxScore);
    }

    public static void orderDecisionAndBaseBallGame(int[] order, boolean[] select, int hitManNum){
        if(hitManNum == HIT_MAN_COUNT + 1){
            BaseBallGame(order);
            return;
        }

        for(int i = 1; i <= 9; i++){
            if(!select[i]){
                select[i] = true;
                order[i] = hitManNum;
                orderDecisionAndBaseBallGame(order,select,hitManNum + 1);
                select[i] = false;
            }
        }
    }

    private static void BaseBallGame(int[] order) {
        int hitManIndex = 0;

        int score = 0;

        for(int inning = 1; inning <= inningCount; inning++) {
            boolean[] roo = new boolean[4];
            int outCount = 0;

            while(outCount != 3) {
                hitManIndex = hitManIndex % 9 + 1;
                int hitManNum = order[hitManIndex];

                int result = inningResult[inning][hitManNum];
                if (result == 0) {
                    outCount++;
                } else {
                    score += run(roo, result);
                }
            }
        }
        maxScore = Math.max(maxScore, score);
    }

    static int run(boolean[] roo, int result){
        int Point = 0;

        if(anta(result)){
            for(int i = 3; i > 0; i--){
                if(roo[i]){
                    if(i + result >= 4) {
                        Point++;
                        roo[i] = false;
                    }
                    else{
                        roo[i] = false;
                        roo[result + i] = true;
                    }
                }
            }
            if(homeRun(result)) Point++;
            else roo[result] = true;
        }

        return Point;
    }

    private static boolean homeRun(int result) {
        return result == 4;
    }

    private static boolean anta(int result) {
        return result == 1 || result == 2 || result == 3 || homeRun(result);
    }
}