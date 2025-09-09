package algorithm.programmers.math;

public class NumberBlock {
    public int[] solution(long begin, long last) {
        int start = (int) begin;
        int end = (int) last;

        int[] result = new int[end - start + 1];

        int index = 0;
        for (int i = start; i <= end; i++) {
            result[index++] = findNum(i);
        }
        return result;
    }

    private static int findNum(int num) {
        if (num == 1) return 0;

        int sqrt = (int) Math.sqrt(num);
        int min = 1;

        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                int max = num / i;

                if (max <= 10_000_000) {
                    return max;
                } else {
                    min = Math.max(min, i);
                }
            }
        }
        return min;
    }
}
