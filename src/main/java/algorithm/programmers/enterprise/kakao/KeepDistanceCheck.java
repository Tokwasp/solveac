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

    // 8/30 재풀이
//    private static final char PERSON_SHAPE = 'P';
//    private static final char PARTITION_SHAPE = 'X';
//    private static final int SAFE_DISTANCE = 2;
//
//    private static String[][] places;
//    private static int[] dx = {-1, 1, 0, 0};
//    private static int[] dy = {0, 0, -1, 1};
//
//    public int[] solution(String[][] places) {
//        this.places = places;
//        int[] result = new int[5];
//
//        for (int roomNum = 0; roomNum < places.length; roomNum++) {
//            String[] room = places[roomNum];
//            result[roomNum] = isSafeRoom(room, roomNum);
//        }
//        return result;
//    }
//
//    private static int isSafeRoom(String[] room, int roomNum) {
//        for (int rowNum = 0; rowNum < room.length; rowNum++) {
//            String row = room[rowNum];
//
//            for (int col = 0; col < row.length(); col++) {
//                char shape = row.charAt(col);
//
//                // 사람이면 안전 거리 체크
//                if (shape == PERSON_SHAPE) {
//                    if (isSafeDistance(roomNum, rowNum, col) == 0) {
//                        return 0;
//                    }
//                }
//            }
//        }
//        return 1;
//    }
//
//    private static int isSafeDistance(int roomNum, int startRow, int startCol) {
//        String[] room = places[roomNum];
//        boolean[][] visited = new boolean[5][5];
//
//        Queue<int[]> queue = new LinkedList<>();
//        visited[startRow][startCol] = true;
//        queue.add(new int[]{startRow, startCol, 0});
//
//        while (!queue.isEmpty()) {
//            int[] poll = queue.poll();
//            int row = poll[0];
//            int col = poll[1];
//            int dist = poll[2];
//
//            if (dist >= SAFE_DISTANCE) {
//                continue;
//            }
//
//            for (int i = 0; i < dx.length; i++) {
//                int nextRow = row + dx[i];
//                int nextCol = col + dy[i];
//
//                // 맵 밖이면 제외
//                if (!isMapIn(nextRow, nextCol) || visited[nextRow][nextCol]) continue;
//
//                char shape = room[nextRow].charAt(nextCol);
//
//                // 파티션이면
//                if (shape == PARTITION_SHAPE) continue;
//
//                // 사람이면
//                if (shape == PERSON_SHAPE) {
//                    return 0;
//                }
//
//                // 빈 테이블 이면
//                visited[nextRow][nextCol] = true;
//                queue.add(new int[]{nextRow, nextCol, dist + 1});
//            }
//        }
//        return 1;
//    }
//
//    private static boolean isMapIn(int row, int col) {
//        return 0 <= row && row < 5 && 0 <= col && col < 5;
//    }
}
