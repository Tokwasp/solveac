package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class PowerShortage6497 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) { // 여러 테스트 케이스 처리 가능
            String line = br.readLine();

            StringTokenizer st = new StringTokenizer(line, " ");
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break; // 종료 조건

            PriorityQueue<Node> pq = new PriorityQueue<>();
            int totalWeight = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                pq.add(new Node(start, end, weight));
                totalWeight += weight;
            }

            parents = new int[m];
            for (int i = 0; i < m; i++) {
                parents[i] = i;
            }

            int minWeight = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int parents1 = getParents(cur.start);
                int parents2 = getParents(cur.end);

                if (parents1 != parents2) {
                    connectParents(parents1, parents2);
                    minWeight += cur.weight;
                }
            }
            System.out.println(totalWeight - minWeight);
        }
    }

    private static void connectParents(int prev, int cur) {
        if (prev < cur) {
            parents[cur] = prev;
        } else {
            parents[prev] = cur;
        }
    }

    private static int getParents(int num) {
        if (parents[num] == num) {
            return num;
        }
        return parents[num] = getParents(parents[num]);
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(weight, node.weight);
        }
    }
}