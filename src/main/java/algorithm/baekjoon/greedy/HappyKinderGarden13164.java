package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class HappyKinderGarden13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] diff = new int[N-1];

        for(int i = 1; i < N; i++){
            diff[i-1] = arr[i] - arr[i-1];
        }
        Arrays.sort(diff);

        int sum = 0;
        for(int i = 0; i < N-K; i++) sum += diff[i];
        System.out.println(sum);
    }
}