package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class NetworkConnection1922 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node node) -> node.weight));

        for(int i = 1; i <= M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Node(start,end,weight));
        }

        parents = new int[N + 1];
        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }

        int connectCount = 0;
        int connectFee = 0;
        while(!pq.isEmpty() && connectCount != N - 1){
            Node curNode = pq.poll();

            if(getParents(curNode.start) != getParents(curNode.end)){
                connect(curNode.start, curNode.end);
                connectCount++;
                connectFee += curNode.weight;
            }
        }
        System.out.print(connectFee);
    }

    static int getParents(int x){
        if(parents[x] == x) return x;
        return parents[x] = getParents(parents[x]);
    }

    static void connect(int node, int secondNode){
        int nodeA = getParents(node);
        int nodeB = getParents(secondNode);

        if(nodeA < nodeB){
            parents[nodeB] = nodeA;
        }
        else{
            parents[nodeA] = nodeB;
        }
    }

    static class Node{
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
