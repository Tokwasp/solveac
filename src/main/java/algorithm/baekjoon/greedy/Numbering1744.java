package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Numbering1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 내림 차순 정렬
        Arrays.sort(arr, Collections.reverseOrder());

        int plusPointer = 0;
        int minusPointer = arr.length - 1;

        int sum = 0;
        Queue<Integer> q = new LinkedList<>();

        // 양수 케이스
        while(plusPointer <= minusPointer && arr[plusPointer] > 0){
            if(q.isEmpty()) q.add(arr[plusPointer++]);
            else{
                int num = q.poll();
                sum += Math.max(num * arr[plusPointer], num + arr[plusPointer]);
                plusPointer++;
            }
        }
        while(!q.isEmpty()) sum += q.poll();

        // 음수 케이스 ( 0 포함 )
        while(minusPointer >= plusPointer && arr[minusPointer] <= 0){
            if(q.isEmpty()) q.add(arr[minusPointer--]);
            else{
                int num = q.poll();
                sum += num * arr[minusPointer--];
            }
        }
        while(!q.isEmpty()) sum += q.poll();

        System.out.println(sum);
    }
}