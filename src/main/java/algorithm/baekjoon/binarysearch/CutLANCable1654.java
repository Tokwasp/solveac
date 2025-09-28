package algorithm.baekjoon.binarysearch;

import java.io.*;
import java.util.stream.Stream;

public class CutLANCable1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = input[0]; int n = input[1];
        int[] arr = new int[k];

        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        long start = 1;
        long end = Integer.MAX_VALUE;

        while(start <= end){
            long mid = (start + end) / 2;

            if(isPossible(mid, arr, n)){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }

    private static boolean isPossible(long mid, int[] arr, int n){
        long count = 0;
        for(int i = 0; i < arr.length; i++){
            count += arr[i] / mid;
        }
        return count >= n;
    }
}
