package algorithm.programmers.graph.dfs;

public class Fatigue {
    private static boolean[] visited;
    private static int[][] dungeons;
    private static int maxDungeonCount = 0;

    public int solution(int fatigue, int[][] dungeons) {
        this.dungeons = dungeons;

        // 탐색
        this.visited = new boolean[dungeons.length];
        dfs(0, fatigue);
        return maxDungeonCount;
    }

    private static void dfs(int depth, int fatigue) {
        maxDungeonCount = Math.max(maxDungeonCount, depth);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                int minFatigue = dungeons[i][0];
                int useFatigue = dungeons[i][1];
                int nextFatigue = fatigue - useFatigue;

                if (fatigue >= minFatigue) {
                    visited[i] = true;
                    dfs(depth + 1, nextFatigue);
                    visited[i] = false;
                }
            }
        }
    }
}
