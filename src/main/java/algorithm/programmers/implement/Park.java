package algorithm.programmers.implement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Park {
    private static String[][] parks;

    public int solution(int[] mats, String[][] parks) {
        this.parks = parks;
        Arrays.sort(mats);

        Queue<Integer> queue = new LinkedList<>();
        for (int num : mats) {
            queue.add(num);
        }

        int max = -1;
        for (int row = 0; row < parks.length; row++) {
            for (int col = 0; col < parks[0].length; col++) {
                while (!queue.isEmpty() && isPossible(row, col, queue.peek())) {
                    max = queue.poll();
                }
            }
        }
        return max;
    }

    private static boolean isPossible(int startRow, int startCol, int size) {
        for (int row = startRow; row < startRow + size; row++) {
            for (int col = startCol; col < startCol + size; col++) {
                if (!isMapIn(row, col)) {
                    return false;
                }

                if (!parks[row][col].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row < parks.length && 0 <= col && col < parks[0].length;
    }
}
