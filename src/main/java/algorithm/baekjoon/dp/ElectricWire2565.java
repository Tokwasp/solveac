package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class ElectricWire2565 {
    static class Node{
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Node> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(start,end));
        }
        list.sort(Comparator.comparingInt(Node::getStart));

        int[] dp = new int[N];
        for(int i = 0; i < N; i++) Arrays.fill(dp, 1);

        int max = 1;
        for(int i = 0; i < N; i++){
            Node node = list.get(i);
            for(int j = 0; j < i; j++){
                Node compareNode = list.get(j);
                if(node.end > compareNode.end){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i],max);
                }
            }
        }
        System.out.println(N-max);
    }
}