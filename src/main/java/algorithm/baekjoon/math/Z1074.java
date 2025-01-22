package algorithm.baekjoon.math;

import java.io.*;
import java.util.StringTokenizer;

public class Z1074 {
    static int drawCount = 0;
    static int r = 0, c = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        partition(0, 0, (int) Math.pow(2,n));
    }

    static void partition(int row, int col, int size) {
        if(size == 1){
            System.out.println(drawCount);
            return;
        }

        int nextSize = size / 2;
        int prevTotalExtent = nextSize * nextSize;

        // 2사분면
        if(r < row + nextSize && c < col + nextSize){
            partition(row, col, nextSize);
        }
        // 1사분면
        else if(r < row + nextSize && c >= col + nextSize){
            drawCount += prevTotalExtent;
            partition(row, col + nextSize, nextSize);
        }
        // 3사분면
        else if(r >= row + nextSize && c < col + nextSize){
            drawCount += prevTotalExtent * 2;
            partition(row + nextSize, col, nextSize);
        }
        // 4사분면
        else{
            drawCount += prevTotalExtent * 3;
            partition(row + nextSize, col + nextSize, nextSize);
        }
    }
}