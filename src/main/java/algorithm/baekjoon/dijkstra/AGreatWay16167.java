package algorithm.baekjoon.dijkstra;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class AGreatWay16167 {
    static int INF = 200000000;
    static int[][] graph;
    static boolean[] visited;
    static int[] dist;
    static int[] position;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); int pathCount = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        dist = new int[N+1];
        visited = new boolean[N+1];
        position = new int[N+1];

        // 그래프 값 초기화
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        // 경로, 비용 그래프에 넣는 과정
        for(int i = 1; i <= pathCount; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startNode = input[0]; int endNode = input[1];
            int tenMinuteFee = input[2]; int oneMinuteFee = input[3];
            int totalTime = input[4];

            int price;
            if(totalTime > 10) price = tenMinuteFee + (totalTime - 10) * oneMinuteFee;
            else price = tenMinuteFee;
            graph[startNode][endNode] = Math.min(graph[startNode][endNode],price);
        }

        Dijkstra(1, N);

        if(dist[N] != INF) System.out.println(dist[N] + " " + position[N]);
        else System.out.println("It is not a great way.");
    }

    static void Dijkstra(int src, int N){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for(int i = 1; i <= N; i++) dist[i] = INF;
        dist[src] = 0;
        pq.add(new int[] {0, src, 1});

        for(int i = 1; i <= N; i++){
            position[i] = Integer.MAX_VALUE;
        }
        position[src] = 1;

        while(!pq.isEmpty()){

            int[] curr = pq.poll();
            int u = curr[1];

            if(visited[u]) continue;

            visited[u] = true;

            for(int v = 1; v <= N; v++){

                //최단 거리 갱신 시 그 거리가 가장 짧은 길이 이다.
                if(dist[v] > dist[u] + graph[u][v]){
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new int[] {dist[v], v, curr[2] + 1});
                    position[v] = curr[2] + 1;
                    continue;
                }

                //최단 거리 갱신 하지 못 했지만 거리가 같은 경우
                if(graph[u][v] != 0 && graph[u][v] != INF && dist[v] == dist[u] + graph[u][v]){
                    position[v] = Math.min(position[v], curr[2] + 1);
                }
            }
        }
    }
}
