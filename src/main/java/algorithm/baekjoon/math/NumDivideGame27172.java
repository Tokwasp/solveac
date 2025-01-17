package algorithm.baekjoon.math;

import java.io.*;
import java.util.StringTokenizer;

public class NumDivideGame27172 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] count = new int[1_000_001];
        int[] input = new int[N + 1];
        int[] result = new int[1_000_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            input[i] = Integer.parseInt(st.nextToken());
            count[input[i]] = 1;
        }

        for(int i = 1; i <= N; i++){
            int value = input[i];

            for(int repeat = value * 2; repeat <= 1_000_000; repeat += value){
                if(count[repeat] == 1){
                    result[value] += 1;
                    result[repeat] -= 1;
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(result[input[i]]).append(" ");
        }
        System.out.print(sb);
    }
}