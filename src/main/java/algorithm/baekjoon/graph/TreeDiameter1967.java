package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class TreeDiameter1967 {
    static List<List<Node>> connect;
    static boolean[] visited;
    static int max = 0;
    static int max_idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 연결 리스트 초기화
        connect = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            connect.add(new ArrayList<>());
        }

        // 입력 받기
        for(int i = 1; i <= N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무방향
            connect.get(start).add(new Node(end,weight));
            connect.get(end).add(new Node(start, weight));
        }

        visited = new boolean[N + 1];

        /* n^2 풀이
        // 모든 노드 깊이 탐색
        for(int i = 1; i <= N; i++){
            visited[i] = true;
            dfs(i, 0);
            visited[i] = false;
        }
        */
        visited[1] = true;
        dfs(1, 0);
        visited[1] = false;

        visited[max_idx] = true;
        dfs(max_idx, 0);
        System.out.print(max);
    }

    static void dfs(int idx, int total){
        if(max < total) {
            max = total;
            max_idx = idx;
        }
        List<Node> next = connect.get(idx);

        for(Node nextNode : next){
            int target = nextNode.end;
            if(!visited[target]){
                visited[target] = true;
                dfs(target, total + nextNode.weight);
                visited[target] = false;
            }
        }
    }

    static class Node{
        int end;
        int weight;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
}