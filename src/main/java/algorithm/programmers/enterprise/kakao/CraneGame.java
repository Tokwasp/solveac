package algorithm.programmers.enterprise.kakao;

import java.util.Stack;

public class CraneGame {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        // 크레인 동작
        for (int i = 0; i < moves.length; i++) {
            int col = moves[i] - 1;

            // 인형 집기
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != 0) {
                    stack.push(board[row][col]);
                    board[row][col] = 0;
                    break;
                }
            }

            // 바구니 인형 확인
            if (stack.size() >= 2) {
                int num1 = stack.pop();
                int num2 = stack.pop();

                if (num1 == num2) {
                    count += 2;
                } else {
                    stack.push(num2);
                    stack.push(num1);
                }
            }
        }
        return count;
    }
}
