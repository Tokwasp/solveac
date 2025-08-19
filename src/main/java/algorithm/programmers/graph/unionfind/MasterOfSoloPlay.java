package algorithm.programmers.graph.unionfind;

public class MasterOfSoloPlay {
    private static int[] parents;
    private static boolean[] visited;
    private static int maxCount = 0;

    public int solution(int[] cards) {
        parents = new int[cards.length + 1];

        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= cards.length; i++) {
            int endNode = cards[i - 1];
            unionParent(i, endNode);
        }

        // 그룹 선정
        for (int i = 1; i < parents.length; i++) {
            visited = new boolean[parents.length];
            int first = sameParentCount(getParent(i));

            for (int j = 1; j < parents.length; j++) {
                if (!visited[j]) {
                    int second = sameParentCount(getParent(j));
                    maxCount = Math.max(maxCount, first * second);
                }
            }
        }
        return maxCount;
    }

    private static void unionParent(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);

        if (aParent < bParent) {
            parents[bParent] = aParent;
        } else {
            parents[aParent] = bParent;
        }
    }

    private static int getParent(int x) {
        if (parents[x] == x) return x;
        return parents[x] = getParent(parents[x]);
    }

    private static int sameParentCount(int x) {
        int count = 0;
        for (int i = 1; i < parents.length; i++) {
            if (getParent(parents[i]) == x) {
                visited[i] = true;
                count++;
            }
        }
        return count;
    }
}
