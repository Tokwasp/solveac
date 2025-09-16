package algorithm.programmers.graph.dfs;

import java.util.Stack;

public class TargetNumber {
    private static int[] numbers;
    private static int count;

    public int solution(int[] numbers, int target) {
        count = 0;
        this.numbers = numbers;
        dfs(0, numbers.length, 0, target);
        return count;
    }

    private static void dfs(int depth, int targetDepth, int total, int target) {
        if (depth == targetDepth) {
            if (total == target) count++;
            return;
        }

        dfs(depth + 1, targetDepth, total + numbers[depth], target);
        dfs(depth + 1, targetDepth, total - numbers[depth], target);
    }

    public int solution2(int[] numbers, int target) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(0);

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

            while (!stack1.isEmpty()) {
                int stackNum = stack1.pop();
                stack2.push(stackNum + number);
                stack2.push(stackNum - number);
            }
            stack1 = stack2;
            stack2 = new Stack<>();
        }

        int count = 0;
        while (!stack1.isEmpty()) {
            if (stack1.pop() == target) count++;
        }
        return count;
    }
}
