package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Three2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0; int end = N-1;
        long min = Long.MAX_VALUE;

        Arrays.sort(arr);
        List<Long> resultList = new ArrayList<>();

        for(int i = 0; i < N -2; i++){
            int point = i;
            int left = point + 1; int right = N-1;

            while(left < right){

                long sum = arr[point] + arr[left] + arr[right];

                if(Math.abs(sum) < min){
                    min = Math.abs(sum);
                    resultList = new ArrayList<>(Arrays.asList(arr[point],arr[left],arr[right]));
                }

                if(sum < 0) left++;
                else right--;
            }
        }

        Collections.sort(resultList);

        for (Long i : resultList) {
            System.out.print(i + " ");
        }
    }
}
