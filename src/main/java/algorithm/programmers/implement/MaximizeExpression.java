package algorithm.programmers.implement;

import java.util.ArrayList;
import java.util.List;

public class MaximizeExpression {
    private static char[] operatorCh = {'+', '-', '*'};
    private static List<Long> numbers;
    private static List<Character> operators;
    private static long max = 0;

    public long solution(String expression) {
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        List<Character> priority = new ArrayList<>();

        int lastIndex = 0;
        for (int i = 0; i < expression.length(); i++) {
            char curCh = expression.charAt(i);

            if (curCh == '+' || curCh == '-' || curCh == '*') {
                String numStr = expression.substring(lastIndex, i);
                numbers.add(Long.parseLong(numStr));
                operators.add(curCh);
                lastIndex = i + 1;
            }
        }
        numbers.add(Long.parseLong(expression.substring(lastIndex, expression.length())));

        // 순열
        permutation(0, 3, priority, new boolean[operatorCh.length]);


        return max;
    }

    private static void permutation(int depth, int targetDepth,
                                    List<Character> result, boolean[] visited) {
        if (depth == targetDepth) {
            max = Math.max(max, findMax(result));
            return;
        }

        for (int i = 0; i < operatorCh.length; i++) {
            if (!visited[i]) {
                result.add(operatorCh[i]);
                visited[i] = true;
                permutation(depth + 1, targetDepth, result, visited);
                result.remove(result.size() - 1);
                visited[i] = false;
            }
        }
    }

    private static long findMax(List<Character> priority) {
        List<Long> nums = new ArrayList<>(numbers);
        List<Character> opers = new ArrayList<>(operators);

        for (char targetOperator : priority) {
            for (int i = 0; i < opers.size(); i++) {
                if (opers.get(i) == targetOperator) {
                    long result = calculate(nums.get(i), nums.get(i + 1), targetOperator);

                    nums.remove(i + 1);
                    nums.remove(i);
                    opers.remove(i);
                    nums.add(i, result);

                    i--;
                }
            }
        }
        return Math.abs(nums.get(0));
    }

    private static long calculate(long num1, long num2, char operator) {
        if (operator == '+') {
            return num1 + num2;
        } else if (operator == '-') {
            return num1 - num2;
        } else {
            return num1 * num2;
        }
    }
}
