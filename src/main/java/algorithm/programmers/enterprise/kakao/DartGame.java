package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DartGame {
    public int solution(String dartResult) {
        List<Dart> darts = new ArrayList<>();
        int index = 0;

        while (index <= dartResult.length() - 1) {
            int score = 0;
            if (dartResult.charAt(index + 1) == '0') {
                score = 10;
                index += 2;
            } else {
                score = dartResult.charAt(index) - '0';
                index += 1;
            }

            char bonus = dartResult.charAt(index++);
            char option = '0';

            if (index <= dartResult.length() - 1 &&
                    (dartResult.charAt(index) == '*' || dartResult.charAt(index) == '#')) {
                option = dartResult.charAt(index++);
            }
            darts.add(new Dart(score, bonus, option));
        }

        Stack<Dart> stack = new Stack<>();
        stack.push(new Dart(0, 'S', '0'));
        for (int i = 0; i < darts.size(); i++) {
            Dart dart = darts.get(i);
            dart.calculateScore();

            Dart priorDart = stack.pop();
            dart.applyOption(priorDart);
            stack.push(priorDart);
            stack.push(dart);
        }

        int score = 0;
        while (!stack.isEmpty()) {
            score += stack.pop().score;
        }
        return score;
    }

    static class Dart {
        private int score;
        private char bonus;
        private char option;

        public Dart(int score, char bonus, char option) {
            this.score = score;
            this.bonus = bonus;
            this.option = option;
        }

        public int calculateScore() {
            if (bonus == 'S') {
                score = (int) Math.pow(score, 1);
            }

            if (bonus == 'D') {
                score = (int) Math.pow(score, 2);
            }

            if (bonus == 'T') {
                score = (int) Math.pow(score, 3);
            }
            return score;
        }

        public void applyOption(Dart priorDart) {
            if (option == '0') {
                return;
            }

            if (option == '*') {
                priorDart.score *= 2;
                score *= 2;
            }

            if (option == '#') {
                score *= -1;
            }
        }
    }
}
