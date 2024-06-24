package algorithm.baekjoon.dijkstra;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MinPrice1916 {
    static int INF = 200000000;
    static boolean[] visited;
    static int[] dist;
    static int[][] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(br.readLine()); int line = Integer.parseInt(br.readLine());

        graph = new int[nodeCount + 1][nodeCount + 1];
        visited = new boolean[nodeCount + 1];
        dist = new int[nodeCount + 1];

        // 시작 -> 끝 노드 비용 초기화
        for(int i = 1; i <= nodeCount; i++){
            for(int j = 1; j <= nodeCount; j++){
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        // 시작 -> 끝 노드 비용 입력
        for(int i = 1; i <= line; i++){
            int[] lineInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startNode = lineInput[0]; int endNode = lineInput[1]; int price = lineInput[2];

            graph[startNode][endNode] = Math.min(price, graph[startNode][endNode]);
        }

        // 출발 도시와 도착 도시 입력 배열
        int[] targetInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = targetInput[0]; int end = targetInput[1];

        dijkstra(start, nodeCount);

        System.out.println(dist[end]);
    }

    static void dijkstra(int startNode, int nodeCount){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 거리 배열 초기화
        for(int i = 1; i <= nodeCount; i++){
            dist[i] = INF;
        }

        dist[startNode] = 0;
        pq.add(new int[] {0, startNode});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int u = curr[1];

            if(visited[u]) continue;

            visited[u] = true;

            for(int v = 1; v <= nodeCount; v++){

                if(dist[v] > graph[u][v] + dist[u]){
                    dist[v] = graph[u][v] + dist[u];
                    pq.add(new int[] {dist[v],v});
                }
            }
        }
    }
}
