package algorithm.baekjoon.implement;

import java.io.*;
import java.util.stream.Stream;

public class Bingo2578 {
    static int diagonal(boolean[][] check){
        int count = 0;
        boolean test = false;

        //(마지막행 첫번째)대각선 체크
        for(int i=0; i<5; i++){
            if(check[i][4-i]) {
                test = true;
            }
            else {
                test = false;
                break;
            }
        }
        if(test) count++;

        //(마지막행 마지막열)대각선 체크
        test = false;
        for(int i=0; i<5; i++){
            if(check[i][i]){
                test = true;
            }
            else {
                test = false;
                break;
            }
        }
        if(test) count++;

        return count;
    }

    static int widthCheck(boolean[][] check){
        //가로체크
        boolean test = false;
        int count = 0;
        for(int i=0; i<5; i++) {
            test = false;
            for (int j = 0; j < 5; j++) {
                if (check[i][j]) test = true;
                else {
                    test = false;
                    break;
                }
            }
            if(test) count++;
        }
        return count;
    }

    static int heightCheck(boolean[][] check){
        //세로체크
        boolean test = false;
        int count =0;
        for(int i=0; i<5; i++) {
            test = false;
            for (int j = 0; j < 5; j++) {
                if (check[j][i]) test = true;
                else {
                    test = false;
                    break;
                }
            }
            if(test) count++;
        }
        return count;
    }

    static int bingocheck(boolean[][] check){
        if(diagonal(check) + widthCheck(check) + heightCheck(check) >= 3) return 1;
        else return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] bingo = new int[5][5];

        //데이터 입력
        for(int i=0; i<5; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<input.length; j++){
                bingo[i][j] = input[j];
            }
        }

        boolean[][] check = new boolean[5][5];
        boolean breakPoint = false;
        int index = 0;

        //빙고 체크
        for(int i=0; i<5; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int s=0; s<5; s++) {
                index++;
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (bingo[j][k] == input[s]) check[j][k] = true;
                    }
                }
                if(bingocheck(check) == 1) {
                    System.out.println(index);
                    breakPoint = true;
                    break;
                }
            }
            if(breakPoint) break;
        }
    }
}