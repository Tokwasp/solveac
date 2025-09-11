package algorithm.programmers.datastructure.set;

import java.util.HashSet;
import java.util.Set;

public class PathLength {
    private static Set<String> destinationSet;

    public int solution(String directions) {
        destinationSet = new HashSet<>();

        int row = 5;
        int col = 5;
        int count = 0;

        for (int i = 0; i < directions.length(); i++) {
            char direction = directions.charAt(i);
            int[] coordinates = findCoordinates(direction);

            int nextRow = row + coordinates[0];
            int nextCol = col + coordinates[1];

            if (!isMapIn(nextRow, nextCol)) continue;

            String destination = convertToString(row, col, nextRow, nextCol);
            String reverse = convertToString(nextRow, nextCol, row, col);

            if (!destinationSet.contains(destination)) {
                destinationSet.add(destination);
                destinationSet.add(reverse);
                count++;
            }

            row = nextRow;
            col = nextCol;
        }
        return count;
    }

    private static int[] findCoordinates(char direction) {
        if (direction == 'U') {
            return new int[]{-1, 0};
        }

        if (direction == 'D') {
            return new int[]{1, 0};
        }

        if (direction == 'L') {
            return new int[]{0, -1};
        }

        if (direction == 'R') {
            return new int[]{0, 1};
        }

        return new int[]{0, 0};
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row <= 10 && 0 <= col && col <= 10;
    }

    private static String convertToString(int str1, int str2, int str3, int str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str1).append(" ");
        sb.append(str2).append(" ");
        sb.append(str3).append(" ");
        sb.append(str4);
        return sb.toString();
    }
}
