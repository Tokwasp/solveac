package algorithm.programmers.graph.bfs;

import java.util.*;

public class KakaoFriendsColoringBook {
    private static List<Integer> pictureList;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public int[] solution(int m, int n, int[][] picture) {
        this.pictureList = new ArrayList<>();
        this.map = picture;
        this.visited = new boolean[picture.length][picture[0].length];

        for (int row = 0; row < picture.length; row++) {
            for (int col = 0; col < picture[0].length; col++) {
                if (!visited[row][col] && map[row][col] != 0) {
                    int pictureCount = bfs(row, col, map[row][col]);
                    pictureList.add(pictureCount);
                }
            }
        }

        pictureList.sort(Comparator.reverseOrder());
        if (pictureList.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{pictureList.size(), pictureList.get(0)};
    }

    private static int bfs(int startRow, int startCol, int target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int pictureCount = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];
            pictureCount++;

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                // 맵 밖
                if (!isMapIn(nextRow, nextCol)) continue;

                // 이미 방문
                if (visited[nextRow][nextCol]) continue;

                if (map[nextRow][nextCol] == target) {
                    queue.add(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return pictureCount;
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }
}
