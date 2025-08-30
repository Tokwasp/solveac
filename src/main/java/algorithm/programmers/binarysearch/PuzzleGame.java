package algorithm.programmers.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class PuzzleGame {
    public int solution(int[] diffs, int[] times, long limit) {
        List<Puzzle> puzzles = new ArrayList<>();

        // 퍼즐 입력 받기
        for (int i = 0; i < diffs.length; i++) {
            int prevTime = getPrevTime(i, times);
            puzzles.add(new Puzzle(diffs[i], prevTime, times[i]));
        }

        int minLevel = 1;
        int maxLevel = 100000;

        // 퍼즐 풀기
        while (minLevel <= maxLevel) {
            long time = 0;
            int midLevel = (minLevel + maxLevel) / 2;

            for (int i = 0; i < puzzles.size(); i++) {
                Puzzle puzzle = puzzles.get(i);
                time += puzzle.getSolveTime(midLevel);

                if (limit < time) break;
            }

            if (limit < time) {
                minLevel = midLevel + 1;
            } else {
                maxLevel = midLevel - 1;
            }
        }

        return minLevel;
    }

    private static int getPrevTime(int index, int[] times) {
        if (index == 0) return 0;
        return times[index - 1];
    }

    static class Puzzle {
        private int level;
        private int prevTime;
        private int curTime;

        public Puzzle(int level, int prevTime, int curTime) {
            this.level = level;
            this.prevTime = prevTime;
            this.curTime = curTime;
        }

        public int getSolveTime(int userLevel) {
            if (this.level <= userLevel) {
                return this.curTime;
            } else {
                int repeat = this.level - userLevel;
                return (this.prevTime + curTime) * repeat + curTime;
            }
        }
    }
}
