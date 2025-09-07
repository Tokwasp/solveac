package algorithm.programmers.math;

public class FindPrime {
    private static final int MAX_NUM = 1_000_001;

    public int solution(int n) {
        boolean[] isPrimeArr = new boolean[MAX_NUM];

        for (int i = 2; i < isPrimeArr.length; i++) {
            isPrimeArr[i] = true;
        }

        for (int i = 2; i <= n; i++) {
            if (isPrimeArr[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    isPrimeArr[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrimeArr[i]) count++;
        }
        return count;
    }
}
