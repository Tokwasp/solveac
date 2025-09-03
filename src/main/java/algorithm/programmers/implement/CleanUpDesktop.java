package algorithm.programmers.implement;

public class CleanUpDesktop {
    public int[] solution(String[] wallpaper) {
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        int maxRow = Integer.MIN_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (int row = 0; row < wallpaper.length; row++) {
            String paper = wallpaper[row];

            for (int col = 0; col < paper.length(); col++) {
                char shape = paper.charAt(col);

                if (shape == '#') {
                    minRow = Math.min(minRow, row);
                    minCol = Math.min(minCol, col);

                    maxRow = Math.max(maxRow, row);
                    maxCol = Math.max(maxCol, col);
                }
            }
        }

        return new int[]{minRow, minCol, maxRow + 1, maxCol + 1};
    }
}
