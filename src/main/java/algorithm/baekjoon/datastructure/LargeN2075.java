package algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class LargeN2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue(Comparator.reverseOrder());
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }
        for(int i = 0; i < N-1; i++){
            pq.poll();
        }
        System.out.println(pq.poll());
    }
}
