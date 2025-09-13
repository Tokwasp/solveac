package algorithm.programmers.datastructure.stack;

import java.util.Stack;

public class StockPrice {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        int size = prices.length - 1;
        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = size - index;
        }
        return result;
    }
}
