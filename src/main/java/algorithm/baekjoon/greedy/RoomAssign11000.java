package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class RoomAssign11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

        list.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list.get(0)[1]);

        for (int i = 1; i < N; i++) {
            int[] current = list.get(i);
            if (pq.peek() <= current[0]) {
                pq.poll();
            }
            pq.add(current[1]);
        }

        System.out.println(pq.size());
    }
}