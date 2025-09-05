package algorithm.programmers.graph.floyd;

import java.util.Arrays;

public class Delivery {
    private static final int INF = 500001;

    public int solution(int nodeCount, int[][] roads, int limitDistance) {
        int[][] graph = new int[nodeCount + 1][nodeCount + 1];

        // 플로이드 초기화
        for (int i = 1; i <= nodeCount; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        // 입력 받기
        for (int i = 0; i < roads.length; i++) {
            int startNode = roads[i][0];
            int endNode = roads[i][1];
            int distance = roads[i][2];

            // 더 작은 가중치가 있다면
            if (graph[startNode][endNode] > distance) {
                graph[startNode][endNode] = distance;
                graph[endNode][startNode] = distance;
            }
        }

        // 플로이드 실행
        for (int n = 1; n <= nodeCount; n++) {
            for (int row = 1; row <= nodeCount; row++) {
                for (int col = 1; col <= nodeCount; col++) {
                    if (graph[row][col] > graph[row][n] + graph[n][col]) {
                        graph[row][col] = graph[row][n] + graph[n][col];
                    }
                }
            }
        }

        // 거리 체크
        int count = 0;
        for (int i = 1; i <= nodeCount; i++) {
            if (graph[1][i] <= limitDistance) count++;
        }

        return count;
    }
}
