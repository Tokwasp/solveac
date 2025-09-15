package algorithm.programmers.divide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TriangleSnail {
    private static List<Coordinate> list;

    public int[] solution(int n) {
        list = new ArrayList<>();
        repeat(n, 0, 0, 1);

        list.sort(Comparator.naturalOrder());
        return list.stream().mapToInt(Coordinate::getNumber).toArray();
    }

    private static void repeat(int n, int startRow, int startCol, int startNum) {
        int num = startNum;

        // 왼쪽변
        for (int i = 0; i < n; i++) {
            list.add(new Coordinate(startRow + i, startCol, num++));
        }

        // 밑변
        for (int i = 1; i <= n - 2; i++) {
            list.add(new Coordinate(startRow + n - 1, startCol + i, num++));
        }

        // 오른쪽변
        for (int i = 0; i < n - 1; i++) {
            list.add(new Coordinate(startRow + n - 1 - i, startCol + n - 1 - i, num++));
        }

        int nextN = n - 3;
        int nextStartRow = startRow + 2;
        int nextStartCol = startCol + 1;

        if (nextN > 0) {
            repeat(nextN, nextStartRow, nextStartCol, num);
        }
    }

    static class Coordinate implements Comparable<Coordinate> {
        public int row;
        public int col;
        public int number;

        public Coordinate(int row, int col, int number) {
            this.row = row;
            this.col = col;
            this.number = number;
        }

        public int compareTo(Coordinate another) {
            int result = this.row - another.row;
            if (result != 0) return result;
            return this.col - another.col;
        }

        public int getNumber() {
            return this.number;
        }
    }
}
