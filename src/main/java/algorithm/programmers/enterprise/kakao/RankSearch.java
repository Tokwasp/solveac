package algorithm.programmers.enterprise.kakao;

import java.util.*;

public class RankSearch {
    private static Map<String, List<Integer>> queryScoreMap;

    public int[] solution(String[] infos, String[] queries) {
        queryScoreMap = new HashMap<>();

        // input 받기
        for (int i = 0; i < infos.length; i++) {
            String[] info = infos[i].split(" ");

            // 쿼리 탐색
            dfs(0, 4, info);
        }

        // 정렬
        for (Map.Entry<String, List<Integer>> entry : queryScoreMap.entrySet()) {
            List<Integer> scores = entry.getValue();
            scores.sort(Comparator.naturalOrder());
        }

        // 쿼리 실행
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i].split(" and ");

            String[] scoreEat = query[3].split(" ");
            String eat = scoreEat[0];
            int score = Integer.parseInt(scoreEat[1]);

            String queryString = changeQuery(query, eat);
            result[i] = findPeople(queryString, score);
        }
        return result;
    }

    private static void dfs(int depth, int targetDepth, String[] info) {
        if (depth == targetDepth) {
            String query = convertString(info);
            List<Integer> scores = queryScoreMap.getOrDefault(query, null);

            if (scores == null) {
                queryScoreMap.put(query, new ArrayList<>());
            }

            queryScoreMap.get(query).add(Integer.parseInt(info[4]));
            return;
        }

        // '-'로 바꾼 경우
        String temp = info[depth];
        info[depth] = "-";
        dfs(depth + 1, targetDepth, info);

        // 안바꾼 경우
        info[depth] = temp;
        dfs(depth + 1, targetDepth, info);
    }

    private static String convertString(String[] info) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                sb.append(info[i]);
            } else {
                sb.append(info[i]).append(" ");
            }
        }
        return sb.toString();
    }

    private static String changeQuery(String[] query, String last) {
        StringBuilder sb = new StringBuilder();
        sb.append(query[0]).append(" ");
        sb.append(query[1]).append(" ");
        sb.append(query[2]).append(" ");
        sb.append(last);
        return sb.toString();
    }

    private static int findPeople(String query, int target) {
        List<Integer> scores = queryScoreMap.get(query);
        if (scores == null) {
            return 0;
        }

        int start = 0;
        int end = scores.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int curScore = scores.get(mid);

            if (curScore >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return scores.size() - start;
    }
}
