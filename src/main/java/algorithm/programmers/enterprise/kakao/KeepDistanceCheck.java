package algorithm.programmers.enterprise.kakao;

public class KeepDistanceCheck {
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static final char PERSON_SHAPE = 'P';
    private static final char TABLE_SHAPE = 'O';
    private static final int MANHATTAN_DISTANCE = 2;
    private static final int ROOM_SIZE = 5;
    private static boolean flag = false;

    public int[] solution(String[][] places) {
        int[] result = new int[ROOM_SIZE];
        visited = new boolean[ROOM_SIZE][ROOM_SIZE];

        for (int room = 0; room < ROOM_SIZE; room++) {
            String[] place = places[room];
            map = new char[ROOM_SIZE][ROOM_SIZE];

            for (int row = 0; row < ROOM_SIZE; row++) {
                map[row] = place[row].toCharArray();
            }

            flag = false;
            for (int row = 0; row < ROOM_SIZE; row++) {
                for (int col = 0; col < ROOM_SIZE; col++) {
                    if (map[row][col] == PERSON_SHAPE) {
                        visited[row][col] = true;
                        dfs(0, row, col);
                        visited[row][col] = false;
                    }
                }
            }

            result[room] = flag ? 0 : 1;
        }

        return result;
    }

    private static void dfs(int depth, int row, int col) {
        if (depth == MANHATTAN_DISTANCE || flag) {
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (isMapIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == PERSON_SHAPE) {
                flag = true;
                return;
            }

            if (isMapIn(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == TABLE_SHAPE) {
                visited[nextRow][nextCol] = true;
                dfs(depth + 1, nextRow, nextCol);
                visited[nextRow][nextCol] = false;
            }
        }
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < ROOM_SIZE && 0 <= col && col < ROOM_SIZE;
    }
}
