package algorithm.swea.d3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class PasswordGenerator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder totalSb = new StringBuilder();

        for(int i=1; i<11; i++) {
            int n = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();
            StringBuilder sb = new StringBuilder();

            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int num : input) {
                q.add(num);
            }

            int num = 1;
            while(true){
                if(num == 6) num = 1;
                int offerNum = cycle(q, num);
                if(offerNum == 0) {
                    q.offer(offerNum);
                    break;
                }
                else{
                    q.offer(offerNum);
                    num++;
                }
            }
            while(!q.isEmpty()){
                sb.append(q.poll() + " ");
            }
            String st = "#" + n + " " + sb;
            totalSb.append(st).append("\n");
        }
        System.out.println();
        System.out.print(totalSb);
    }

    static int cycle(Queue<Integer> q, int num){
        if(!q.isEmpty()) {
            int pollNum = q.poll();
            pollNum -= num;

            if(pollNum <= 0) pollNum = 0;
            return pollNum;
        }
        return -1;
    }
}
