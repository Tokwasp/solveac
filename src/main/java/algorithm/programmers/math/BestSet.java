package algorithm.programmers.math;

public class BestSet {
    private static int[] remember;

    public int[] solution(int length, int targetNum) {
        this.remember = new int[length];

        if (length > targetNum) {
            return new int[]{-1};
        }

        dfs(0, length, targetNum);

        for (int i = 0; i < remember.length; i++) {
            if (remember[i] == 0) {
                return new int[]{-1};
            }
        }

        return remember;
    }

    private static void dfs(int depth, int targetDepth, int remain) {
        if (depth == targetDepth) {
            return;
        }

        int divide = targetDepth - depth;
        int num = remain / divide;
        int next = remain - num;

        remember[depth] = num;
        dfs(depth + 1, targetDepth, next);
    }
}
