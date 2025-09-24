package algorithm.programmers.math;

public class Lotto {
    public int[] solution(int[] lottos, int[] winNums) {
        int correctCount = 0;
        int zeroCount = 0;

        for (int i = 0; i < lottos.length; i++) {
            int lotto = lottos[i];

            if (lotto == 0) {
                zeroCount++;
                continue;
            }

            for (int j = 0; j < winNums.length; j++) {
                if (lotto == winNums[j]) {
                    correctCount++;
                    break;
                }
            }
        }

        int worst = 7 - correctCount;
        if (worst == 7) {
            worst = 6;
        }

        int best = 7 - (correctCount + zeroCount);
        if (best == 7) {
            best = 6;
        }
        return new int[]{best, worst};
    }
}
