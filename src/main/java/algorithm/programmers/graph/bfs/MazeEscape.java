package algorithm.programmers.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class MazeEscape {
    private static char[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public int solution(String[] maps) {
        map = new char[maps.length][maps[0].length()];

        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < maps.length; row++) {
            String colInput = maps[row];

            for (int col = 0; col < colInput.length(); col++) {
                char ch = colInput.charAt(col);

                map[row][col] = ch;
                if (ch == 'S') {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        // 레버 찾기
        int[] lever = bfs(startRow, startCol, true);
        if (lever[0] == -1) {
            return -1;
        }

        // 출구 찾기
        int[] result = bfs(lever[0], lever[1], false);
        if (result[0] == -1) {
            return -1;
        }

        int leverDist = lever[2];
        return leverDist + result[0];
    }

    private static int[] bfs(int startRow, int startCol, boolean findLever) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol, 0});

        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int row = poll[0];
            int col = poll[1];
            int dist = poll[2];

            if (findLever && map[row][col] == 'L') {
                return new int[]{row, col, dist};
            }

            if (!findLever && map[row][col] == 'E') {
                return new int[]{dist};
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];
                int nextDist = dist + 1;

                if (!isMapIn(nextRow, nextCol) || visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == 'X') continue;

                queue.add(new int[]{nextRow, nextCol, nextDist});
                visited[nextRow][nextCol] = true;
            }
        }

        // 레버를 찾지 못했거나 출구로 못간다면
        return new int[]{-1};
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }
}
