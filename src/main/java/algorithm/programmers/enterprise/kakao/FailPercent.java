package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FailPercent {
    public int[] solution(int N, int[] stages) {
        Arrays.sort(stages);
        List<Result> failStages = new ArrayList<>();

        int index = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            int startIndex = index;

            while (index <= stages.length - 1 && stages[index] == i) {
                index++;
                count++;
            }

            int length = stages.length - startIndex;
            double failPercent = 0;
            if (length != 0) {
                failPercent = count / (double) length;
            }
            failStages.add(new Result(i, failPercent));
        }

        failStages.sort(Comparator.naturalOrder());
        return failStages.stream().mapToInt(a -> a.stageNum).toArray();
    }

    private static class Result implements Comparable<Result> {
        private int stageNum;
        private double failPercent;

        public Result(int stageNum, double failPercent) {
            this.stageNum = stageNum;
            this.failPercent = failPercent;
        }

        public int compareTo(Result another) {
            double compare = this.failPercent - another.failPercent;
            if (compare < 0) {
                return 1;
            } else if (compare == 0) {
                return this.stageNum - another.stageNum;
            } else {
                return -1;
            }
        }
    }
}
