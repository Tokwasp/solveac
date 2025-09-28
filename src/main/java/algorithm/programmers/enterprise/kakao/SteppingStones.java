package algorithm.programmers.enterprise.kakao;

public class SteppingStones {
    public int solution(int[] stones, int k) {
        int start = 1;
        int end = 200_000_000;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (isPossible(mid, stones, k)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static boolean isPossible(int mid, int[] stones, int k) {
        int count = 0;

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - mid <= 0) {
                count++;
            } else {
                count = 0;
            }

            if (count == k) {
                return true;
            }
        }
        return false;
    }
}
