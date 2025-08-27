package algorithm.programmers.graph.dfs;

public class Network {
    public int solution(int computerCount, int[][] computers) {
        boolean[] visited = new boolean[computerCount];
        int networkCount = 0;

        for (int i = 0; i < computerCount; i++) {
            if (!visited[i]) {
                dfs(i, visited, computers);
                networkCount++;
            }
        }

        return networkCount;
    }

    private static void dfs(int currentNode, boolean[] visited, int[][] computers) {
        visited[currentNode] = true;

        int[] connectNodes = computers[currentNode];
        for (int nextNode = 0; nextNode < connectNodes.length; nextNode++) {
            int connected = connectNodes[nextNode];

            if (!visited[nextNode] && connected == 1) {
                dfs(nextNode, visited, computers);
            }
        }
    }
}
