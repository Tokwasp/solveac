package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Truck13335 {
    static class Truck{
        int index;
        int weight;

        public Truck(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    static int weightCheck(Queue<Truck> q){
        int weight = 0;
        for(Truck t : q){
            weight += t.weight;
        }
        return weight;
    }


    static void indexAdd(Queue<Truck> q){
        for(Truck t : q){
            t.index++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int w = input[1];
        int l = input[2];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Queue<Truck> q = new LinkedList<>();
        int wCount = 0;

        for(int i=0; i<n; i++) {
            int weight = Integer.parseInt(st.nextToken());
            //최대 하중체크
            if(weightCheck(q) + weight <= l) {
                indexAdd(q);
                q.offer(new Truck(1, weight));
                wCount++;
                if(!q.isEmpty() && q.peek().index == w+1) q.poll();
            }
            else{
                while(!(weightCheck(q) + weight <= l)) {
                    indexAdd(q);
                    wCount++;
                    if(!q.isEmpty() && q.peek().index == w+1) q.poll();
                }
                q.offer(new Truck(1,weight));
            }
        }
        System.out.print(w+wCount);
    }
}
