package algorithm.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BracketValue2504 {
    private static final int INVALID = 0;
    private static int result = 0;
    private static int value = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char bracket = input.charAt(i);

            if (bracket == '(') {
                stack.push(bracket);
                value *= 2;
            } else if (bracket == '[') {
                stack.push(bracket);
                value *= 3;
            } else if (bracket == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(INVALID);
                    return;
                }
                if (input.charAt(i - 1) == '(') {
                    result += value;
                }
                stack.pop();
                value /= 2;
            } else if (bracket == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(INVALID);
                    return;
                }
                if (input.charAt(i - 1) == '[') {
                    result += value;
                }
                stack.pop();
                value /= 3;
            } else {
                System.out.println(INVALID);
                return;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(INVALID);
        } else {
            System.out.println(result);
        }
    }
}
