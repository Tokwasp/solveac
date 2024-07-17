package algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class MinPrice11779 {
    static final int INF = 150000000;
    static boolean[] visited;
    static int[][] graph;
    static int[] dist;
    static int[] pre;
    static int N, start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) graph[i][i] = 0;
                else graph[i][j] = INF;
            }
        }

        for(int i = 1; i <= busCount; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startNode = input[0]; int endNode = input[1]; int price = input[2];

            graph[startNode][endNode] = Math.min(graph[startNode][endNode], price);
        }

        dist = new int[N + 1];

        for(int i = 1; i < dist.length; i++){
            dist[i] = INF;
        }

        visited = new boolean[N + 1];
        pre = new int[N + 1];
        Arrays.fill(pre, -1);

        int[] startEnd = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        start = startEnd[0]; end = startEnd[1];

        dijkstra(start);

        StringBuilder sb = new StringBuilder();

        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = pre[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        for (int node : path) {
            sb.append(node).append(" ");
        }

        System.out.println(dist[end]);
        System.out.println(path.size());
        System.out.println(sb.toString().trim());

    }

    static void dijkstra(int start){
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
        pq.add(new int[] {0,start});

        while(!pq.isEmpty()){
            int[] poll = pq.poll();
            int d = poll[0]; int u = poll[1];

            if(visited[u]) continue;
            visited[u] = true;

            for(int v = 1; v <= N; v++){
                if(dist[v] > dist[u] + graph[u][v]){
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new int[] {dist[v], v});
                    pre[v] = u;
                }
            }
        }
    }
}