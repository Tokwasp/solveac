package algorithm.baekjoon.twopointer;

import java.io.*;
import java.util.stream.Stream;

public class ResolvedPrefixSum1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numberCount = input[0];
        int targetSum = input[1];

        // 입력 받기
        int[] numberArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (end <= numberArr.length - 1) {
            sum += numberArr[end];
            if (sum >= targetSum) {
                while (targetSum <= sum - numberArr[start]) {
                    sum -= numberArr[start];
                    start++;
                }
                minLength = Math.min(minLength, end - start + 1);
            }
            end++;
        }
        System.out.print(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }
}