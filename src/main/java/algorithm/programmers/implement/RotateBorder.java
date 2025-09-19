package algorithm.programmers.implement;

public class RotateBorder {
    private static int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        this.map = new int[rows][columns];
        initMap();
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int startRow = queries[i][0] - 1;
            int startCol = queries[i][1] - 1;

            int endRow = queries[i][2] - 1;
            int endCol = queries[i][3] - 1;

            result[i] = process(map, startRow, startCol, endRow, endCol);
        }
        return result;
    }

    private static void initMap() {
        int num = 1;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                map[row][col] = num++;
            }
        }
    }

    private static int process(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        int min = Integer.MAX_VALUE;
        int temp = map[startRow][startCol];

        // 상 이동
        for (int row = startRow; row < endRow; row++) {
            map[row][startCol] = map[row + 1][startCol];
            min = Math.min(min, map[row][startCol]);
        }

        // 왼 이동
        for (int col = startCol; col < endCol; col++) {
            map[endRow][col] = map[endRow][col + 1];
            min = Math.min(min, map[endRow][col]);
        }

        // 아래 이동
        for (int row = endRow; row > startRow; row--) {
            map[row][endCol] = map[row - 1][endCol];
            min = Math.min(min, map[row][endCol]);
        }

        // 오른쪽 이동
        for (int col = endCol; col > startCol; col--) {
            map[startRow][col] = map[startRow][col - 1];
            min = Math.min(min, map[startRow][col]);
        }
        map[startRow][startCol + 1] = temp;
        min = Math.min(min, map[startRow][startCol + 1]);
        return min;
    }
}
