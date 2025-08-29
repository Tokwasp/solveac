package algorithm.programmers.graph.bfs;

import java.util.*;

public class OilDrilling {
    private static int[] sum;
    private static int[][] land;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] land) {
        this.land = land;
        sum = new int[land[0].length];
        visited = new boolean[land.length][land[0].length];

        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[0].length; col++) {
                if (land[row][col] == 1 && !visited[row][col]) {
                    bfs(row, col);
                }
            }
        }

        return Arrays.stream(sum).max().getAsInt();
    }

    private static void bfs(int startRow, int startCol) {
        Set<Integer> colSet = new HashSet<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];
            colSet.add(col);
            count++;

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (!isMapIn(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol]) continue;

                if (land[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }

        for (int col : colSet) {
            sum[col] += count;
        }
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < land.length && 0 <= col && col < land[0].length;
    }
}
