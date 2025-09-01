package algorithm.programmers.implement;

public class WorkInThePark {
    private static final String[] DIRECTIONS = {"N", "S", "W", "E"};
    private static final int[][] MOVE_COORDINATES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static final char START_SHAPE = 'S';
    private static final char BAN_SHAPE = 'X';
    private static char[][] map;

    public int[] solution(String[] parks, String[] routes) {
        map = new char[parks.length][parks[0].length()];

        int startRow = 0;
        int startCol = 0;

        // 맵 입력 받기
        for (int row = 0; row < parks.length; row++) {
            String park = parks[row];

            for (int col = 0; col < park.length(); col++) {
                char shape = park.charAt(col);
                map[row][col] = shape;

                // 시작 지점 체크
                if (shape == START_SHAPE) {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        // 출발
        int[] destination = move(startRow, startCol, routes);
        return new int[]{destination[0], destination[1]};
    }

    private static int[] move(int startRow, int startCol, String[] routes) {
        int row = startRow;
        int col = startCol;

        for (int i = 0; i < routes.length; i++) {
            String[] route = routes[i].split(" ");
            String moveDirection = route[0];
            int moveCount = Integer.parseInt(route[1]);

            // moveDirection 방향으로 moveCount칸 움직일 수 있는가?
            if (!isMoveAble(row, col, moveDirection, moveCount)) continue;

            // 움직일 수 있다면 해당 방향으로 이동
            int moveIndex = findDirectionIndex(moveDirection);
            row += MOVE_COORDINATES[moveIndex][0] * moveCount;
            col += MOVE_COORDINATES[moveIndex][1] * moveCount;
        }

        return new int[]{row, col};
    }

    private static boolean isMoveAble(int row, int col, String direction, int moveCount) {
        int curRow = row;
        int curCol = col;
        int curCount = 0;

        int moveIndex = findDirectionIndex(direction);

        // 1칸씩 이동
        while (curCount != moveCount) {
            curRow += MOVE_COORDINATES[moveIndex][0];
            curCol += MOVE_COORDINATES[moveIndex][1];

            // 맵 밖이면 이동 불가
            if (!isMapIn(curRow, curCol)) return false;

            // 벽이면 이동 불가
            if (map[curRow][curCol] == BAN_SHAPE) return false;

            curCount++;
        }
        return true;
    }

    private static int findDirectionIndex(String direction) {
        for (int i = 0; i < DIRECTIONS.length; i++) {
            if (DIRECTIONS[i].equals(direction)) return i;
        }
        return -1;
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
    }
}
