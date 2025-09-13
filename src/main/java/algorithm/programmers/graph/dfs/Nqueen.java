package algorithm.programmers.graph.dfs;

public class Nqueen {
    private static int n;
    private static int count;
    private static int[] remember;

    public int solution(int n) {
        this.n = n;
        count = 0;
        remember = new int[n + 1];

        dfs(1, n + 1);
        return count;
    }

    private static void dfs(int depth, int targetDepth) {
        if (depth == targetDepth) {
            count++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            remember[depth] = i;

            if (isSatisfied(depth)) {
                dfs(depth + 1, targetDepth);
            }
        }
    }

    private static boolean isSatisfied(int depth) {
        if (depth == 1) return true;

        for (int row = 1; row <= depth - 1; row++) {
            int col = remember[depth];
            int diff = depth - col;
            int sum = depth + col;

            // 열 체크
            if (remember[row] == col) return false;

            // 아래 대각선 체크
            if (row - remember[row] == diff) return false;

            // 위로 대각선 체크
            if (row + remember[row] == sum) return false;
        }
        return true;
    }
}
