package algorithm.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AmazingDecimal2023 {
    static int start, end;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        start = (int) Math.pow(10, N - 1);
        end = (int) Math.pow(10, N);

        dfs(0, N);
        System.out.print(sb);
    }

    private static void dfs(int num, int N) {
        if(N == 0){
            if(isDecimal(num) && start <= num && num < end) {
                sb.append(num).append("\n");
            }
            return;
        }

        for(int i = 0; i < 10; i++){
            int next = num * 10 + i;

            if(isDecimal(next)){
                dfs(next, N - 1);
            }
        }
    }

    private static boolean isDecimal(int num) {
        if(num < 2) return false;

        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}