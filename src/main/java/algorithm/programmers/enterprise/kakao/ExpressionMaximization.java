package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionMaximization {
    private static List<Character> operatorType;
    private static List<Integer> numbers;
    private static List<Character> operators;

    private static char[] remember;
    private static boolean[] visited;
    private static long max = 0;

    public long solution(String expression) {
        Set<Character> set = new HashSet<>();
        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*') {
                operators.add(ch);
                set.add(ch);
                numbers.add(Integer.parseInt(sb.toString()));

                sb = new StringBuilder();
                continue;
            }
            sb.append(ch);
        }
        numbers.add(Integer.parseInt(sb.toString()));

        operatorType = new ArrayList<>(set);
        remember = new char[operatorType.size()];
        visited = new boolean[operatorType.size()];

        findPriority(0, operatorType.size());
        return max;
    }

    private static void findPriority(int depth, int targetDepth) {
        if (depth == targetDepth) {
            max = Math.max(max, Math.abs(findResult()));
            return;
        }

        for (int i = 0; i < operatorType.size(); i++) {
            if (!visited[i]) {
                remember[depth] = operatorType.get(i);
                visited[i] = true;
                findPriority(depth + 1, targetDepth);
                visited[i] = false;
            }
        }
    }

    private static long findResult() {
        List<Long> copyNumber = new ArrayList<>();
        List<Character> copyOperators = new ArrayList<>();

        for (int num : numbers) {
            copyNumber.add(Long.valueOf(num));
        }

        for (char ch : operators) {
            copyOperators.add(ch);
        }

        for (int i = 0; i < remember.length; i++) {
            char operator = remember[i];

            for (int j = 0; j < copyOperators.size(); j++) {
                if (operator == copyOperators.get(j)) {
                    long num1 = copyNumber.get(j);
                    long num2 = copyNumber.get(j + 1);

                    long result = calculate(operator, num1, num2);
                    copyNumber.remove(j + 1);
                    copyNumber.remove(j);
                    copyOperators.remove(j);

                    copyNumber.add(j, result);
                    j--;
                }
            }
        }
        return copyNumber.get(0);
    }

    private static long calculate(char opertor, long num1, long num2) {
        if (opertor == '+') {
            return num1 + num2;
        }

        if (opertor == '-') {
            return num1 - num2;
        }

        if (opertor == '*') {
            return num1 * num2;
        }
        return -1L;
    }
}
