package algorithm.programmers.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class WordChange {
    public int solution(String begin, String target, String[] words) {
        int result = bfs(begin, target, words);
        return result;
    }

    private static int bfs(String start, String target, String[] words) {
        Queue<String[]> queue = new LinkedList<>();
        queue.add(new String[]{start, "0"});
        boolean[] visited = new boolean[words.length];

        while (!queue.isEmpty()) {
            String[] poll = queue.poll();
            String curWord = poll[0];
            int dist = Integer.parseInt(poll[1]);

            if (curWord.equals(target)) {
                return dist;
            }

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (visited[i]) continue;

                if (isDifferOneChar(curWord, word)) {
                    queue.add(new String[]{word, String.valueOf(dist + 1)});
                    visited[i] = true;
                }
            }
        }
        return 0;
    }

    private static boolean isDifferOneChar(String word, String target) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) count++;
        }
        return count == 1 ? true : false;
    }
}
