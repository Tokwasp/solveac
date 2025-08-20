package algorithm.programmers.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DividePowerGrid {
    private static boolean[] visited;
    private static List<List<Integer>> nodes;

    public int solution(int nodeCount, int[][] wires) {
        nodes = new ArrayList<>();

        // 초기화
        for (int i = 0; i <= nodeCount; i++) {
            nodes.add(new ArrayList<>());
        }

        // 입력
        for (int row = 0; row < wires.length; row++) {
            int startNode = wires[row][0];
            int endNode = wires[row][1];

            // 양방향 연결
            nodes.get(startNode).add(endNode);
            nodes.get(endNode).add(startNode);
        }

        // 노드 하나 끊고 bfs
        int min = Integer.MAX_VALUE;
        for (int row = 0; row < wires.length; row++) {
            int startNode = wires[row][0];
            int endNode = wires[row][1];

            nodes.get(startNode).remove(Integer.valueOf(endNode));
            nodes.get(endNode).remove(Integer.valueOf(startNode));

            // bfs 실행
            visited = new boolean[nodeCount + 1];
            int connect = bfs(endNode);
            int anotherConnect = nodeCount - connect;
            min = Math.min(min, Math.abs(connect - anotherConnect));

            nodes.get(startNode).add(endNode);
            nodes.get(endNode).add(startNode);
        }
        return min;
    }

    private static int bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;
        visited[startNode] = true;

        for (int endNode : nodes.get(startNode)) {
            visited[endNode] = true;
            queue.add(endNode);
            count++;
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int endNode : nodes.get(node)) {
                if (!visited[endNode]) {
                    queue.add(endNode);
                    visited[endNode] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
