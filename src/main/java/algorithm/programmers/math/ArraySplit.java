package algorithm.programmers.math;

public class ArraySplit {
    public int[] solution(int n, long left, long right) {
        int[] result = new int[(int) (right - left) + 1];
        int index = 0;

        for (long num = left; num <= right; num++) {
            int row = (int) (num / n);
            int col = (int) (num % n);

            int arrNum = Math.max(row, col) + 1;
            result[index++] = arrNum;
        }
        return result;
    }
}
