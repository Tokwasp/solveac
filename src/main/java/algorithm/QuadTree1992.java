package algorithm;

import java.io.*;
import java.util.stream.Stream;

public class QuadTree1992 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new int[N][];

        for(int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        partition(0 ,0 ,N);


        System.out.println(sb);
    }

    static void partition(int row, int col, int size){

        if(equalsColor(row,col,size)){
            sb.append(map[row][col]);
            return;
        }
        else{
            sb.append("(");
        }

        int newSize = size / 2;

        // 2사분면
        partition(row,col, newSize);
        // 1사분면
        partition(row, col+ newSize, newSize);
        // 3사분면
        partition(row + newSize, col , newSize);
        // 4사분면
        partition(row + newSize, col + newSize, newSize);

        sb.append(")");
    }

    static boolean equalsColor(int row, int col, int size){

        int color = map[row][col];

        for(int i = row; i < row + size; i++){
            for(int j = col; j < col + size; j++){
                if(color != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
