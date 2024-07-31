package algorithm.baekjoon.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RestAreaBuild1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 2];
        if(N != 0) st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[0] = 0; arr[N + 1] = L;

        Arrays.sort(arr);

        int start = 1; int end = L - 1;

        while(start <= end){
            int mid = (start + end) / 2;
            int sum = 0;

            for(int i = 1; i <= N+1; i++){
                sum += (arr[i] - arr[i-1] - 1) / mid;
            }

            if(sum > M) start = mid + 1;
            else end = mid - 1;
        }
        System.out.println(start);
    }
}