package algorithm.programmers.divide;

public class QuadCompression {
    private static int zeroCount = 0;
    private static int oneCount = 0;

    public int[] solution(int[][] arr) {
        repeat(arr.length, 0, 0, arr);
        return new int[]{zeroCount, oneCount};
    }

    private static void repeat(int n, int startRow, int startCol, int[][] arr) {
        if (isSame(n, startRow, startCol, arr)) return;

        int nextN = n / 2;

        // 1사분면
        repeat(nextN, startRow, startCol + nextN, arr);

        // 2사분면
        repeat(nextN, startRow, startCol, arr);

        // 3사분면
        repeat(nextN, startRow + nextN, startCol, arr);

        // 4사분면
        repeat(nextN, startRow + nextN, startCol + nextN, arr);
    }

    private static boolean isSame(int n, int startRow, int startCol, int[][] arr) {
        int basic = arr[startRow][startCol];

        for (int i = startRow; i < startRow + n; i++) {
            for (int j = startCol; j < startCol + n; j++) {
                if (arr[i][j] != basic) return false;
            }
        }

        if (basic == 0) {
            zeroCount++;
        } else {
            oneCount++;
        }
        return true;
    }
}
