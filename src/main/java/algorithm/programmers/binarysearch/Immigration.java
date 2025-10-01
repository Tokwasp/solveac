package algorithm.programmers.binarysearch;

public class Immigration {
    public long solution(int n, int[] times) {
        long start = 1;
        long end = (long) Math.pow(10, 18);

        while (start <= end) {
            long midTime = (start + end) / 2;

            long count = 0;
            for (int time : times) {
                count += midTime / time;
            }

            if (count >= n) {
                end = midTime - 1;
            } else {
                start = midTime + 1;
            }
        }
        return start;
    }
}
