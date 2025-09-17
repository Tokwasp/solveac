package algorithm.programmers.graph.dfs;

public class VowelDictionary {
    private static final String[] WORDS = {"A", "E", "I", "O", "U"};
    private static String[] remember;
    private static int count;
    private static int result;

    public int solution(String target) {
        remember = new String[WORDS.length];
        result = 0;
        count = 0;

        dfs(0, WORDS.length, target);
        return result;
    }

    private static void dfs(int depth, int maxDepth, String target) {
        if (isEqualsTarget(depth, target)) {
            result = count;
            return;
        }

        if (depth == maxDepth) {
            return;
        }

        for (int i = 0; i < WORDS.length; i++) {
            remember[depth] = WORDS[i];
            count++;
            dfs(depth + 1, maxDepth, target);
        }
    }

    private static boolean isEqualsTarget(int depth, String target) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(remember[i]);
        }
        String result = sb.toString();
        return result.equals(target);
    }
}
