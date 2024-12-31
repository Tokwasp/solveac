package algorithm.baekjoon.math;

import java.io.*;
import java.util.*;

public class DecimalRoute1963 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int maxValue = 9999;
        int[] decimal = new int[10000];
        for(int i = 0; i < decimal.length; i++){
            decimal[i] = i;
        }

        for(int i = 2; i <= maxValue; i++){
            if(decimal[i] != 0) {
                for (int j = i * i; j <= maxValue; j += i) {
                    decimal[j] = 0;
                }
            }
        }

        for(int t = 0; t < testCase; t++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{A, 0});
            boolean[] visited = new boolean[10000];
            visited[A] = true;
            boolean pass = false;

            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                int num = cur[0];
                int change = cur[1];

                // 탈출 조건
                if(num == B){
                    sb.append(change).append("\n");
                    pass = true;
                    break;
                }

                for(int i = 0; i < 4; i++){
                    for(int j = 0; j <= 9; j++){
                        if(i == 0 && j == 0){
                            continue;
                        }

                        int next = change(num, i, j);
                        if(!visited[next] && decimal[next] != 0){
                            queue.add(new int[] {next, change + 1});
                            visited[next] = true;
                        }
                    }
                }
            }
            if(!pass){
                sb.append("impossible").append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int change(int num, int index, int changNum){
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.setCharAt(index, String.valueOf(changNum).charAt(0));
        return Integer.parseInt(sb.toString());
    }
}