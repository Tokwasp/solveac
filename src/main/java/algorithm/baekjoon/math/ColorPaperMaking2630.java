package algorithm.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class ColorPaperMaking2630 {
    static int blue = 0;
    static int white = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

         map = new int[N][];

        for(int i = 0; i < N; i++) map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        partition(0,0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    static void partition(int row, int col, int size){

        int color = map[row][col];
        if(AllColorEquals(row,col,size)){
            if(color == 1) blue++; else white++;
            return;
        }

        int newSize = size / 2;

        // 1사분면
        partition(row, col + newSize, newSize);
        // 2사분면
        partition(row, col, newSize);
        // 3사분면
        partition(row + newSize, col, newSize);
        // 4사분면
        partition(row + newSize, col + newSize, newSize);

    }

    static boolean AllColorEquals(int row, int col, int size){
        int color = map[row][col];

        for(int i = row; i < row + size; i++){
            for(int j = col; j < col + size; j++){
                if(color != map[i][j]) return false;
            }
        }
        return true;
    }
}
