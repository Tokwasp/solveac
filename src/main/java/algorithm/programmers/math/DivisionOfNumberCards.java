package algorithm.programmers.math;

public class DivisionOfNumberCards {
    public int solution(int[] arrayA, int[] arrayB) {
        // A 최대 공략수 찾기
        int minA = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            minA = findMin(minA, arrayA[i]);
        }

        // B와 비교
        for (int i = 0; i < arrayB.length; i++) {
            if (arrayB[i] % minA == 0) {
                minA = 0;
                break;
            }
        }

        // B 최대 공략수 찾기
        int minB = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            minB = findMin(minB, arrayB[i]);
        }

        // A와 비교
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] % minB == 0) {
                minB = 0;
                break;
            }
        }
        return Math.max(minA, minB);
    }

    private static int findMin(int a, int b) {
        while (b != 0) {
            int remain = a % b;
            a = b;
            b = remain;
        }
        return a;
    }
}
