package algorithm.swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoQueen {

    static int[] arr;
    static int N;
    static int count = 0;

    public static void main(String[] args) throws IOException {

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

       int T = Integer.parseInt(br.readLine());
       for(int i = 1; i <= T; i++) {
           N = Integer.parseInt(br.readLine());
           arr = new int[N];
           count = 0;

           dfs(0);
           String st = "#" + i + " " + count;
           sb.append(st).append("\n");
       }
        System.out.print(sb);
    }

    public static void dfs(int col) {

        if (col == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[col] = i;
            if (checkArr(col)) {
                dfs(col + 1);
            }
        }

    }

    public static boolean checkArr(int col) {
        if(col == 0) return true;

        for (int i = 0; i < col; i++) {
            //같은 행 제외
            if(arr[i] == arr[col]) return false;

            // (행 - 열) -> 왼쪽 대각선 제외
            if(arr[i] - i == arr[col] - col) return false;

            // (행 + 열) -> 오른쪽 대각선 제외
            if(arr[i] + i == arr[col] + col) return false;
        }

        return true;
    }
}
