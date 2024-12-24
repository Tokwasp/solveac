package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class RunningWithBeer9205 {
    static ArrayList<Node> list;
    static ArrayList<ArrayList<Integer>> graph;
    static int store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < testCase; i++){
            store = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            for(int j = 0; j < store + 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                list.add(new Node(row,col));
            }
            graph = new ArrayList<>();
            for(int j = 0 ; j < store + 2; j++){
                graph.add(new ArrayList<>());
            }

            for(int j = 0; j < store + 2; j++){
                for(int k = j + 1; k < store + 2; k++){
                    if(totalDist(list.get(j),list.get(k)) <= 1000){
                        graph.get(j).add(k);
                        graph.get(k).add(j);
                    }
                }
            }
            sb.append((bfs() ? "happy" : "sad") + "\n");
        }
        System.out.print(sb);
    }

    private static int totalDist(Node node1, Node node2) {
        return Math.abs(node1.row - node2.row) + Math.abs(node1.col - node2.col);
    }

    public static boolean bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        boolean[] visited = new boolean[store + 2];
        visited[0] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            if(now == store + 1){
                return true;
            }

            for (int next : graph.get(now)) {
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(next);
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