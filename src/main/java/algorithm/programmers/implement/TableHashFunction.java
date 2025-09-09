package algorithm.programmers.implement;

import java.util.Arrays;

public class TableHashFunction {
    public int solution(int[][] data, int inputCol, int row_begin, int row_end) {
        // 정렬
        Arrays.sort(data, (a, b) -> {
            int diff = a[inputCol - 1] - b[inputCol - 1];
            if (diff != 0) return diff;
            return b[0] - a[0];
        });

        // 3,4 단계
        int result = 0;
        for (int i = row_begin; i <= row_end; i++) {
            int remain = 0;

            for (int num : data[i - 1]) {
                remain += num % i;
            }
            result = result ^ remain;
        }
        return result;
    }
}
