package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class decimalSum1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 2) {
            System.out.println(1);
            return;
        }

        List<Integer> decimalList = new ArrayList<>();

        int[] arr = new int[4000001];
        for(int i = 2; i <= 4000000; i++){
            arr[i] = i;
        }

        //에레토스테네스 체
        for(int i = 2; i <= N; i++) {
            for (int j = 1; i * j <= N; j++) {
                if(j == 1 && arr[i * j] != 0) decimalList.add(i);
                else arr[i * j] = 0;
            }
        }

        int[] decimalArr = decimalList.stream().mapToInt(Integer::intValue).toArray();

        int start = 0;
        int end = 0;
        int sum = 0;
        int count = 0;

        while(end < decimalArr.length){
            if(sum < N){
                sum += decimalArr[end++];
            }
            else{
                sum -= decimalArr[start++];
            }
            if(sum == N) {
                count++;
                sum -= decimalArr[start++];
            }
        }
        if(decimalArr.length > 0 && decimalArr[--end] == N) count++;

        System.out.println(count);
    }
}
