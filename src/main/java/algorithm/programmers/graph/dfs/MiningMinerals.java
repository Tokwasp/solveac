package algorithm.programmers.graph.dfs;

public class MiningMinerals {
    private static final int[][] TIRED_SCORE = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

    private static int[] picks;
    private static String[] minerals;
    private static int min = Integer.MAX_VALUE;

    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;

        dfs(0, minerals.length / 5 + 1, 0);
        return min;
    }

    private void dfs(int depth, int targetDepth, int tired) {
        if (depth == targetDepth || notAvailablePick()) {
            min = Math.min(min, tired);
            return;
        }

        for (int i = 0; i < picks.length; i++) {
            if (picks[i] != 0) {
                picks[i] -= 1;
                dfs(depth + 1, targetDepth, tired + calculateMine(i, depth));
                picks[i] += 1;
            }
        }
    }

    private boolean notAvailablePick() {
        for (int i = 0; i < 3; i++) {
            if (picks[i] != 0) return false;
        }
        return true;
    }

    private int calculateMine(int select, int depth) {
        int count = 0;
        int sum = 0;

        for (int i = depth * 5; i < minerals.length; i++) {
            if (count == 5) {
                return sum;
            }

            String mineral = minerals[i];
            int tired = convertTiredFrom(select, mineral);
            sum += tired;
            count++;
        }
        return sum;
    }

    private int convertTiredFrom(int select, String mineral) {
        if (mineral.equals("diamond")) {
            return TIRED_SCORE[select][0];
        }
        if (mineral.equals("iron")) {
            return TIRED_SCORE[select][1];
        }
        if (mineral.equals("stone")) {
            return TIRED_SCORE[select][2];
        }
        return -1;
    }
}
