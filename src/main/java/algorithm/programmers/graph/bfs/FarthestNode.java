package algorithm.programmers.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FarthestNode {
    public int solution(int nodeCount, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        // 초기화
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int startNode = edges[i][0];
            int endNode = edges[i][1];

            graph.get(startNode).add(endNode);
            graph.get(endNode).add(startNode);
        }

        boolean[] visited = new boolean[nodeCount + 1];
        visited[1] = true;

        List<int[]> remember = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        remember.add(new int[]{1, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curNode = poll[0];
            int dist = poll[1];

            List<Integer> nextNodes = graph.get(curNode);
            for (int nextNode : nextNodes) {
                if (!visited[nextNode]) {
                    int[] nextArr = new int[]{nextNode, dist + 1};
                    queue.add(nextArr);
                    visited[nextNode] = true;
                    remember.add(nextArr);
                }
            }
        }

        remember.sort((a, b) -> b[1] - a[1]);
        List<Integer> results = new ArrayList<>();
        int max = remember.get(0)[1];
        if (max == 0) return 0;

        int count = 1;
        for (int i = 1; i < remember.size(); i++) {
            int curMax = remember.get(i)[1];
            int node = remember.get(i)[0];

            if (max != curMax) break;
            if (node == 1) continue;
            count++;
        }

        return count;
    }
}
