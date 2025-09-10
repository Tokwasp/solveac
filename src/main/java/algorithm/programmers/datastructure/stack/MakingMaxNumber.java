package algorithm.programmers.datastructure.stack;

import java.util.Stack;

public class MakingMaxNumber {
    public String solution(String number, int deleteCount) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            int num = number.charAt(i) - '0';

            if (stack.isEmpty()) {
                stack.add(num);
                continue;
            }

            while (!stack.isEmpty() && stack.peek() < num && 0 < deleteCount) {
                stack.pop();
                deleteCount--;
            }
            stack.add(num);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(String.valueOf(stack.pop()));
        }

        String result = sb.reverse().toString();
        if (result.length() == number.length()) {
            return result.substring(0, result.length() - deleteCount);
        }
        return result;
    }
}
