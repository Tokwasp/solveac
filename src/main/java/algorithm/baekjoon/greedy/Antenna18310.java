package algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Antenna18310 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dist = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) dist[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(dist);
        int result = 0;

        if(N%2 == 0) result = dist[(N/2)-1];
        else result = dist[N/2];
        System.out.println(result);

        /* 시간복잡도 N(20만 * 20만)제곱이라 초과
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0; i<N; i++) {
            sum = 0;
            for (int j = 0; j < N; j++) {
                sum += Math.abs(dist[i] - dist[j]);
            }
            if(min == sum) continue;
            min = Math.min(min, sum);
            if (min == sum) result = dist[i];
        }
        */
    }
}
