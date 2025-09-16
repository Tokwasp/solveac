package algorithm.programmers.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class GameMapShortestDist {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        return bfs(0, 0, maps);
    }

    private static int bfs(int startRow, int startCol, int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        visited[startRow][startCol] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol, 1});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];
            int dist = poll[2];

            if (isDestination(row, col, maps)) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];
                int nextDist = dist + 1;

                if (!isMapIn(nextRow, nextCol, maps)) continue;
                if (maps[nextRow][nextCol] == 0 || visited[nextRow][nextCol]) continue;

                queue.add(new int[]{nextRow, nextCol, nextDist});
                visited[nextRow][nextCol] = true;
            }
        }
        return -1;
    }

    private static boolean isDestination(int row, int col, int[][] map) {
        return row == map.length - 1 && col == map[0].length - 1;
    }

    private static boolean isMapIn(int row, int col, int[][] map) {
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }
}
