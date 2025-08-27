package algorithm.programmers.graph.bfs;

import java.util.*;

public class FindPrime {
    private static Set<Integer> set = new HashSet();
    private static List<Character> numbers = new ArrayList<>();
    private static boolean[] visited;

    public int solution(String input) {
        visited = new boolean[input.length()];
        permutation(input);
        return set.size();
    }

    private static void permutation(String input) {
        primeCheck();

        // 순열
        for (int i = 0; i < input.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                numbers.add(input.charAt(i));
                permutation(input);
                numbers.remove(numbers.size() - 1);
                visited[i] = false;
            }
        }
    }

    private static void primeCheck() {
        // 리스트가 빈 경우 예외 체크
        if (numbers.isEmpty()) return;

        int num = convertIntFrom(numbers);
        int sqrt = (int) Math.sqrt(num);

        // 예외 0,1 & 중복 체크
        if (num == 0) return;
        if (num == 1) return;

        // 소수 판별
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return;
            }
        }
        set.add(num);
    }

    private static int convertIntFrom(List<Character> numbers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            sb.append(numbers.get(i));
        }

        return Integer.parseInt(sb.toString());
    }
}
