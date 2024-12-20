package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class LIS14002ArraySolve {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];
        int[] prev = new int[N];

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int max = 1;
        int maxIndex = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }

                if (dp[i] > max) {
                    max = dp[i];
                    maxIndex = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n");
        List<Integer> list = new ArrayList<>();
        while (maxIndex != -1) {
            int value = arr[maxIndex];
            list.add(value);
            maxIndex = prev[maxIndex];
        }

        Collections.reverse(list);
        for (int value : list) {
            sb.append(value).append(" ");
        }
        System.out.print(sb);
    }
}