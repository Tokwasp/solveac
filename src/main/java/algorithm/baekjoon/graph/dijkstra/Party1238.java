package algorithm.baekjoon.graph.dijkstra;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Party1238 {
    static int INFINITE = 1000001;
    static ArrayList<ArrayList<Node>> list, reverseList;
    static int N, M, X;
    static int[] dist, reverseDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        X = input[2];

        list = new ArrayList<>();
        reverseList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] connectWeight = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = connectWeight[0];
            int end = connectWeight[1];
            int weight = connectWeight[2];

            list.get(start).add(new Node(end, weight));
            reverseList.get(end).add(new Node(start, weight));
        }

        dist = dijkstra(list);
        reverseDist = dijkstra(reverseList);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist[i] + reverseDist[i]);
        }

        System.out.println(ans);
    }

    static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
        pq.offer(new Node(X, 0));

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, INFINITE);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.end;

            if (visited[cur]) continue;
            visited[cur] = true;

            ArrayList<Node> nextList = list.get(cur);
            for (Node nextNode : nextList) {
                if (dist[nextNode.end] > dist[cur] + nextNode.weight) {
                    dist[nextNode.end] = dist[cur] + nextNode.weight;
                    pq.add(new Node(nextNode.end, dist[nextNode.end]));
                }
            }

        }
        return dist;
    }

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }
}
