package algorithm.programmers.graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DesertIslandTravel {
    private static List<Integer> islands;

    private static boolean[][] visited;
    private static char[][] map;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[] maps) {
        islands = new ArrayList<>();
        map = new char[maps.length][maps[0].length()];

        // 입력 받기
        for (int i = 0; i < maps.length; i++) {
            String input = maps[i];

            for (int j = 0; j < input.length(); j++) {
                map[i][j] = input.charAt(j);
            }
        }

        // 섬 찾기
        visited = new boolean[map.length][map[0].length];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] != 'X' && !visited[row][col]) {
                    islands.add(calculateTotalDays(row, col));
                }
            }
        }

        // 섬이 없다면
        if (islands.isEmpty()) {
            return new int[]{-1};
        }

        // 정렬
        islands.sort((a, b) -> a - b);
        return islands.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int calculateTotalDays(int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        int totalDay = map[startRow][startCol] - '0';
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];

            for (int i = 0; i < dx.length; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (!isMapIn(nextRow, nextCol)) continue;

                if (map[nextRow][nextCol] != 'X' && !visited[nextRow][nextCol]) {
                    queue.add(new int[]{nextRow, nextCol});
                    totalDay += map[nextRow][nextCol] - '0';
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return totalDay;
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }
}
