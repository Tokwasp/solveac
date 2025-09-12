package algorithm.programmers.datastructure.stack;

import java.util.Arrays;
import java.util.Stack;

public class BehindBigNumber {
    public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        // 초기화
        Arrays.fill(result, -1);

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];

            while (!stack.isEmpty() && numbers[stack.peek()] < num) {
                int index = stack.pop();
                result[index] = num;
            }
            stack.add(i);
        }
        return result;
    }
}
