package algorithm.baekjoon.math;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class PrefixSum11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sum = new int[arr.length];

        sum[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            sum[i] = sum[i-1] + arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            if(start == 0){
                sb.append(sum[end]).append("\n");
            }
            else {
                sb.append(sum[end] - sum[start - 1]).append("\n");
            }
        }
        System.out.print(sb);
    }
}