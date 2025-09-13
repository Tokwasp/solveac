package algorithm.programmers.datastructure.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RotatingParentheses {
    private static Map<Character, Character> map;

    public int solution(String s) {
        map = new HashMap<>();
        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');

        String next = s;
        int count = 0;

        // 초기 값 검사
        if (isCorrect(s)) count++;

        // 한칸씩 옮기기
        for (int i = 0; i < s.length() - 1; i++) {
            next = findNext(next);
            if (isCorrect(next)) count++;
        }
        return count;
    }

    private static String findNext(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < input.length(); i++) {
            sb.append(input.charAt(i));
        }

        sb.append(input.charAt(0));
        return sb.toString();
    }

    private static boolean isCorrect(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // '(', '[', '{'
            if (map.containsKey(ch)) {
                stack.push(ch);
                continue;
            }

            // ')', ']', '}'
            if (stack.isEmpty() || map.get(stack.peek()) != ch) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
