package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class RunningWithBeer9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < testCaseNum; t++){
            int n = Integer.parseInt(br.readLine());

            Node[] nodes = new Node[n + 2];
            for(int i = 0; i < n + 2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                nodes[i] = new Node(start,end);
            }
            List<List<Integer>> connectList = new ArrayList<>();

            for(int i = 0; i < n + 2; i++){
                connectList.add(new ArrayList<>());
            }

            for(int i = 0; i < n + 2; i++){
                for(int j = i + 1; j < n + 2; j++){
                    if(distCalculate(nodes[i], nodes[j]) <= 1000){
                        connectList.get(i).add(j);
                        connectList.get(j).add(i);
                    }
                }
            }
            boolean result = bfs(connectList, n);
            if(result){
                sb.append("happy");
            }
            else{
                sb.append("sad");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static int distCalculate(Node node1, Node node2){
        return Math.abs(node1.row - node2.row) + Math.abs(node1.col - node2.col);
    }

    private static boolean bfs(List<List<Integer>> connectList, int destination){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        boolean[] visited = new boolean[destination + 2];
        visited[0] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == destination + 1){
                return true;
            }

            for(int next :connectList.get(cur)){
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        return false;
    }

    static class Node{
        int row;
        int col;

        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}