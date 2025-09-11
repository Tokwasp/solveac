package algorithm.programmers.enterprise.kakao;

public class TakeAGroupPhoto {
    private static final char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    private static boolean[] visited;
    private static char[] remember;
    private static char[][] dataArr;
    private static int count;

    public int solution(int dataCount, String[] data) {
        count = 0;
        remember = new char[friends.length];
        visited = new boolean[friends.length];
        dataArr = new char[dataCount][4];

        // data 나누어 저장
        for (int i = 0; i < data.length; i++) {
            String input = data[i];
            char people1 = input.charAt(0);
            char people2 = input.charAt(2);
            char operator = input.charAt(3);
            char dist = input.charAt(4);

            dataArr[i][0] = people1;
            dataArr[i][1] = people2;
            dataArr[i][2] = operator;
            dataArr[i][3] = dist;
        }

        // 순열
        dfs(0, friends.length);
        return count;
    }

    private static void dfs(int depth, int targetDepth) {
        if (depth == targetDepth) {
            if (isSatisfied()) {
                count++;
            }
            return;
        }

        for (int i = 0; i < friends.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                remember[depth] = friends[i];
                dfs(depth + 1, targetDepth);
                visited[i] = false;
            }
        }
    }

    private static boolean isSatisfied() {
        for (int i = 0; i < dataArr.length; i++) {
            char people1 = dataArr[i][0];
            char people2 = dataArr[i][1];

            char operator = dataArr[i][2];
            int dist = dataArr[i][3] - '0';

            int findDist = findDist(people1, people2);
            if (!isPass(dist, findDist, operator)) {
                return false;
            }
        }
        return true;
    }

    private static int findDist(char people1, char people2) {
        int people1Index = 0;
        int people2Index = 0;

        for (int i = 0; i < remember.length; i++) {
            char friend = remember[i];

            if (friend == people1) {
                people1Index = i;
            }

            if (friend == people2) {
                people2Index = i;
            }
        }
        return Math.abs(people1Index - people2Index) - 1;
    }

    private static boolean isPass(int dist, int findDist, char operator) {
        if (operator == '<') {
            return findDist < dist;
        }

        if (operator == '=') {
            return findDist == dist;
        }

        if (operator == '>') {
            return findDist > dist;
        }

        return false;
    }
}
