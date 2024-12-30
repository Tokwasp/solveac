package algorithm.baekjoon.math;

import java.io.*;
import java.util.*;

public class DecimalRoute1963 {
    static List<Integer> decimalList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        decimalList = new ArrayList<>();
        int maxTarget = 9999;

        int[] decimalArr = new int[10000];
        for(int i = 0; i < decimalArr.length; i++){
            decimalArr[i] = i;
        }

        for(int i = 2; i <= maxTarget; i++){
            if(decimalArr[i] != 0) {
                for(int j = i * i; j <= maxTarget; j += i){
                    decimalArr[j] = 0;
                }
            }
        }

        for(int t = 0; t < testCase; t++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[10000];
            int[] count = new int[10000];
            
            queue.add(A);
            visited[A] = true;

            while(!queue.isEmpty()){
                int num = queue.poll();
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j <= 9; j++){
                        if(i == 0 && j == 0){
                            continue;
                        }
                        int k = change(num, i, j);
                        if(decimalArr[k] != 0 && !visited[k]){
                            queue.add(k);
                            visited[k] = true;
                            count[k] = count[num] + 1;
                        }
                    }
                }
            }
            sb.append(count[B]).append("\n");
        }
        System.out.print(sb);
    }

    private static int change(int num, int i, int j) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.setCharAt(i, (char) (j + '0'));
        return Integer.parseInt(sb.toString());
    }
}