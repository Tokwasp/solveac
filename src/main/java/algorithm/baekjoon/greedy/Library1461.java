package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Library1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> minusQueue = new PriorityQueue<>((a,b) -> b - a);
        PriorityQueue<Integer> plusQueue = new PriorityQueue<>((a,b) -> b - a);

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num < 0){ minusQueue.add(Math.abs(num));}
            else { plusQueue.add(num); }
        }

        int max = 0;
        if(minusQueue.isEmpty()){
            max = plusQueue.peek();
        }
        else if(plusQueue.isEmpty()){
            max = minusQueue.peek();
        }
        else{
            max = Math.max(plusQueue.peek(), minusQueue.peek());
        }

        int result = 0;
        while(!plusQueue.isEmpty()){
            int dist = plusQueue.poll();
            for(int i = 0; i < M - 1; i++){
                plusQueue.poll();

                if(plusQueue.isEmpty()){
                    break;
                }
            }
            result += dist * 2;
        }

        while(!minusQueue.isEmpty()){
            int dist = minusQueue.poll();
            for(int i = 0; i < M - 1; i++){
                minusQueue.poll();

                if(minusQueue.isEmpty()){
                    break;
                }
            }
            result += dist * 2;
        }

        result -= max;
        System.out.println(result);
    }
}