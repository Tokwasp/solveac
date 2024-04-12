package algorithm.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Josephus1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int[] numArr = Stream.iterate(1, a -> a+1).limit(n).mapToInt(Integer::intValue).toArray();
        for(int i : numArr) q.offer(i);

        int count = 0;
        while(!q.isEmpty()){
            int num = q.poll();
            count++;
            if(count == k) {
                count = 0;
                sb.append(num).append(", ");
                continue;
            }
            q.offer(num);
        }
        System.out.println("<" + sb.delete(sb.length()-2,sb.length()) + ">");
    }
}
