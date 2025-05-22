package algorithm.programmers.enterprise.kakao;

public class ArcheryCompetition {
    private static int[] apeachInfo;
    private static int[] rionInfo;
    private static int[] result;

    private static int arrowCount;
    private static int maxDiff = 0;

    public int[] solution(int arrowCount, int[] apeachInfo) {
        this.apeachInfo = apeachInfo;
        this.arrowCount = arrowCount;

        result = new int[11];
        rionInfo = new int[11];

        dfs(0, 0);

        return maxDiff == 0 ? new int[]{-1} : result;
    }

    private static void dfs(int depth, int shootCount) {
        if (depth == 10) {
            rionInfo[10] += arrowCount - shootCount;

            int diff = getDiff();
            if (diff > 0) {
                if (diff > maxDiff || (diff == maxDiff && isBetter(rionInfo, result))) {
                    maxDiff = diff;
                    updateResult();
                }
            }

            rionInfo[10] = 0;
            return;
        }

        // 해당 점수를 포기
        dfs(depth + 1, shootCount);

        // 해당 점수를 획득
        int need = apeachInfo[depth] + 1;
        if (shootCount + need <= arrowCount) {
            rionInfo[depth] = need;
            dfs(depth + 1, shootCount + need);
            rionInfo[depth] = 0;
        }
    }

    private static int getDiff() {
        int rionScore = 0;
        int apeachScore = 0;

        for (int i = 0; i < 11; i++) {
            if (apeachInfo[i] == 0 && rionInfo[i] == 0) continue;

            if (rionInfo[i] > apeachInfo[i]) {
                rionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }

        return rionScore - apeachScore;
    }

    private static void updateResult() {
        for (int i = 0; i < 11; i++) {
            result[i] = rionInfo[i];
        }
    }

    private static boolean isBetter(int[] newInfo, int[] oldInfo) {
        for (int i = 10; i >= 0; i--) {
            if (newInfo[i] > oldInfo[i]) return true;
            else if (newInfo[i] < oldInfo[i]) return false;
        }
        return false;
    }
}
