package algorithm.baekjoon.math;

import java.io.*;

public class AmazingPrime {
    private static StringBuilder sb;
    private static String[] remember;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        remember = new String[n];
        dfs(0, n);
        System.out.print(sb.toString());
    }

    private static void dfs(int depth, int targetDepth){
        if(depth == targetDepth){
            int num = convertInt(remember);

            if(isPrime(num)){
                sb.append(num).append("\n");
            }
            return;
        }

        for(int i = depth == 0 ? 1 : 0; i <= 9; i++){
            remember[depth] = String.valueOf(i);

            int num = convertInt(remember, depth);
            if(isPrime(num)){
                dfs(depth + 1, targetDepth);
            }
        }
    }

    private static int convertInt(String[] arr, int depth){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= depth; i++){
            sb.append(arr[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    private static int convertInt(String[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    private static boolean isPrime(int num){
        if(num == 1) return false;

        int sqrt = (int) Math.sqrt(num);
        for(int i = 2; i <= sqrt; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
